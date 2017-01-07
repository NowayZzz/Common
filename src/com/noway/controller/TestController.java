package com.noway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noway.base.cache.service.ICache;
import com.noway.base.cache.service.impl.EhcacheCacheImpl;
import com.noway.dwr.NowayDwr;
import com.noway.socket.NowaySocket;

@Controller
@RequestMapping("/test")
public class TestController  {

	ICache ehcacheCacheImpl = new EhcacheCacheImpl("testCache");;
	
	@RequestMapping(value = "/cache/insert")
	public void cacheInsert() {
		try {
			ehcacheCacheImpl.put("noway", "奈文摩尔");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/cache/get")
	public void cacheGet(String cacheId) throws Exception {
		System.out.println(ehcacheCacheImpl.get(cacheId));
	}
	
	@RequestMapping(value = "/cache/remove")
	public void cacheRemove(String cacheId) {
		ehcacheCacheImpl.remove(cacheId);
	}
	
	@RequestMapping(value = "/dwr/pushmsg")
	public void pushmsg() {
		NowayDwr.pushMessage("pushMessage", "我擦,hehehe!");
	}
	
	@RequestMapping(value = "/socket/start")
	public void startSocket() {
		Thread thread = new Thread(new NowaySocket());
		thread.start();
	}

	
	
}