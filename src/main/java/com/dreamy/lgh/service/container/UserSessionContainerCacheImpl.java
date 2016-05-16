package com.dreamy.lgh.service.container;

import com.dreamy.lgh.beans.CanonicalSession;
import com.dreamy.lgh.beans.UserSessionContainer;
import com.dreamy.lgh.service.cache.CommonService;
import com.dreamy.utils.ConstUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class UserSessionContainerCacheImpl<S extends CanonicalSession> implements UserSessionContainer<S> {

    private static final Logger log = LoggerFactory.getLogger(UserSessionContainerCacheImpl.class);

    private String prefix;

    private int timeout = ConstUtils.Session.USERSESSION_TIMEOUT;

    private String buildKey(String id) {
        return prefix == null ? id : prefix + id;
    }

    private String buildRemenberKey(String id) {
        return prefix == null ? id : prefix + "rm_" + id;
    }

    @Autowired
    CommonService commonService;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public S get(String id) {
        if (StringUtils.isNotEmpty(id)) {
            try {
                S s = (S) commonService.getCacheService().get(buildKey(id));
                if (s == null) {
                    s = (S) commonService.getCacheService().get(buildRemenberKey(id));
                }
                return s;
            } catch (Throwable e) {
                log.error("get usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public void set(String id, S userSession) {
        if (StringUtils.isNotEmpty(id) && userSession != null) {
            try {
                commonService.getCacheService().set(buildKey(id), userSession, timeout);
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
    }

    @Override
    public void clear(String id) {
        if (StringUtils.isNotEmpty(id)) {
            try {
                commonService.getCacheService().remove(buildKey(id));
                commonService.getCacheService().remove(buildRemenberKey(id));
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
    }

    @Override
    public void persistentSet(String id, S userSession) {
        if (StringUtils.isNotEmpty(id) && userSession != null) {
            try {
                commonService.getCacheService().put(buildRemenberKey(id), userSession);
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
    }
}
