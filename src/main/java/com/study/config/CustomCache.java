package com.study.config;

import java.util.HashMap;
import java.util.Map;

public class CustomCache {

//	private final Map<String, Object> cache = new HashMap<>();
//
//	public void put(String key, Object value) {
//		cache.put(key, value);
//	}
//
//	public Object get(Object key) {
//
//		return cache.get(key.toString());
//	}
//	public void remove(Object key) {
//		cache.remove(key);
//	}
//
//	public void clear() {
//		cache.clear();
//	}
	private final Map<Integer, Object> cache = new HashMap<>();

	public void put(Object key, Object value) {
		cache.put((Integer) key, value);
	}

	public Object get(Object key) {

		return cache.get(key);
	}
	public void remove(Object key) {
		cache.remove(key);
	}

	public void clear() {
		cache.clear();
	}
	

	

}
