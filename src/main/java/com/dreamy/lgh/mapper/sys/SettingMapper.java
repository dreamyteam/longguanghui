package com.dreamy.lgh.mapper.sys;

import com.dreamy.lgh.domain.sys.Setting;
import com.dreamy.lgh.domain.sys.SettingConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SettingMapper extends BaseMapper<Setting,Integer,SettingConditions>{
    int countByExample(SettingConditions example);

    int deleteByExample(SettingConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Setting record);

    int insertSelective(Setting record);

    List<Setting> selectByExample(SettingConditions example);

    Setting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Setting record, @Param("example") SettingConditions example);

    int updateByExample(@Param("record") Setting record, @Param("example") SettingConditions example);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}