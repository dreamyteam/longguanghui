package com.dreamy.lgh.service.impl.setting;

import com.dreamy.lgh.dao.iface.SettingDao;
import com.dreamy.lgh.domain.sys.Setting;
import com.dreamy.lgh.service.iface.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:27
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public Integer save(Setting setting) {
        return settingDao.save(setting);
    }

    @Override
    public Integer updateByRecord(Setting setting) {
        return settingDao.update(setting);
    }
}
