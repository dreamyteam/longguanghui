package com.dreamy.lgh.service.cache;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Service
public class LocalCache implements CacheService {
    /**
     * 缓存
     */
    private static final Map<String, Object> MAP_DATA = new HashMap<String, Object>();//存放对应属性值

    @Override
    public Boolean put(String key, Object value) {
        MAP_DATA.put(key, value);
        return true;
    }

    @Override
    public void set(String key, Object value, int expire) {
        MAP_DATA.put(key,value);
    }

    @Override
    public Object get(String key) {
        return MAP_DATA.get(key);
    }

    @Override
    public Boolean remove(String key) {
        Object o = MAP_DATA.remove(key);
        return o != null;
    }

    @Override
    public Boolean contains(String key) {
        return MAP_DATA.containsKey(key);
    }

    @Override
    public synchronized Boolean putList(String key, Object data) {
        List<Object> obj = (List<Object>)MAP_DATA.get(key);
        if(obj == null) {
            obj = new ArrayList<Object>();
            MAP_DATA.put(key, obj);
        }
        return obj.add(data);
    }

    @Override
    public List<Object> getList(String key) throws Exception {
        return (List<Object>)MAP_DATA.get(key);
    }

    @Override
    public Boolean clearAll() {
        MAP_DATA.clear();
        return true;
    }
}
