# In-Memory Cache & Design Patterns Assignment

This repository contains the implementation of two software design tasks: an in-memory cache with pluggable eviction policies and a conceptual design for a document upload notification system using the Observer Pattern.

---

## Task 1 — In-Memory Cache with Pluggable Eviction Policies

### Overview

This task implements an in-memory cache system that supports different eviction policies. The cache provides two main operations:

*   `get(key)` — Retrieve a value if it is present in the cache.
*   `put(key, value)` — Insert or update a value for the given key.

When the cache reaches its maximum capacity, it evicts an entry based on the selected eviction policy to make space for the new entry.

### Supported Eviction Policies

1.  **LRU (Least Recently Used)**: Evicts the item that has not been accessed for the longest time.
2.  **FIFO (First-In First-Out)**: Evicts the item that has been in the cache for the longest time, regardless of access patterns.

The design follows the **Strategy Pattern**, which allows new eviction policies to be added in the future without modifying the core cache logic.

### Key Features

*   **O(1) average time complexity** for `get` and `put` operations.
*   Uses a `HashMap` for fast key-based lookups.
*   Uses a `Doubly Linked List` to maintain the order for the LRU policy.
*   Uses a `Queue` (specifically, a `LinkedList`) to maintain the insertion order for the FIFO policy.
*   The design is fully extensible and modular, making it easy to introduce new eviction strategies.

### How to Run

1.  Clone the repository.
2.  Compile the Java files:
    ```bash
    javac src/main/java/com/assignment/screening/assignment/*.java
    ```
3.  Run the demo from the `src/main/java` directory:
    ```bash
    java com.assignment.screening.assignment.Main
    ```

---

## 📚 Task 2 — Design Pattern: Observer Pattern

### Overview

For the second task, the **Observer Pattern** is used to design a notification system that alerts various modules whenever a new document is uploaded.

### Why Observer Pattern?

The Observer Pattern is ideal for this scenario because it:
*   Allows multiple, disparate modules (e.g., `Analytics`, `Alerts`, `Audit Logging`) to subscribe to events they are interested in.
*   Decouples the `DocumentUploader` (the subject) from its observers. The uploader does not need to have direct knowledge of each module's internal logic.
*   Makes it easy to add or remove modules (observers) at any time without modifying the uploader's code.
*   Promotes a loosely coupled and clean architecture, enhancing maintainability.

### Key Components (Conceptual)

*   **Subject (Publisher)**: `DocumentUploader` - This component is responsible for the core upload functionality and for notifying observers when an upload is complete.
*   **Observers**: `AnalyticsModule`, `AlertsModule`, `AuditLogModule` - These modules subscribe to the `DocumentUploader` and are notified upon a new document upload.
*   **Notification**: The `update()` method in each observer is triggered by the subject on every successful upload, allowing the observer to perform its specific action.
