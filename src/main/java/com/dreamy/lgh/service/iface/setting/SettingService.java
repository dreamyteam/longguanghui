package com.dreamy.lgh.service.iface.setting;

import com.dreamy.lgh.domain.sys.Setting;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:27
 */
public interface SettingService {

    /**
     *
     * @param setting
     * @return
     */
    Integer save(Setting setting);

    /**
     *
     * @param setting
     * @return
     */
    Integer updateByRecord(Setting setting);
}
