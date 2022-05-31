package com.wang.service.impl;

import com.wang.dao.AdminUserMapper;
import com.wang.entity.AdminUser;
import com.wang.service.AdminUserService;
import com.wang.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String passWord) {
        return adminUserMapper.login(userName, passWord);
    }
}
