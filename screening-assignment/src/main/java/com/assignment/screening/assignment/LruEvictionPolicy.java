package com.assignment.screening.assignment;

import java.util.HashMap;
import java.util.Map;

public class LruEvictionPolicy<K> implements EvictionPolicy<K> {

    private final Map<K, DLLNode<K>> nodeMap = new HashMap<>();
    private final DLLNode<K> head = new DLLNode<>(null); // dummy head
    private final DLLNode<K> tail = new DLLNode<>(null); // dummy tail

    public LruEvictionPolicy() {
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(DLLNode<K> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(DLLNode<K> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    @Override
    public void onGet(K key) {
        if (nodeMap.containsKey(key)) {
            DLLNode<K> node = nodeMap.get(key);
            removeNode(node);
            addToFront(node);
        }
    }

    @Override
    public void onPut(K key) {
        if (nodeMap.containsKey(key)) {
            DLLNode<K> node = nodeMap.get(key);
            removeNode(node);
            addToFront(node);
        } else {
            DLLNode<K> newNode = new DLLNode<>(key);
            nodeMap.put(key, newNode);
            addToFront(newNode);
        }
    }

    @Override
    public K evictKey() {
        DLLNode<K> lruNode = tail.prev;
        if (lruNode == head) return null;

        removeNode(lruNode);
        nodeMap.remove(lruNode.key);

        return lruNode.key;
    }
}
