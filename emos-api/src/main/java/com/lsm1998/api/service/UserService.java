package com.lsm1998.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.api.model.User;
import com.lsm1998.api.controller.request.UserListParam;

public interface UserService
{
    Page<User> getPageList(UserListParam param);

    boolean insert(User user);

    User getUserByUsername(String username);
}
