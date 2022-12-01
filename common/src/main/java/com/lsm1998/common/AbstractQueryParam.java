package com.lsm1998.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsm1998.common.query.QueryParam;

public abstract class AbstractQueryParam<T> implements QueryParam
{
    @Override
    public <T> QueryWrapper<T> queryWrapper()
    {
        return new QueryWrapper<>();
    }
}
