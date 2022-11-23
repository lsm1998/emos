package com.lsm1998.common.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@FunctionalInterface
public interface PageParam<T>
{
    Page<T> getPageInfo();
}
