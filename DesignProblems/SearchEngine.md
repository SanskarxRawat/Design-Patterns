# Design a Search Engine

We need to design a program that efficiently returns the subset of documents containing a given set of words from a collection of one million documents (average size 10 KB each) that fits entirely in the RAM of a single computer.

---

## **1. Clarify Requirements**
- **Functional Requirements:**
    - Accept a query containing one or more keywords.
    - Return all documents that contain *all* the specified keywords.
- **Non-Functional Requirements:**
    - Low latency: Query processing must complete in a few tens of milliseconds.
    - High throughput: Support for concurrent queries.
    - Efficient use of memory since the entire collection is stored in RAM.
- **Questions to Ask:**
    - Is the search case-sensitive?
    - Should we support stemming, stop-word removal, or other forms of normalization?
    - Is ranking required or is it enough to return all matching documents?
    - How often do documents get updated (impacting index maintenance)?

---

## **2. Key Components**
1. **Document Storage:**
    - Store the document text and metadata in memory.
2. **Inverted Index:**
    - A mapping from each keyword to the list of document IDs (postings list) that contain the keyword.
3. **Query Processor:**
    - Tokenizes and normalizes the query.
    - Retrieves postings lists for each keyword.
4. **Postings List Merger:**
    - Intersects the postings lists to find the set of documents that contain all query keywords.
5. **Result Formatter:**
    - Retrieves and formats the document details for output (optional ranking).

---

## **3. High-Level Design**
- **Input:** A query string containing one or more keywords.
- **Processing Pipeline:**
    1. **Preprocessing (Index Construction):**
        - Parse all one million documents.
        - Tokenize and normalize (e.g., lowercasing, removing punctuation, stop-word removal, stemming) each document.
        - Build an inverted index: For each unique word, store a postings list of document IDs where the word appears.
    2. **Query Processing:**
        - Parse and normalize the incoming query.
        - For each keyword, look up its postings list in the inverted index.
        - Intersect the postings lists to obtain the set of documents that contain all keywords.
    3. **Output Generation:**
        - Return the list of document IDs (or detailed document data) that match the query.
- **Output:** The subset of documents that contain all the query keywords.

---

## **4. Deep Dive into Critical Components**

### **4.1 Inverted Index Construction**
- **Process:**
    - Iterate over each document in the collection.
    - For each document:
        - Tokenize and normalize the text.
        - For every token, update the inverted index by appending the document ID to the token’s postings list.
- **Optimizations:**
    - Use efficient data structures like hash maps (e.g., `Map<string, List<int>>`) for keyword lookup.
    - Optionally, compress postings lists to save memory if needed.
    - Sort the postings lists to facilitate fast intersection operations.

### **4.2 Query Processing and Postings List Intersection**
- **Query Processing:**
    - Normalize the input query (e.g., lower case, stemming).
    - Retrieve the postings list for each keyword from the inverted index.
- **Intersection Strategy:**
    - If the query contains multiple keywords, perform an intersection of their postings lists.
    - Use efficient intersection algorithms (e.g., merge algorithm on sorted lists) to ensure low latency.
    - If one keyword has an extremely small postings list, start with that list to minimize work.

### **4.3 Data Structures and Performance Considerations**
- **In-Memory Index:**
    - The entire inverted index is maintained in RAM for fast access.
- **Efficiency:**
    - Hash maps provide O(1) average time lookup for each keyword.
    - Sorted arrays or lists for postings enable O(n) merging/intersection, where n is the size of the smallest postings list.
- **Memory Usage:**
    - Given 1 million documents averaging 10 KB each, the total document text size is about 10 GB. With an efficient index and normalization, the index should comfortably fit within available RAM on modern machines.

---

## **5. Scalability & Performance**
- **Latency:**
    - The in-memory inverted index allows keyword lookups and intersections to be performed in a few tens of milliseconds.
- **Throughput:**
    - Multi-threading or asynchronous processing can be used to handle multiple queries concurrently.
- **Optimizations:**
    - Cache frequently executed queries.
    - Precompute and store intermediate intersection results for common keyword combinations if needed.

---

## **6. Trade-offs**
- **Preprocessing vs. Query Time:**
    - A comprehensive index-building phase improves query time at the cost of initial preprocessing.
- **Memory vs. Speed:**
    - Storing the entire index in memory maximizes speed but requires sufficient RAM.
- **Ranking vs. Simplicity:**
    - Adding ranking to sort results by relevance increases complexity; for this design, returning all matching documents may suffice.

---

## **7. Failure Handling**
- **Index Inconsistencies:**
    - Periodically validate and rebuild the index, especially if documents are updated.
- **Query Timeouts:**
    - Monitor query performance and implement safeguards for excessively long-running queries.
- **Data Corruption:**
    - Use backups and logs to recover from memory or data corruption issues.

---

## **8. Real-World Examples**
- **Google Search:**
    - Uses a massive, distributed inverted index for keyword-based retrieval.
- **Elasticsearch/Solr:**
    - Popular search engines that implement similar in-memory indexing techniques for rapid querying.

---

## **Example Walkthrough**

### **Input:**
Query: `"machine learning algorithms"`

### **Processing Steps:**
1. **Preprocessing (already done):**
    - The inverted index contains postings lists for keywords like "machine", "learning", and "algorithms".
2. **Query Processing:**
    - Tokenize and normalize the query to: `["machine", "learning", "algorithms"]`.
    - Retrieve postings lists for each keyword:
        - "machine" → [Doc1, Doc3, Doc5, ...]
        - "learning" → [Doc2, Doc3, Doc7, ...]
        - "algorithms" → [Doc3, Doc8, Doc9, ...]
3. **Intersection:**
    - Intersect the postings lists to find the common document(s) present in all lists (e.g., Doc3).
4. **Output:**
    - Return the document(s) that contain all query keywords.

### **Output:**
```json
{
  "query": "machine learning algorithms",
  "results": [
    {"document_id": 3, "title": "An Introduction to Machine Learning Algorithms"},
    // ...other matching documents if applicable
  ]
}
