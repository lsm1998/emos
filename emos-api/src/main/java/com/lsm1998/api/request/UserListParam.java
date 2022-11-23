package com.lsm1998.api.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.api.model.User;
import com.lsm1998.common.AbstractPageQueryParam;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class UserListParam<T> extends AbstractPageQueryParam<T>
{
    private User data;

    public Page<T> getPageInfo()
    {
        this.checkPage();
        this.checkSize();
        return new Page<>(this.getPage(), this.getSize());
    }

    @Override
    public QueryWrapper<T> queryWrapper()
    {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if(this.data == null)
        {
            return wrapper;
        }
        if(this.data.getId() != 0)
        {
            wrapper = wrapper.eq("id",this.data.getId());
        }
        if( !Strings.isEmpty(this.data.getUsername()))
        {
            wrapper = wrapper.like("username",this.data.getUsername());
        }
        return wrapper;
    }
}
