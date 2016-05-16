package com.dreamy.lgh.service.cache;

import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Service
public class RedisCache implements CacheService {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);


    @Autowired
    private ValueOperations<String, Object> valueOperations;


    @Override
    public Boolean put(String key, Object o) {
        if (StringUtils.isNotEmpty(key) && o != null) {
            try {
                valueOperations.set(key, o.toString());
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public void set(String key, Object value, int timeout) {
        valueOperations.set(key, value, timeout, TimeUnit.SECONDS);

    }

    @Override
    public Object get(String key) throws Exception {
        if (StringUtils.isNotEmpty(key)) {
            try {
                Object s = valueOperations.get(key);
                if (s == null) {
                    s= valueOperations.get(key);
                }
                return s;
            } catch (Throwable e) {
                log.error("get usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public Boolean remove(String key) throws Exception {
        valueOperations.getOperations().delete(key);
        return true;

    }

    @Override
    public Boolean contains(String key) throws Exception {
        return  valueOperations.getOperations().hasKey(key);

    }

    @Override
    public Boolean putList(String key, Object data) throws Exception {

        return true;

    }

    @Override
    public List<Object> getList(String key) throws Exception {
        return null;
    }

    @Override
    public Boolean clearAll() throws Exception {
        return true;
    }

    public void clear(String key){
        if (StringUtils.isNotEmpty(key)) {
            try {
                valueOperations.getOperations().delete(key);
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
    }
}
