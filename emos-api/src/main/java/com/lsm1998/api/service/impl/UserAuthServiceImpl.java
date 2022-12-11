package com.lsm1998.api.service.impl;

import com.lsm1998.api.mapper.UserAuthMapper;
import com.lsm1998.api.service.UserAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAuthServiceImpl implements UserAuthService
{
    @Resource
    private UserAuthMapper userAuthMapper;

    @Override
    public List<String> userAuthKeyList(Long userId)
    {
        return userAuthMapper.userAuthKeyList(userId);
    }
}
