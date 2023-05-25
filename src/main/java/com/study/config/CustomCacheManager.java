package com.study.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class CustomCacheManager implements CacheManager{
	
	private CustomCache customCache;
	
	public CustomCacheManager()
	{
		this.customCache=new CustomCache();
		System.out.println("cacheManager");
	}

	@Override
	public Cache getCache(String name) {
		
		return new CustomCacheWrapper(name,customCache);
	}

	@Override
	public Collection<String> getCacheNames() {
		return Collections.singleton(customCache.getClass().getName());
	}

}
