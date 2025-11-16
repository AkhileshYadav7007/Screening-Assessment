package com.assignment.screening.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {

		System.out.println("----- LRU Cache -----");
		Cache<String, String> lruCache = new Cache<>(3, new LruEvictionPolicy<>());

		lruCache.put("A", "Apple");
		lruCache.put("B", "Ball");
		lruCache.put("C", "Cat");

		lruCache.get("A");   // A becomes most recently used
		lruCache.put("D", "Dog"); // evicts B (LRU)

		System.out.println(lruCache);

		System.out.println("----- FIFO Cache -----");
		Cache<String, String> fifoCache = new Cache<>(3, new FifoEvictionPolicy<>());

		fifoCache.put("A", "Apple");
		fifoCache.put("B", "Ball");
		fifoCache.put("C", "Cat");

		fifoCache.put("D", "Dog"); // evicts A (FIFO)

		System.out.println(fifoCache);
	}
}

