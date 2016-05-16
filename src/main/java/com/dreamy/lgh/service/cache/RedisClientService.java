package com.dreamy.lgh.service.cache;

import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作类
 * User: yujianfu
 * Date: 15/9/1
 * Time: 上午10:56
 */
@Component
public class RedisClientService {


    /**
     * string 操作
     */
    @Autowired
    @Qualifier("valueOperations")
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    @Qualifier("rawValueOperations")
    private ValueOperations<String, Object> rawValueOperations;

    /**
     * hash 操作
     */
    @Autowired
    private HashOperations<String, String, String> hashOperations;


    /**
     * set 操作
     */
    @Autowired
    private SetOperations<String, String> setOperations;


    /**
     * sorted set 操作
     */
    @Autowired
    @Qualifier("zSetOperations")
    private ZSetOperations<String, Object> zSetOperations;

    @Autowired
    private RedisTemplate redisTemplate;


    /*************************************key 操作******************************************/


    /**
     * @param keyFormat
     * @param keyValues
     */
    public void del(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.delete(key);
    }

    /**
     * 设置key的过期时间
     *
     * @param keyFormat
     * @param keyValues
     */
    public void expire(String keyFormat, Integer seconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在指定点时间点过期
     *
     * @param keyFormat
     * @param date
     * @param keyValues
     */
    public void expireAt(String keyFormat, Date date, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.expireAt(key, date);
    }


    /*************************************String 操作******************************************/

    /**
     * 指定key设置值
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     *
     * @param keyFormat
     * @param keyValues
     */
    public void set(String keyFormat, Object value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, value);
    }


    /**
     * 指定点key设置值,并且设置过期时间
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     *
     * @param keyFormat     KEY的格式如:KEY{ID}
     * @param keyValues     如：1234，最后生成：KEY1234
     * @param expireSeconds 失效时间
     * @param value         实际的值
     */
    public void set(String keyFormat, Object value, long expireSeconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, value, expireSeconds, TimeUnit.SECONDS);
    }


    /**
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     *
     * @param keyFormat
     * @param keyValues
     * @param <T>
     * @return
     */
    public <T> T get(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Object o = valueOperations.get(key);
        if (o != null) {
            return (T) o;
        }
        return null;
    }

    /**
     *计数器
     */

    /**
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     * 专用于计数器数值的获取
     *
     * @param keyFormat
     * @param keyValues
     * @return
     */
    public Long getNumber(String keyFormat, String... keyValues) {

        String key = format(keyFormat, keyValues);
        String result = (String) rawValueOperations.get(key);
        if (result != null) {
            return Long.valueOf(result);
        }
        return 0L;
    }


    /**
     * 计数器的初始化
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     *
     * @param keyFormat
     * @param keyValues
     */
    public void setNumber(String keyFormat, Long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        rawValueOperations.set(key, String.valueOf(value));
    }

    /**
     * 计数器的初始化
     * keyFormat KEY的格式如:KEY_{ID}_{USERID} 如果keyValues是 1234，5432 最后生成的cache key是：KEY_1234_5432
     *
     * @param keyFormat     KEY的格式如:KEY{ID}
     * @param keyValues     如：1234，最后生成：KEY1234
     * @param expireSeconds 失效时间
     * @param value         实际的值
     */
    public void setNumber(String keyFormat, Long value, long expireSeconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        rawValueOperations.set(key, value, expireSeconds, TimeUnit.SECONDS);
    }


    /**
     * 计数器，自增计数
     *
     * @param keyFormat
     * @param value
     * @param keyValues
     */
    public Long incrBy(String keyFormat, Long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return rawValueOperations.increment(key, value);
    }


    /**********************************set操作*******************************************/
    /**
     * 获取set中所有的集合
     *
     * @param keyFormat
     * @param keyValues
     * @return
     */
    public Set<String> sMembers(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Set<String> values = setOperations.members(key);
        return values;
    }

    /**
     * 查询key的数量
     *
     * @param keyFormat
     * @param keyValues
     * @return
     */
    public Long scard(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Long count = setOperations.size(key);
        if (count == null) {
            return 0l;
        }
        return count;
    }


    /**
     * 求所有所有key的并集
     *
     * @param keys
     * @return
     */
    public Set<String> sUnion(Set<String> keys) {
        String key = "";
        Set<String> values = setOperations.union(key, keys);
        return values;
    }

    /**
     * 求所有keys的并集，结果存入指定的key
     *
     * @param keys
     * @param resultKey
     * @return
     */
    public Long sUnionAndStore(Set<String> keys, String resultKey) {
        if (CollectionUtils.isEmpty(keys)) {
            return -1l;
        }

        String key = keys.iterator().next();
        keys.remove(key);

        Long result = setOperations.unionAndStore(key, keys, resultKey);
        return result;
    }


    /**
     * key1与key2的差集
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set<String> sDiff(String key1, String key2) {
        Set<String> values = setOperations.difference(key1, key2);
        return values;
    }

    /**
     * 向集合添加元素
     *
     * @param key1
     * @param value
     * @param keyValues
     * @return
     */
    public Long sAdd(String key1, String value, String... keyValues) {
        String key = format(key1, keyValues);
        Long result = setOperations.add(key, value);
        return result;
    }

    /**
     * 移除单个元素
     *
     * @param key1
     * @param value
     * @param keyValues
     * @return
     */
    public void sRem(String key1, String value, String... keyValues) {
        String key = format(key1, keyValues);
        setOperations.remove(key, value);
    }

    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param key1
     * @param keyValues
     * @return
     */
    public String sPop(String key1, String... keyValues) {
        String key = format(key1, keyValues);
        String result = setOperations.pop(key);
        return result;
    }


    /**
     * *******************************zset操作******************************************
     */
    public void zadd(String key1, Integer score, String member, String... keyValues) {
        String key = format(key1, keyValues);
        zSetOperations.add(key, member, score);
    }

    public Set<Object> zrange(String key, long start, long end) {
        return  zSetOperations.range(key, start, end);
    }

    public Set<Object> reverseZrange(String key, long start, long end) {
        return  zSetOperations.reverseRange(key, start, end);
    }


    /**
     * *******************************List操作******************************************
     */


    /*********************************hash操作******************************************/


    /**
     * 将HashMap中的Key对应的Value加上一个值
     *
     * @param keyFormat
     * @param field
     * @param value
     * @param keyValues
     */
    public void hIncrBy(String keyFormat, String field, long value,
                        String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.increment(key, field, value);
    }

    /**
     * 删除HashMap field字段
     *
     * @param keyFormat
     * @param field
     * @param keyValues
     */
    public void hDel(String keyFormat, String field, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.delete(key, field);
    }


    /**
     * 获取HashMap 所有值
     *
     * @param keyFormat
     * @param keyValues
     * @return
     */
    public Map<String, String> hGetAll(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Map<String, String> result = hashOperations.entries(key);
        return result;
    }

    /**
     * 获取hash中指定key 的值
     *
     * @param keyFormat
     * @param hashKey
     * @param keyValues
     * @return
     */
    public String hGet(String keyFormat, String hashKey, String... keyValues) {
        String key = format(keyFormat, keyValues);
        String result = hashOperations.get(key, hashKey);
        return result;
    }

    /**
     * @param keyFormat
     * @param hashKey
     * @param keyValues
     * @return
     */
    public void hPut(String keyFormat, String hashKey, String value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.put(key, hashKey, value);
    }


    /**
     * 格式化Key
     */
    public static String format(String formatKey, String... keyValues) {
        if (keyValues == null || keyValues.length == 0) {
            return formatKey;
        }
        StringBuilder key = new StringBuilder();
        char[] chars = formatKey.toCharArray();
        int index = -1;
        boolean inmark = false;
        boolean firstinmark = false;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '{') {
                index++;
                inmark = true;
                firstinmark = true;
            } else if (ch == '}') {
                inmark = false;
            } else if (inmark) {
                if (firstinmark) {
                    firstinmark = false;
                    key.append(keyValues[index]);
                }
            } else {
                key.append(chars[i]);
            }
        }
        return key.toString();
    }
}
