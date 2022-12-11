package com.lsm1998.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.api.mapper.UserMapper;
import com.lsm1998.api.model.User;
import com.lsm1998.api.service.UserService;
import com.lsm1998.common.MD5Util;
import com.lsm1998.api.controller.request.UserListParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> getPageList(UserListParam param)
    {
        return userMapper.selectPage(param.getPageInfo(), param.queryWrapper());
    }

    @Override
    public boolean insert(User user)
    {
        user.setSalt(MD5Util.salt());
        user.setPassword(MD5Util.md5(user.getPassword(), user.getSalt()));
        Date now = new Date();
        user.setCreatedTime(now);
        user.setUpdatedTime(now);
        return userMapper.insert(user) > 0;
    }

    @Override
    public User getUserByUsername(String username)
    {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }
}
