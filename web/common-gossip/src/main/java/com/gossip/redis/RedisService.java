package com.gossip.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {
	@Autowired
	private ShardedJedisPool pool;
	//set方法无超时
	//set方法有超时
	//exists方法
	//get方法
	//del
	
	/**
	 * 键值形式存入redis
	 * @param key redis 键
	 * @param value redis 值
	 */
	public void set(String key,String value){
		ShardedJedis jedis = pool.getResource();
		jedis.set(key, value);
		pool.returnResource(jedis);
	}

	/**
	 * 
	 * @param key
	 * @return Boolean 判断键是否存在
	 */
	public Boolean exists(String key){
		ShardedJedis jedis = pool.getResource();
		Boolean exists = jedis.exists(key);
		pool.returnResource(jedis);
		return exists;
	}
	
	/**
	 * 
	 * @param key redis 键
	 * @return 根据键在redis获取值
	 */
	public String get(String key){
		ShardedJedis jedis = pool.getResource();
		String value = jedis.get(key);
		pool.returnResource(jedis);
		return value;
	}
	
	/**
	 * 删除键值
	 * @param key redis 键
	 */
	public void del(String key){
		ShardedJedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnResource(jedis);
	}
	
	
	
	
	
	
}
