package com.util.辅助类;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * Client的使用方法
 * This is a Memcached client for the Java platform available from
 *  <a href="http:/www.danga.com/memcached/">http://www.danga.com/memcached/</a>.
 * <br/> 
 * Supports setting, adding, replacing, deleting compressed/uncompressed and<br/>
 * serialized (can be stored as string if object is native class) objects to memcached.<br/>
 * <br/>
 * Now pulls SockIO objects from SockIOPool, which is a connection pool.  The server failover<br/>
 * has also been moved into the SockIOPool class.<br/>
 * This pool needs to be initialized prior to the client working.  See javadocs from SockIOPool.<br/>
 * <br/>
 * Some examples of use follow.<br/>
 * <h3>To create cache client object and set params:</h3>
 * <pre> 
 *	MemcachedClient mc = new MemcachedClient();
 *
 *	// compression is enabled by default	
 *	mc.setCompressEnable(true);
 *
 *	// set compression threshhold to 4 KB (default: 15 KB)	
 *	mc.setCompressThreshold(4096);
 *
 *	// turn on storing primitive types as a string representation
 *	// Should not do this in most cases.	
 *	mc.setPrimitiveAsString(true);
 * </pre>	
 * <h3>To store an object:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "cacheKey1";	
 *	Object value = SomeClass.getObject();	
 *	mc.set(key, value);
 * </pre> 
 * <h3>To store an object using a custom server hashCode:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "cacheKey1";	
 *	Object value = SomeClass.getObject();	
 *	Integer hash = new Integer(45);	
 *	mc.set(key, value, hash);
 * </pre> 
 * The set method shown above will always set the object in the cache.<br/>
 * The add and replace methods do the same, but with a slight difference.<br/>
 * <ul>
 * 	<li>add -- will store the object only if the server does not have an entry for this key</li>
 * 	<li>replace -- will store the object only if the server already has an entry for this key</li>
 * </ul> 
 * <h3>To delete a cache entry:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "cacheKey1";	
 *	mc.delete(key);
 * </pre> 
 * <h3>To delete a cache entry using a custom hash code:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "cacheKey1";	
 *	Integer hash = new Integer(45);	
 *	mc.delete(key, hashCode);
 * </pre> 
 * <h3>To store a counter and then increment or decrement that counter:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "counterKey";	
 *	mc.storeCounter(key, new Integer(100));
 *	System.out.println("counter after adding      1: " mc.incr(key));	
 *	System.out.println("counter after adding      5: " mc.incr(key, 5));	
 *	System.out.println("counter after subtracting 4: " mc.decr(key, 4));	
 *	System.out.println("counter after subtracting 1: " mc.decr(key));	
 * </pre> 
 * <h3>To store a counter and then increment or decrement that counter with custom hash:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "counterKey";	
 *	Integer hash = new Integer(45);	
 *	mc.storeCounter(key, new Integer(100), hash);
 *	System.out.println("counter after adding      1: " mc.incr(key, 1, hash));	
 *	System.out.println("counter after adding      5: " mc.incr(key, 5, hash));	
 *	System.out.println("counter after subtracting 4: " mc.decr(key, 4, hash));	
 *	System.out.println("counter after subtracting 1: " mc.decr(key, 1, hash));	
 * </pre> 
 * <h3>To retrieve an object from the cache:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "key";	
 *	Object value = mc.get(key);	
 * </pre> 
 * <h3>To retrieve an object from the cache with custom hash:</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String key   = "key";	
 *	Integer hash = new Integer(45);	
 *	Object value = mc.get(key, hash);	
 * </pre> 
 * <h3>To retrieve an multiple objects from the cache</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String[] keys      = { "key", "key1", "key2" };
 *	Map&lt;Object&gt; values = mc.getMulti(keys);
 * </pre> 
 * <h3>To retrieve an multiple objects from the cache with custom hashing</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	String[] keys      = { "key", "key1", "key2" };
 *	Integer[] hashes   = { new Integer(45), new Integer(32), new Integer(44) };
 *	Map&lt;Object&gt; values = mc.getMulti(keys, hashes);
 * </pre> 
 * <h3>To flush all items in server(s)</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	mc.flushAll();
 * </pre> 
 * <h3>To get stats from server(s)</h3>
 * <pre>
 *	MemcachedClient mc = new MemcachedClient();
 *	Map stats = mc.stats();
 * </pre> 
 *
 * @author greg whalin <greg@meetup.com> 
 * @author Richard 'toast' Russo <russor@msoe.edu>
 * @author Kevin Burton <burton@peerfear.org>
 * @author Robert Watts <robert@wattsit.co.uk>
 * @author Vin Chawla <vin@tivo.com>
 * @version 1.5
 */
public class MemcachedUtil {
	
	private static final Log log = Logs.get();

	private SockIOPool pool = null;
	private MemCachedClient client = null;
	private static MemcachedUtil instance = null; 
	private MemcachedUtil(){};
	
	static {
		instance = new MemcachedUtil();
		instance.poolStartup();
	}
	
	public static MemcachedUtil getInstance(){
		return instance;
	}
	
	private void poolStartup(){
		
		if(log.isDebugEnabled()){
			log.debugf("Memcached Server IP: %s", Json.toJson(C.MEMCACHED_HOSTS));
			
		}
		
		Integer[] weights   = new Integer[C.MEMCACHED_WEIGHTS.length];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = Integer.parseInt(C.MEMCACHED_WEIGHTS[i]);
		}
		
//		高级设置
		int initialConnections  = 5;
		int minSpareConnections = 5;
		int maxSpareConnections = 50;	
		long maxIdleTime        = 1000 * 60 * 30;	// 30 minutes
		long maxBusyTime        = 1000 * 60 * 5;	// 5 minutes
		long maintThreadSleep   = 1000 * 5;			// 5 seconds
		int	socketTimeOut       = 1000 * 3;			// 3 seconds to block on reads
		int	socketConnectTO     = 1000 * 3;			// 3 seconds to block on initial connections.  If 0, then will use blocking connect (default)
		boolean failover        = false;			// turn off auto-failover in event of server down	
		boolean nagleAlg        = false;			// turn off Nagle's algorithm on all sockets in pool	
		boolean aliveCheck      = false;			// disable health check of socket on checkout

		pool = SockIOPool.getInstance();
		pool.setServers(C.MEMCACHED_HOSTS);
		pool.setWeights(weights);
		
//		高级设置
		pool.setInitConn( initialConnections );
		pool.setMinConn( minSpareConnections );
		pool.setMaxConn( maxSpareConnections );
		pool.setMaxIdle( maxIdleTime );
		pool.setMaxBusyTime( maxBusyTime );
		pool.setMaintSleep( maintThreadSleep );
		pool.setSocketTO( socketTimeOut );
		pool.setNagle( nagleAlg );	
		pool.setHashingAlg( SockIOPool.NEW_COMPAT_HASH );
		pool.setAliveCheck( true );
		
		pool.initialize();
		
		client = new MemCachedClient();
	}
	
	public void poolShutdown(){
		pool.shutDown();
	}
	
	/**
	 * 
	 * @param key
	 * @param obj
	 * @param date 失效日期
	 */
	public MemCachedClient client(){
		return client;
	}
	
	
	
}
