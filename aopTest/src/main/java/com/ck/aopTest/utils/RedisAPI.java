package com.ck.aopTest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * redis数据库链接工具
 * 
 * @see:
 * @Company:江苏鸿信系统集成有限公司微信开发组
 * @author 杨坚
 * @Time 2016年11月24日
 * @version 1.0v
 */
public final class RedisAPI {

	// 可用连接实例的最大数目，默认值为8；
	private static int MAX_TOTAL = 0;

	// 控制一个jedisPool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 0;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 0;

	private static int TIMEOUT = 0;
	// 获取连接是否校验可用
	private static boolean TEST_ON_BORROW = false;
	// 主1
	private static JedisPool jedisPoolMasterOne = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {

			TEST_ON_BORROW = Boolean.valueOf(CommonUtil.printPlatformProperties("redis_test_on_borrow"));
			TIMEOUT = Integer.parseInt(CommonUtil.printPlatformProperties("redis_timeout"));
			MAX_WAIT = Integer.parseInt(CommonUtil.printPlatformProperties("redis_max_wait"));
			MAX_IDLE = Integer.parseInt(CommonUtil.printPlatformProperties("redis_max_idle"));
			MAX_TOTAL = Integer.parseInt(CommonUtil.printPlatformProperties("redis_max_total"));
			List<String> redisList = new ArrayList<String>();
			JSONObject redisJson = new JSONObject();
			redisList = new ArrayList<String>();
			redisJson = new JSONObject();
			redisList.add("master_ip");
			redisList.add("master_port");
			redisList.add("master_auth");
			redisList.add("master_data");
			// 获取列表集合
			redisJson = CommonUtil.printPlatformPropertiesList(redisList);
			// redis数据处理
			JedisPoolConfig configMasterOne = new JedisPoolConfig();
			configMasterOne.setMaxTotal(MAX_TOTAL);
			configMasterOne.setMaxIdle(MAX_IDLE);
			configMasterOne.setMaxWaitMillis(MAX_WAIT);
			configMasterOne.setTestOnBorrow(TEST_ON_BORROW);
			jedisPoolMasterOne = new JedisPool(configMasterOne, redisJson.getString("master_ip"),
					redisJson.getInt("master_port"), TIMEOUT, redisJson.getString("master_auth"),
					redisJson.getInt("master_data") == 0 ? 0 : redisJson.getInt("master_data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择主数据库连接池 使用完立即将连接释放到连接池(写操作)
	 * 
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static Jedis masterChooseConnect() {
		return jedisPoolMasterOne.getResource();
	}

	/**
	 * 获取map形式长度
	 * 
	 * @param key
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static long getMapLen(String key) {
		Jedis jedis = null;
		try {
			jedis = masterChooseConnect();
			return jedis.hlen(key);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
			return 0;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取map形式的键
	 * 
	 * @param key
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static Set<String> getMapKey(String key) {
		Jedis jedis = null;
		try {
			jedis = masterChooseConnect();
			return jedis.hkeys(key);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
			return null;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取map形式的值
	 * 
	 * @param key
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static List<String> getMapValue(String key) {
		Jedis jedis = null;
		try {
			jedis = masterChooseConnect();
			return jedis.hvals(key);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
			return null;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 根据键获取信息
	 * 
	 * @param key
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月17日
	 * @version 1.0v
	 */
	public static String getKeyStr(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = masterChooseConnect();
			value = jedis.get(key);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
		} finally {
			closeJedis(jedis);
		}
		return value;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static void closeJedis(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 查询map形式中的键值对
	 * 
	 * @param key
	 *            缓存数据库中的key
	 * @param paramKey
	 *            map中的key
	 * @return
	 * @author think
	 * @Time 2017/7/3
	 * @version 1.0v
	 */
	public static String getMapKeyValue(String key, String paramKey) {
		Jedis jedis = null;
		try {
			jedis = masterChooseConnect();
			return jedis.hget(key, paramKey);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
			return null;
		} finally {
			closeJedis(jedis);
		}
	}
	
	/**
	 * 模糊获取map形式的键
	 * 
	 * @param key
	 * @return
	 * @author 杨坚
	 * @Time 2016年11月24日
	 * @version 1.0v
	 */
	public static Set<String> getMapKeys(String keypattern) {
		Jedis jedis = null;
		try {
			jedis = masterChooseConnect();
			return jedis.keys(keypattern);
		} catch (Exception e) {
			closeJedis(jedis);
			e.printStackTrace();
			return null;
		} finally {
			closeJedis(jedis);
		}
	}
}