package zp.com.test;

import redis.clients.jedis.Jedis;


public class TestJedis {
	public static void main(String[] args){
		Jedis jedis = null;
		try{
            jedis = new Jedis("172.16.16.253", 6379);
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println("Result:" + value);
        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
        	jedis.close();
        }
	}
	
}
