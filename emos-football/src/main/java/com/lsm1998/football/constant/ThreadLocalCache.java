package com.lsm1998.football.constant;

import com.lsm1998.football.model.User;

public class ThreadLocalCache
{
    public static ThreadLocal<User> baseSignatureRequestThreadLocal = new ThreadLocal<>();
}
