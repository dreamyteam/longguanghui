package com.dreamy.lgh.service.impl.setting;

import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.SettingDao;
import com.dreamy.lgh.domain.sys.Setting;
import com.dreamy.lgh.domain.sys.SettingConditions;
import com.dreamy.lgh.service.iface.setting.SettingService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String getValue(String key) {
        SettingConditions conditions = new SettingConditions();
        conditions.createCriteria().andKeyEqualTo(key);

        Page page = new Page();
        page.setPageSize(1);

        conditions.setPage(page);
        List<Setting> settingList = settingDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(settingList)) {
            return settingList.get(0).getValue();
        }

        return null;
    }
}

