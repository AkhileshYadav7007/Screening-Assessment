package com.assignment.screening.assignment;

class DLLNode<K> {
    K key;
    DLLNode<K> prev, next;

    DLLNode(K key) {
        this.key = key;
    }
}
