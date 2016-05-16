package com.dreamy.lgh.service.impl;

import com.dreamy.lgh.service.cache.RedisClientService;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:38
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final static String CACHE_PREFIX = "VC_";

    @Autowired
    private RedisClientService redisClientService;

    @Override
    public String createVerificationCode(Integer length) {
        String chars = "0123456789";
        Integer charsLength = chars.length();
        char[] rands = new char[length];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * charsLength);
            rands[i] = chars.charAt(rand);
        }

        return String.valueOf(rands);
    }


    @Override
    public void saveCodeToCache(String cacheKey, String code) {
        redisClientService.set(CACHE_PREFIX + cacheKey, code);
        redisClientService.expire(CACHE_PREFIX + cacheKey, 1800);
    }


    @Override
    public String getCodeFromCache(String cacheKey) {
        return redisClientService.get(CACHE_PREFIX + cacheKey);
    }
}
