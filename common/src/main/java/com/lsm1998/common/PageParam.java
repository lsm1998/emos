package com.lsm1998.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageParam<E> implements Serializable
{
    private E data;

    private int page;

    private int size;

    private void checkPage()
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

    public Page<E> getPage()
    {
        this.checkPage();
        this.checkSize();
        return new Page<>(page, size);
    }
}
