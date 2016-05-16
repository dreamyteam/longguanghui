package com.dreamy.lgh.service.impl;

import com.dreamy.lgh.service.iface.ShortMessageService;
import com.dreamy.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 上午11:36
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    private static final String API_KEY = "93a1be01ead21e04bac79f8e75225f9f";
    private static final String API_SECRITE = "8d613058";
    private static final String REQUEST_URL = "https://sms.yunpian.com/v2/sms/single_send.json";

    @Override
    public void send(String mobile, String text) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("apikey", API_KEY);
        map.put("mobile", mobile);
        map.put("text", text);

        HttpUtils.post(REQUEST_URL, map);
    }
}
