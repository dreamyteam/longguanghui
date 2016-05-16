package com.dreamy.lgh.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyongxing on 16/3/31.
 */
public interface BaseDao<BaseDomain, ID extends Serializable, T> {


    void setBaseMapper();

    /**保存
     * @param record
     * @return
     */
    int save(BaseDomain record);

    /**
     * 根据id获取实体
     * @param id
     * @return
     */
    BaseDomain selectById(ID id);

    /**
     * 根据id 删除实体
     * @param id
     * @return
     */
    int deleteById(ID id);

    /**
     *   根据条件删除实体
      * @param example
     * @return
     */
    int deleteByExample(T example);


    /**
     * 更新
     * @return
     */
    int update(BaseDomain baseDomain);

    /**
     * 根据条件获取实体集合
     * @param example
     * @return
     */
    List<BaseDomain> selectByExample(T example);

    /**
     * 根据条件更新
     * @param baseDomain
     *  更新数据
     * @param t
     * 更新条件
     * @return
     */
    int updateByExampleSelective(BaseDomain baseDomain, T t);

    /**
     * 根据条件 获取总行数
     * @param example
     * @return
     */
    int countByExample(T example);


}
