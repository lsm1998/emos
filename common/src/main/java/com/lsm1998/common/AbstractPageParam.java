package com.lsm1998.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.common.query.PageParam;
import lombok.Data;

@Data
public abstract class AbstractPageParam implements PageParam
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

    private void checkSize()
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
    public abstract <T> Page<T> getPageInfo();

    public <T> Page<T> newPage()
    {
        this.checkPage();
        this.checkSize();
        return new Page<>(this.page, this.size);
    }
}
