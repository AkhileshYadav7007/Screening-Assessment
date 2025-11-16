package com.assignment.screening.assignment;

public interface EvictionPolicy<K> {
    void onGet(K key);
    void onPut(K key);
    K evictKey();
}
