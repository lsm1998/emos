package com.lsm1998.football.service;

import com.lsm1998.football.model.User;

public interface UserService
{
    User login(String username, String password);

    User getUser(Integer id);
}
