# Design a Mileage Run Finder System

Airlines award frequent flyers with “status” based on miles flown over a period (typically 12 months). Some travelers take additional flights—so-called “mileage runs”—to maintain or upgrade their status at the lowest possible cost per mile (CPM). This system helps users find and compare round-trip flights (or multi-leg itineraries) that minimize the ratio of dollars spent to miles flown.

---

## **1. Clarify Requirements**
- **Functional Requirements:**
    - **Input:**
        - User-specified parameters (departure city, travel dates, preferences such as layovers, budget, etc.).
    - **Output:**
        - A list of candidate mileage runs, ranked by cost-per-mile (CPM), along with itinerary details.
    - **Core Features:**
        - Retrieve flight options and pricing data from various airlines and travel aggregators.
        - Calculate total miles flown for a given itinerary.
        - Compute CPM by dividing total ticket cost by total miles flown.
        - Allow filtering and sorting based on user preferences (e.g., shortest travel time, least layovers, best CPM).

- **Non-Functional Requirements:**
    - **Scalability:** Able to process many flight itineraries and pricing data, potentially from multiple data sources.
    - **Responsiveness:** Provide results quickly despite multiple external API calls and cost calculations.
    - **Extensibility:** Ability to add more features (e.g., historical trends, dynamic pricing alerts) over time.
    - **Accuracy:** Reliable computation of distances (using great circle distance) and up-to-date pricing information.

- **Key Questions:**
    - Should the system search only for round-trip flights or include multi-leg itineraries?
    - How frequently is flight pricing updated, and how should the system handle caching or real-time queries?
    - Are there any additional user constraints (preferred airlines, flight times, or layover limits)?

---

## **2. Partitioning Features into Independent Tasks**
To design a scalable system, partition the overall functionality into distinct components:

1. **Flight Data Aggregation:**
    - **Task:** Gather flight itineraries and pricing data from multiple sources (airline APIs, travel aggregators).
    - **Independence:** Can operate in parallel with data collection and pre-processing.
    - **Considerations:** API rate limits, data normalization, caching of flight data.

2. **Distance Calculation:**
    - **Task:** Calculate the distance (in miles) for each itinerary.
    - **Method:** Use great-circle distance between airport coordinates; sum distances over multiple legs if necessary.
    - **Independence:** A pure computation module, receiving flight leg data and outputting mileage.

3. **Cost-Per-Mile Computation:**
    - **Task:** Compute CPM for each itinerary by dividing total cost by total miles flown.
    - **Independence:** Can be integrated as part of the ranking engine.
    - **Considerations:** Currency conversion, handling of fees, taxes, etc.

4. **Itinerary Ranking and Filtering:**
    - **Task:** Rank itineraries by CPM (and optionally other factors such as total travel time, convenience, etc.).
    - **Independence:** A separate service that applies business logic for filtering and sorting results.
    - **Considerations:** User customization of ranking preferences.

5. **User Interface and API Layer:**
    - **Task:** Provide endpoints for users to input preferences and view ranked mileage run options.
    - **Independence:** Separate from backend processing; can be scaled independently.
    - **Considerations:** UX design to present detailed flight itineraries and computed CPM clearly.

6. **Caching and Data Refresh:**
    - **Task:** Cache recent flight data and computed results to minimize repeated external API calls.
    - **Independence:** Acts as a middleware between flight data aggregation and CPM computation.
    - **Considerations:** Cache invalidation strategy, data freshness guarantees.

---

## **3. High-Level System Architecture**
### **Data Flow:**
1. **User Input:**
    - User provides departure location, travel dates, and any specific constraints or preferences via a web/mobile interface.

2. **Flight Data Aggregation Service:**
    - Collects flight options and pricing from various sources.
    - Normalizes and stores data temporarily in a cache or database.

3. **Distance & CPM Calculation Module:**
    - For each flight itinerary, calculates the total miles flown.
    - Computes the CPM using total cost and total mileage.
    - Augments the flight data with these computed metrics.

4. **Ranking & Filtering Service:**
    - Applies user-defined filters and ranks itineraries by CPM (and other optional criteria).
    - Generates a sorted list of candidate mileage runs.

5. **User API/Interface:**
    - Delivers the ranked results to the user for review and booking decisions.

### **Component Interaction:**
- **Microservices Architecture:**
    - Each of the tasks (data aggregation, computation, ranking) can be implemented as independent microservices.
    - Communication via lightweight RESTful APIs or message queues.

- **Distributed Processing:**
    - Use parallel processing to handle the large number of potential itineraries.
    - Utilize cloud-based resources to scale processing on-demand.

---

## **4. Detailed Considerations**
### **Distance Calculation:**
- **Algorithm:**
    - Use the Haversine formula for calculating the great-circle distance between two points (airports).
- **Edge Cases:**
    - Account for multi-leg flights, connecting routes, and potential detours.

### **Cost-Per-Mile Computation:**
- **Formula:**
    - CPM = Total Cost (in USD) / Total Miles Flown.
- **Data Normalization:**
    - Ensure that all cost figures are in a consistent currency.
    - Optionally include fees and taxes for a more comprehensive calculation.

### **Ranking and User Preferences:**
- **User Customization:**
    - Allow users to adjust the weight given to factors other than CPM (e.g., total travel time, number of layovers).
- **Approximate Highest Priority:**
    - Since the absolute best mileage run might not always be essential, support “near-optimal” results to improve response time.

### **Handling Scale:**
- **Data Volume:**
    - Flight data and itineraries may be in the millions or billions; partition the data and process in batches.
- **Distributed Caching:**
    - Use a distributed cache (e.g., Redis) to reduce API call overhead and speed up repetitive queries.

---

## **5. Trade-Offs**
- **Precision vs. Compute Time:**
    - More accurate distance and cost calculations may increase compute time. Consider using approximate methods if a slight loss of precision is acceptable.
- **Global Optimality vs. Local Heuristics:**
    - It may not always be essential to operate on the absolute lowest CPM option if near-optimal choices are sufficient for maintaining status.
- **Centralized Coordination vs. Distributed Processing:**
    - A centralized service for ranking may simplify global ordering but could become a bottleneck; a distributed approach improves scalability but increases system complexity.

---

## **6. Summary**
The Mileage Run Finder system helps frequent flyers identify round-trip or multi-leg itineraries that minimize cost per mile, ensuring they can maintain or upgrade their status cost-effectively. The system is divided into independent tasks: aggregating flight data, computing distances and CPM, and ranking/filtering itineraries based on user preferences. By leveraging distributed processing, caching, and scalable microservices, the design meets the challenges of handling billions of itineraries while minimizing compute time and providing high-quality results.
