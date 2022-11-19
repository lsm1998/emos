package com.lsm1998.api.controller;

import com.lsm1998.api.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Resource
    private UserService userService;
}
