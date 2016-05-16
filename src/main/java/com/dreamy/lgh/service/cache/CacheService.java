package com.dreamy.lgh.service.cache;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/1.
 */
public interface CacheService {
    /**
     * 保存缓存
     * @param key
     * @param o
     * @return
     */
    public Boolean put(String key, Object o);

    /**
     * 设置缓存值
     *
     * @param key
     * @param value  缓存值
     * @param expire 失效时间(单位秒)
     */
    public void set(String key, Object value, int expire);

    /**
     * 获取缓存
     * @return
     */
    public Object get(String key) throws Exception;
    /**
     * 删除缓存
     * @param key
     * @return
     */
    public Boolean remove(String key) throws Exception;
    /**
     * 键是否存在
     * @param key
     * @return
     */
    public Boolean contains(String key) throws Exception;
    /**
     * 添加到list中
     * @param key
     * @param data
     * @return
     */
    public Boolean putList(String key, Object data) throws Exception;
    /**
     * 获取list
     * @param key
     * @return
     * @throws Exception
     */
    public List<Object> getList(String key) throws Exception;
    /**
     * 删除全部
     * @return
     */
    public Boolean clearAll() throws Exception;
}
