package com.dreamy.lgh.domain;

import java.util.Date;

/**
 * Created by wangyongxing on 16/3/31.
 */
public abstract class BaseDomain<T>  {
    /**
     * 数据库主键id获取
     *
     * @return
     */
    public abstract T getId();

    /**
     * 设置数据库主键id
     *
     * @param id
     */
    public abstract void setId(T id);

    /**
     * 设置创建时间
     *
     * @param createdAt
     */
    public abstract void setCreatedAt(Date createdAt);

    /**
     * 设置修改时间
     *
     * @param updatedAt
     */
    public abstract void setUpdatedAt(Date updatedAt);
}
