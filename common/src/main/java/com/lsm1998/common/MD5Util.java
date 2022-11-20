package com.lsm1998.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class MD5Util
{
    public static String salt()
    {
        return UUID.randomUUID().toString().substring(0, 16);
    }

    public static String md5(String password, String salt)
    {
        return DigestUtils.md5Hex(password + salt);
    }
}
