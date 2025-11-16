package com.assignment.screening.assignment;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {

    private final int capacity;
    private final EvictionPolicy<K> policy;
    private final Map<K, V> map;

    public Cache(int capacity, EvictionPolicy<K> policy) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        this.policy = policy;
        this.map = new HashMap<>();
    }

    public void get(K key) {
        if (!map.containsKey(key)) return;

        policy.onGet(key);
        map.get(key);
    }

    public void put(K key, V value) {
        if (!map.containsKey(key) && map.size() == capacity) {
            K evictedKey = policy.evictKey();
            if (evictedKey != null) {
                map.remove(evictedKey);
            }
        }
        map.put(key, value);
        policy.onPut(key);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}