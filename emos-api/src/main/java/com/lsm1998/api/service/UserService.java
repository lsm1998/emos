package com.lsm1998.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.api.model.User;
import com.lsm1998.common.PageParam;

public interface UserService
{
    Page<User> getPageList(PageParam<User> param);

    boolean insert(User user);
}
