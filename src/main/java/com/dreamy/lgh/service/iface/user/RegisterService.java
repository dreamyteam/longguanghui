package com.dreamy.lgh.service.iface.user;


import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.enums.ErrorCodeEnums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午12:23
 */
public interface RegisterService {

    /**
     *
     * @param param
     * @return
     */
    ErrorCodeEnums checkRegisterParam(RegisterParams param);

    /**
     *
     * @param param
     * @return
     */
    String createUserKey(RegisterParams param);

    /**
     *
     * @param params
     * @return
     */
    ErrorCodeEnums checkAddMemberParam(RegisterParams params);


}
