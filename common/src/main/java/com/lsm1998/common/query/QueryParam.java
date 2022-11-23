package com.lsm1998.common.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@FunctionalInterface
public interface QueryParam<T>
{
    QueryWrapper<T> queryWrapper();
}
