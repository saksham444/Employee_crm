package com.birdeye.employee_details;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class LRUCache {
	 
    // store keys of cache
    private Deque<Integer> doublyQueue;
 
    // store references of key in cache
    private HashSet<Integer> hashSet;
 
    // maximum capacity of cache 
    private final int CACHE_SIZE;
 
    LRUCache(int capacity) {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }
 
    /* Refer the page within the LRU cache */
    public void refer(int page) {
        if (!hashSet.contains(page)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                int last = doublyQueue.removeLast();
                hashSet.remove(last);
            } 
        } 
        else {/* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            doublyQueue.remove(page);
        } 
        doublyQueue.push(page);
        hashSet.add(page);
    }
}
