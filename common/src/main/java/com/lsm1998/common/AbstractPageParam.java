package com.lsm1998.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.common.query.PageParam;
import lombok.Data;

@Data
public abstract class AbstractPageParam<T> implements PageParam<T>
{
    private int page;

    private int size;

    public void checkPage()
    {
        if (page < 1)
        {
            page = 1;
        }
    }

    public void checkSize()
    {
        if (size < 10)
        {
            size = 10;
        } else if (size > 100)
        {
            size = 100;
        }
    }

    @Override
    public abstract Page<T> getPageInfo();
}
