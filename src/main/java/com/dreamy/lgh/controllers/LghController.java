package com.dreamy.lgh.controllers;

import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.WebUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyongxing on 16/4/12.
 */
public class LghController extends LghBaseController {
    @Override
    public boolean checkLogin() {
        return Boolean.FALSE;
    }


    public void jsonpReturn(HttpServletRequest request, HttpServletResponse response, Object result) {
        String contentType = "";
        String jsonResult = JsonUtils.toString(result);
        if (StringUtils.isNotBlank(request.getParameter("callback"))) {
            StringBuilder jsonp = new StringBuilder();
            jsonp.append(request.getParameter("callback")).append("(").append(jsonResult).append(");");
            contentType = WebUtils.getContentTypeJavascript();
            jsonResult = jsonp.toString();
        } else {
            contentType = WebUtils.getContentTypeJson();
        }

        write(response, jsonResult, contentType);
    }

}
