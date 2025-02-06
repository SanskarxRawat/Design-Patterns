# Design a Plagiarism Detector

## **1. Clarify Requirements**
- **Functional Requirements**:
    - Detect exact and partial matches between a submitted document and existing sources.
    - Highlight matched sections and provide source references.
    - Support multiple document formats (e.g., PDF, DOCX, plain text).
- **Non-Functional Requirements**:
    - High accuracy in detecting paraphrased and rephrased content.
    - Low latency (e.g., < 1 second for short texts).
    - Scalable to handle millions of documents.
- **Questions to Ask**:
    - Is the comparison against a private database or the entire web?
    - What’s the minimum match threshold (e.g., 5%, 20%) to flag plagiarism?
    - Should it handle multiple languages?

---

## **2. Key Components**
1. **Document Ingestion**: Parse and store documents in a searchable format.
2. **Text Normalization**: Preprocess text (e.g., remove stop words, stemming).
3. **Fingerprinting**: Generate unique representations of text chunks.
4. **Similarity Detection**: Compare fingerprints against a database.
5. **APIs**: Expose endpoints for submission and results (e.g., `POST /check`).

---

## **3. High-Level Design**
- **Input**: User submits a document.
- **Processing**:
    - Normalize text (tokenization, stemming, remove punctuation).
    - Generate fingerprints (e.g., hashes, n-grams).
    - Query the database for matches.
- **Output**: Report with matched sections, similarity score, and sources.

---

## **4. Deep Dive into Critical Components**

### **4.1 Document Ingestion**
- **Parsing**:
    - Use libraries like Apache Tika to extract text from PDFs, DOCX, etc.
- **Storage**:
    - Store raw text and metadata (e.g., author, timestamp) in a database (e.g., Elasticsearch for fast text search).

### **4.2 Text Normalization**
- **Steps**:
    1. Tokenize text into words.
    2. Remove stop words (e.g., "the", "is").
    3. Apply stemming (e.g., "running" → "run").
    4. Convert to lowercase.

### **4.3 Fingerprinting**
- **Techniques**:
    - **Exact Matches**: Use cryptographic hashes (e.g., SHA-256) for identical text chunks.
    - **Partial Matches**:
        - **Shingling**: Split text into overlapping n-grams (e.g., 5-word sequences).
        - **MinHash**: Generate compact signatures to estimate similarity between sets.
        - **Locality-Sensitive Hashing (LSH)**: Bucket similar documents for fast lookups.

### **4.4 Similarity Detection**
- **Algorithms**:
    - **Cosine Similarity**: Compare TF-IDF vectors of documents.
    - **Jaccard Index**: Measure overlap between shingled sets.
    - **Edit Distance**: Detect rephrased content (computationally expensive).
- **Optimizations**:
    - Use inverted indexes to map fingerprints to documents.
    - Prioritize recent or frequently matched documents.

### **4.5 APIs**
- **POST /check**:
    - Input: `{ "document": "Sample text...", "format": "txt" }`
    - Output:
      ```json
      {
        "similarity_score": 0.35,
        "matches": [
          {
            "source_doc_id": "doc_123",
            "matched_text": "Sample text...",
            "source_url": "https://example.com/doc_123"
          }
        ]
      }
      ```

---

## **5. Scalability & Performance**
- **Large Document Databases**:
    - Shard the database by fingerprint ranges (e.g., hash-based sharding).
    - Use distributed systems like Apache Spark for batch processing.
- **Latency**:
    - Cache frequently queried fingerprints (e.g., Redis).
    - Precompute LSH buckets for common n-grams.
- **Load Handling**:
    - Deploy stateless workers behind a load balancer for parallel processing.

---

## **6. Trade-offs**
- **Precision vs. Recall**:
    - Lowering the similarity threshold increases recall but adds false positives.
- **Storage vs. Speed**:
    - Storing more fingerprints improves accuracy but increases storage costs.
- **Multilingual Support**:
    - Language-specific normalization increases complexity.

---

## **7. Failure Handling**
- **Database Downtime**:
    - Use replicas and fallback to a reduced-accuracy mode (e.g., skip partial matches).
- **High Traffic**:
    - Queue requests and process them asynchronously.

---

## **8. Real-World Examples**
- **Turnitin**: Uses fingerprinting and pattern matching against academic papers.
- **Copyscape**: Web-focused plagiarism detection for digital content.
- **Google Scholar**: Checks for duplicate research papers.

---

## **Example Walkthrough**
### Input:
**Document**: "Machine learning is the science of getting computers to act without being explicitly programmed."

### Steps:
1. **Normalize**: "machine learn scienc comput act explicit program".
2. **Shingling (3-grams)**: ["machine learn scienc", "learn scienc comput", ...].
3. **Fingerprinting**: Generate MinHash signatures for each shingle.
4. **Query Database**: Find overlapping signatures with existing documents.
5. **Match Found**: A research paper with 90% similarity to the input text.

### Output:
```json
{
  "similarity_score": 0.9,
  "matches": [
    {
      "source_doc_id": "paper_456",
      "matched_text": "Machine learning is the science...",
      "source_url": "https://arxiv.org/paper_456"
    }
  ]
}