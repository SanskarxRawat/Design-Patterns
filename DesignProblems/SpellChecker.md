# Design a Spell Checker

## **1. Clarify Requirements**
- **Functional Requirements**:
    - Detect misspelled words in a given text.
    - Suggest corrections for misspelled words.
- **Non-Functional Requirements**:
    - Low latency (e.g., < 100ms response time).
    - High accuracy in suggestions.
    - Scalable to handle large dictionaries and high request volumes.
- **Questions to Ask**:
    - What’s the size of the dictionary (e.g., 100K words, 1M words)?
    - Should it support multiple languages?
    - Is it for real-time typing (e.g., Google Docs) or batch processing (e.g., spell-checking a document)?

---

## **2. Key Components**
1. **Dictionary Storage**: Store valid words.
2. **Misspelling Detection**: Identify words not in the dictionary.
3. **Suggestion Generation**: Provide possible corrections for misspelled words.
4. **APIs**: Expose functionality to users (e.g., `POST /check`).

---

## **3. High-Level Design**
- **Input**: User submits text.
- **Processing**:
    - Tokenize text into words.
    - Check each word against the dictionary.
    - Generate suggestions for misspelled words.
- **Output**: List of misspelled words with suggestions.

---

## **4. Deep Dive into Critical Components**

### **4.1 Dictionary Storage**
- Use a **Trie (Prefix Tree)** for efficient word lookup and prefix-based searches.
    - **Advantages**: Fast lookup (O(L), where L = word length), supports prefix searches for suggestions.
    - **Alternative**: Hash table (O(1) lookup but no prefix support).
- For large dictionaries, store the Trie in memory (e.g., Redis) for fast access.

### **4.2 Misspelling Detection**
- For each word in the input text:
    - Check if it exists in the dictionary.
    - If not, flag it as misspelled.

### **4.3 Suggestion Generation**
- Use **Edit Distance (Levenshtein Distance)** to find words in the dictionary that are "close" to the misspelled word.
    - Edit distance = minimum number of operations (insert, delete, replace) to transform one word into another.
    - Example: "helo" → "hello" (edit distance = 1).
- **Optimizations**:
    - Limit search to words with edit distance ≤ 2 (most typos are within this range).
    - Use **BK-Tree** (Burkhard-Keller Tree) for efficient edit distance searches.
- **Rank Suggestions**:
    - Use frequency of words (e.g., "hello" is more common than "helo").
    - Use context (e.g., "their" vs. "there" based on sentence structure).

### **4.4 APIs**
- **POST /check**:
    - Input: `{ "text": "Ths is a smple txt." }`
    - Output:
      ```json
      {
        "misspelled": [
          { "word": "Ths", "suggestions": ["This", "Thus"] },
          { "word": "smple", "suggestions": ["simple", "sample"] },
          { "word": "txt", "suggestions": ["text", "txt"] }
        ]
      }
      ```

---

## **5. Scalability & Performance**
- **Dictionary Size**:
    - Use a distributed Trie or sharded hash table for large dictionaries.
    - Compress the Trie (e.g., Radix Tree) to reduce memory usage.
- **Latency**:
    - Cache frequently requested words and suggestions (e.g., Redis).
    - Use precomputed suggestions for common typos.
- **Load Handling**:
    - Use a load balancer to distribute requests across multiple spell-checker servers.

---

## **6. Trade-offs**
- **Accuracy vs. Speed**:
    - Higher edit distance thresholds improve accuracy but increase computation time.
- **Memory vs. Disk**:
    - Storing the dictionary in memory improves speed but increases cost.

---

## **7. Failure Handling**
- **Dictionary Unavailable**:
    - Fallback to a smaller, local dictionary.
- **High Load**:
    - Rate limit requests or queue them for batch processing.

---

## **8. Real-World Examples**
- **Google Docs**: Real-time spell-checking with context-aware suggestions.
- **Grammarly**: Advanced spell-checking with grammar and style corrections.

---

## **Example Walkthrough**
### Input:
`"Ths is a smple txt."`

### Steps:
1. Tokenize: `["Ths", "is", "a", "smple", "txt"]`.
2. Check each word:
    - "Ths" → Not in dictionary → Misspelled.
    - "is" → In dictionary → Correct.
    - "a" → In dictionary → Correct.
    - "smple" → Not in dictionary → Misspelled.
    - "txt" → Not in dictionary → Misspelled.
3. Generate suggestions:
    - "Ths" → ["This", "Thus"].
    - "smple" → ["simple", "sample"].
    - "txt" → ["text", "txt"].

### Output:
```json
{
  "misspelled": [
    { "word": "Ths", "suggestions": ["This", "Thus"] },
    { "word": "smple", "suggestions": ["simple", "sample"] },
    { "word": "txt", "suggestions": ["text", "txt"] }
  ]
}