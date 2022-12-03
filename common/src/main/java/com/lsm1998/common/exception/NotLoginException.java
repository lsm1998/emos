package com.lsm1998.common.exception;

public class NotLoginException extends Exception
{
    public NotLoginException()
    {
        super("用户未登录");
    }
}
