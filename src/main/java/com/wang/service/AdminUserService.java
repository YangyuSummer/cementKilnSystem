package com.wang.service;

import com.wang.entity.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName, String passWord);
}
