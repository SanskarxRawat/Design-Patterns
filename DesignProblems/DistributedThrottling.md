# Design Distributed Throttling for Web Crawlers

In a distributed web crawling system, there are **n** crawler machines responsible for downloading web pages. Each URL is assigned to a crawler based on the rule `Hash(URL) mod n`. However, since downloading a web page consumes bandwidth from the web server hosting that page, it is critical that the combined requests from all crawlers do not exceed **b bytes per minute** for any given website. This design outlines a distributed throttling mechanism that coordinates crawler activity to enforce per-website bandwidth limits.

---

## **1. Clarify Requirements**
- **Functional Requirements:**
    - Each crawler must process only the URLs for which it is responsible.
    - A central coordination mechanism must ensure that, collectively, crawlers do not request more than **b bytes** from any individual website within a one-minute window.
    - Crawlers must request permission before downloading a page from a specific website.

- **Non-Functional Requirements:**
    - **Scalability:** The throttling mechanism should scale with the number of crawlers.
    - **Low Latency:** Permission checks should incur minimal delay to maintain crawling throughput.
    - **Fault Tolerance:** The system should handle individual crawler or coordinator failures gracefully.

- **Questions to Consider:**
    - How dynamic is the value of **b**? Should the system support dynamic adjustments per website?
    - What is the expected rate of requests and how frequently do websites get hit?
    - How will the system handle network partitions or delays between crawlers and the coordination server?

---

## **2. Key Components and Overall Approach**
1. **Crawler Machines (Workers):**
    - Each machine is assigned a set of URLs based on `Hash(URL) mod n`.
    - Before downloading, a crawler must request permission from the coordination server to ensure the website’s bandwidth quota is not exceeded.

2. **Throttling Coordinator (Central Server):**
    - Maintains a per-website record of bandwidth usage (in bytes) for the current minute.
    - Applies a rate-limiting algorithm (e.g., token bucket or leaky bucket) for each website.
    - Responds to permission requests from crawlers, allowing or delaying downloads as needed.

3. **Rate-Limiting Mechanism:**
    - Each website is allocated a bucket that can hold up to **b bytes**.
    - When a crawler intends to download a page, it must “spend” bytes from the corresponding bucket.
    - The bucket is replenished at the start of each minute to reset the bandwidth quota.

---

## **3. High-Level Design**
- **Workflow:**
    1. **URL Assignment:** Each crawler receives URLs according to `Hash(URL) mod n`.
    2. **Permission Request:** Before initiating a download, a crawler sends a request to the Throttling Coordinator, including:
        - The target website (extracted from the URL).
        - The estimated size of the download (in bytes).
    3. **Bandwidth Check:** The coordinator checks the current usage for that website:
        - **If** adding the requested bytes does not exceed the limit **b**, the coordinator grants permission and updates the usage counter.
        - **If not**, the request is denied or delayed until the quota is replenished.
    4. **Download Execution:** Once permission is granted, the crawler downloads the page.
    5. **Periodic Reset:** Every minute, the coordinator resets the bandwidth usage counters for all websites.

- **Coordination Model:**
    - The Throttling Coordinator acts as a central rate limiter.
    - Communication between crawlers and the coordinator should be lightweight and highly available.
    - Optionally, a distributed or replicated coordination service (e.g., based on a consensus protocol) can be used to avoid a single point of failure.

---

## **4. Detailed Design Considerations**

### **4.1 Crawler Responsibilities**
- **URL Hashing and Assignment:**
    - Each crawler uses the hash function to determine its set of URLs.
- **Pre-Download Check:**
    - Before fetching any URL, the crawler contacts the coordinator with the website and expected page size.
- **Backoff and Retry:**
    - If permission is denied, the crawler waits for a short interval before retrying, ensuring that it does not overload the coordinator.

### **4.2 Throttling Coordinator**
- **Data Structures:**
    - Maintain an in-memory table mapping website identifiers to their current byte usage and remaining quota.
- **Rate-Limiting Algorithm:**
    - Use a token bucket model where the bucket is refilled at the beginning of every minute.
    - Ensure atomic updates to the bucket counters to avoid race conditions.
- **Handling Edge Cases:**
    - If a download exceeds the remaining quota, the coordinator can either partially allow the request (if the protocol supports partial content) or require the crawler to delay the request.
- **Scalability and Fault Tolerance:**
    - For high availability, consider replicating the coordinator state across multiple nodes.
    - Use caching and eventual consistency to ensure that temporary coordinator failures do not block the entire crawl.

---

## **5. Scalability & Performance Considerations**
- **Network Overhead:**
    - Keep the permission request messages lightweight to minimize network latency.
- **Coordinator Load:**
    - Optimize the coordinator to handle high-frequency, small-sized messages.
    - Consider batching requests or using asynchronous communication.
- **Distributed Coordination:**
    - To prevent the coordinator from becoming a bottleneck, a distributed coordination service can be used to partition the rate-limiting responsibilities by website domain.

---

## **6. Trade-Offs**
- **Centralized vs. Distributed Coordination:**
    - A centralized coordinator is simpler but may become a single point of failure.
    - A distributed solution increases complexity but improves resilience and scalability.
- **Strict Enforcement vs. Throughput:**
    - Strict rate limiting guarantees compliance with bandwidth constraints but may slow down the crawl.
    - Allowing some flexibility or burst tolerance can increase throughput but risks occasional overuse of a website’s bandwidth.

---

## **7. Failure Handling**
- **Coordinator Failures:**
    - Implement failover strategies using redundant coordinator instances.
    - Use persistent logs or distributed consensus to restore state after a failure.
- **Crawler Timeouts:**
    - If a crawler does not receive a response within a specified time, it should retry the permission request.
- **Network Partitions:**
    - Design the system to gracefully handle temporary network failures between crawlers and the coordinator.

---

## **8. Example Walkthrough**
### **Scenario:**
- Assume **n = 100** crawlers and a bandwidth limit **b = 1 MB/minute** per website.

### **Step-by-Step:**
1. **URL Assignment:**
    - Crawler 17 is responsible for URLs where `Hash(URL) mod 100 = 17`.

2. **Permission Request:**
    - Crawler 17 needs to download a page from `example.com` estimated at 50 KB.
    - It sends a request to the coordinator: “May I download 50 KB from example.com?”

3. **Bandwidth Check:**
    - The coordinator checks that `example.com` has not exceeded 1 MB for the current minute.
    - If the current usage is 700 KB, then adding 50 KB (totaling 750 KB) is allowed.
    - The coordinator grants permission and updates the usage counter.

4. **Download Execution:**
    - Crawler 17 downloads the page.
    - Subsequent requests from any crawler for `example.com` are similarly checked until the 1 MB limit is reached.

5. **Periodic Reset:**
    - At the start of the next minute, the coordinator resets the usage for `example.com` and all other websites.

---

## **9. Summary**
This design for distributed throttling ensures that a fleet of crawlers can download pages across the web while respecting per-website bandwidth limits. By assigning URLs based on a hash function and coordinating downloads through a central (or distributed) rate limiter, the system prevents overloading web servers and efficiently manages crawling activities. The design balances simplicity, scalability, and fault tolerance to meet the operational constraints of large-scale web crawling.
