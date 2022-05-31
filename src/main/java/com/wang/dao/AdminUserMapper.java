package com.wang.dao;

import com.wang.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {

    AdminUser login(@Param("userName") String userName, @Param("passWord") String passWord);

}
