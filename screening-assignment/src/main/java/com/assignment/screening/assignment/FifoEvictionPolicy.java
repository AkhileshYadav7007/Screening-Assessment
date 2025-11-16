package com.assignment.screening.assignment;

import java.util.LinkedList;
import java.util.Queue;

public class FifoEvictionPolicy<K> implements EvictionPolicy<K> {

    private final Queue<K> queue = new LinkedList<>();

    @Override
    public void onGet(K key) {
        // FIFO does not reorder on get
    }

    @Override
    public void onPut(K key) {
        queue.add(key);
    }

    @Override
    public K evictKey() {
        return queue.poll();
    }
}