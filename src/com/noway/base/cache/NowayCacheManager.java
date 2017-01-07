package com.noway.base.cache;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;

public class NowayCacheManager {

	static Logger log = Logger.getLogger(NowayCacheManager.class);

	private NowayCacheManager() {
	}

	private static class Inner { // 私有的静态内部类
		static CacheManager cacheManager = CacheManager.getInstance();
	}

	public static CacheManager getInstance() {
		return Inner.cacheManager;
	}

}