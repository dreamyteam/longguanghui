package com.dreamy.lgh.service.impl.user;

import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.UserDao;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.domain.user.UserConditions;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByMobile(String mobile) {
        User user = new User();
        UserConditions conditions = new UserConditions();
        conditions.createCriteria().andPhoneEqualTo(mobile);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<User> users = userDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(users)) {
            user = users.get(0);
        }

        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    public Integer updateByRecord(User user) {
        return userDao.update(user);
    }

    @Override
    public Cookie rememerPwd(User user) {
//        long validTime = (60 * 60 * 24 * 7 * 1000);
//        String cookieValueWithMd5 = HashUtils.md5(user.getUserName() + ":" + user.getPassword() + ":" + validTime + ":" + "jeff1989");
//        String cookieValue = user.getUserName() + ":" + validTime + ":" + cookieValueWithMd5;
//        String cookieValueBase64 = Base64.encode(cookieValue.getBytes());
//        Cookie cookie = new Cookie("zmcom", cookieValueBase64);
//        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
//        cookie.setPath("/");

        return null;
    }
}
