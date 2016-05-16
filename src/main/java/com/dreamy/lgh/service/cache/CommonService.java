package com.dreamy.lgh.service.cache;

import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Service
public class CommonService {

    @Value("${devModel}")
    private String devModel;

    @Autowired
    private LocalCache localCache;
    @Autowired
    private RedisCache redisCache;

    public boolean isDev() {
        if (StringUtils.isNotEmpty(devModel) && !devModel.equals("dev")) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }




    public CacheService getCacheService() {
        CacheService cacheService = isDev() ? localCache : redisCache;
        return cacheService;
    }


}
