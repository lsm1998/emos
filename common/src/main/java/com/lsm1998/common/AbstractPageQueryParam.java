package com.lsm1998.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.common.query.QueryParam;

public abstract class AbstractPageQueryParam extends AbstractPageParam implements QueryParam
{
    @Override
    public <T> Page<T> getPageInfo()
    {
        return this.newPage();
    }
}
