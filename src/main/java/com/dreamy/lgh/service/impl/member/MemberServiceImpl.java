package com.dreamy.lgh.service.impl.member;

import com.dreamy.beans.Page;
import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.dao.iface.MemberDao;
import com.dreamy.lgh.dao.iface.UserDao;
import com.dreamy.lgh.domain.user.*;
import com.dreamy.lgh.enums.MemberEnums;
import com.dreamy.lgh.enums.MemberStateEnums;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午7:44
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer save(Members members) {
        return memberDao.save(members);
    }

    @Override
    public Members saveByWx(WxUser wxUser, Integer userId) {
        Date date = new Date();
        Members members = new Members()
                .userId(userId)
                .wxId(wxUser.getOpenid())
                .wxImageUrl(wxUser.getHeadimgurl())
                .startedAt(date)
                .endedAt(date)
                .wxUserName(wxUser.getNickname());
        save(members);
        return members;
    }

    @Override
    public Members getByUserId(Integer userId) {
        MembersConditions conditions = new MembersConditions();
        conditions.createCriteria().andUserIdEqualTo(userId);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<Members> membersList = memberDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(membersList)) {
            return membersList.get(0);
        }
        return null;
    }

    @Override
    public Members getByOpenId(String openId) {
        MembersConditions conditions = new MembersConditions();
        conditions.createCriteria().andWxIdEqualTo(openId);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<Members> membersList = memberDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(membersList)) {
            return membersList.get(0);
        }
        return null;
    }

    @Override
    public List<Members> getByPageAndOrder(Page page, String order) {
        MembersConditions conditions = new MembersConditions();
        conditions.setPage(page);
        conditions.setOrderByClause(order);
        return memberDao.selectByExample(conditions);
    }

    @Override
    public List<Members> getMemberByUserIds(List<Integer> userIds) {
        MembersConditions membersConditions = new MembersConditions();
        membersConditions.createCriteria().andUserIdIn(userIds).andStatusEqualTo(MemberStateEnums.active.getStatus());

        return memberDao.selectByExample(membersConditions);
    }

    @Override
    public List<Members> getApplyMemberByUserIds(List<Integer> userIds) {
        MembersConditions membersConditions = new MembersConditions();
        membersConditions.createCriteria().andUserIdIn(userIds).andStatusEqualTo(MemberStateEnums.new_add.getStatus()).andTypeGreaterThan(MemberEnums.vip.getType());

        return memberDao.selectByExample(membersConditions);
    }

    @Override
    public List<Members> getMemberByUserIdsAndStatus(List<Integer> userIds, Integer status) {
        MembersConditions membersConditions = new MembersConditions();
        membersConditions.createCriteria().andUserIdIn(userIds).andStatusEqualTo(status);

        return memberDao.selectByExample(membersConditions);
    }

    @Override
    public Map<Integer, Members> getUserIdAndMemberMapByMemberList(List<Members> membersList) {
        Map<Integer, Members> membersMap = new HashMap<Integer, Members>();
        for (Members members : membersList) {
            membersMap.put(members.getUserId(), members);
        }

        return membersMap;
    }

    @Override
    public Integer updateByRecord(Members members) {
        return memberDao.update(members);
    }

    @Override
    public List<UserWithMember> getByPageAndUserNameAndPhone(Page page, String userName, String phone) {

        List<UserWithMember> userWithMemberList = new LinkedList<UserWithMember>();
        UserConditions userConditions = new UserConditions();
        UserConditions.Criteria criteria = userConditions.createCriteria();
        if (StringUtils.isNotEmpty(userName)) {
            criteria.andUserNameLike("%" + userName + "%");
        }

        if (StringUtils.isNotEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }

        page.setTotalNum(userDao.countByExample(userConditions));
        userConditions.setPage(page);
        List<User> users = userDao.selectByExample(userConditions);

        if (CollectionUtils.isNotEmpty(users)) {
            List<Integer> userIds = new LinkedList<Integer>();
            for (User user : users) {
                userIds.add(user.getId());
            }

            List<Members> membersList = getMemberByUserIds(userIds);
            MemberEnums[] enums = MemberEnums.values();
            if (CollectionUtils.isNotEmpty(membersList)) {
                Map<Integer, Members> membersMap = getUserIdAndMemberMapByMemberList(membersList);
                Date currentDate = new Date();
                for (User user : users) {
                    UserWithMember userWithMember = new UserWithMember();
                    userWithMember.setUser(user);
                    Members members = membersMap.get(user.getId());
                    if (members != null) {
                        if (currentDate.after(members.getEndedAt())) {
                            userWithMember.setMemberStateStr(MemberStateEnums.out_of_date.getDescription());
                        } else {
                            userWithMember.setMemberStateStr(MemberStateEnums.active.getDescription());
                        }


                        for (MemberEnums memberEnums : enums) {
                            if (members.getType().equals(memberEnums.getType())) {
                                userWithMember.setLevelStr(memberEnums.getDescription());
                            }
                        }

                        userWithMember.setMembers(members);
                        userWithMemberList.add(userWithMember);
                    }
                }
            }
        }

        return userWithMemberList;
    }

    @Override
    public List<UserWithMember> getApplyListByPageAndUserNameAndPhone(Page page, String userName, String phone) {
        List<UserWithMember> userWithMemberList = new LinkedList<UserWithMember>();
        UserConditions userConditions = new UserConditions();
        UserConditions.Criteria criteria = userConditions.createCriteria();
        if (StringUtils.isNotEmpty(userName)) {
            criteria.andUserNameLike("%" + userName + "%");
        }

        if (StringUtils.isNotEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }

        page.setTotalNum(userDao.countByExample(userConditions));
        userConditions.setPage(page);
        List<User> users = userDao.selectByExample(userConditions);

        if (CollectionUtils.isNotEmpty(users)) {
            List<Integer> userIds = new LinkedList<Integer>();
            for (User user : users) {
                userIds.add(user.getId());
            }

            List<Members> membersList = getApplyMemberByUserIds(userIds);
            if (CollectionUtils.isNotEmpty(membersList)) {
                MemberEnums[] enums = MemberEnums.values();
                Map<Integer, Members> membersMap = getUserIdAndMemberMapByMemberList(membersList);
                Date currentDate = new Date();
                for (User user : users) {
                    UserWithMember userWithMember = new UserWithMember();
                    userWithMember.setUser(user);
                    Members members = membersMap.get(user.getId());
                    if (members != null) {
                        if (currentDate.after(members.getEndedAt())) {
                            userWithMember.setMemberStateStr(MemberStateEnums.out_of_date.getDescription());
                        } else {
                            userWithMember.setMemberStateStr(MemberStateEnums.active.getDescription());
                        }


                        for (MemberEnums memberEnums : enums) {
                            if (members.getType().equals(memberEnums.getType())) {
                                userWithMember.setLevelStr(memberEnums.getDescription());
                            }
                        }

                        userWithMember.setMembers(members);
                        userWithMemberList.add(userWithMember);
                    }
                }
            }
        }

        return userWithMemberList;
    }
}
