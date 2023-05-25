package com.study.config;

import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class CustomCacheWrapper implements Cache {

	private final String name;
	private final CustomCache customCache;

	public CustomCacheWrapper(String name, CustomCache customCache) {
		super();
		this.name = name;
		this.customCache = customCache;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return customCache;
	}

	@Override
	public ValueWrapper get(Object key) {
		Object value = customCache.get(key);
		return (value != null ? new SimpleValueWrapper(value) : null);
	}

	@Override
	public void put(Object key, Object value) {
		customCache.put(key.toString(), value);
	}
	

	@Override
	public void evict(Object key) {
		customCache.remove(key.toString());
	}

	@Override
	public void clear() {
		customCache.clear();
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
