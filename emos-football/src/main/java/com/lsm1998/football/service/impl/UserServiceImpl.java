package com.lsm1998.football.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsm1998.football.mapper.UserMapper;
import com.lsm1998.football.model.User;
import com.lsm1998.football.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password)
    {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUser(Integer id)
    {
        return userMapper.selectById(id);
    }
}
