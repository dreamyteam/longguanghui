package com.dreamy.lgh.service.iface;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:38
 */
public interface VerificationCodeService {

    /**
     * 默认的生成6位的验证码（数字+字母）
     *
     * @return
     */
    String createVerificationCode(Integer length);

    /**
     * @param cacheKey
     * @param code
     * @return
     */
    void saveCodeToCache(String cacheKey, String code);

    /**
     * @param cacheKey
     * @return
     */
    String getCodeFromCache(String cacheKey);
}
