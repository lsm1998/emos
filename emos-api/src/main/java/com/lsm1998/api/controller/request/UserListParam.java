package com.lsm1998.api.controller.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.api.model.User;
import com.lsm1998.common.AbstractPageQueryParam;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class UserListParam extends AbstractPageQueryParam
{
    private Long id;

    private String username;

    @Override
    public <T> QueryWrapper<T> queryWrapper()
    {
        QueryWrapper<T> wrapper = new QueryWrapper<>();

        if (this.getId() != 0)
        {
            wrapper = wrapper.eq("id", this.getId());
        }
        if (!Strings.isEmpty(this.getUsername()))
        {
            wrapper = wrapper.like("username", this.getUsername());
        }
        return wrapper;
    }
}
