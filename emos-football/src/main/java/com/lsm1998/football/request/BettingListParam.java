package com.lsm1998.football.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsm1998.common.AbstractPageQueryParam;
import lombok.Data;

import java.util.Date;

@Data
public class BettingListParam extends AbstractPageQueryParam
{
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Override
    public <T> QueryWrapper<T> queryWrapper()
    {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (this.getUserId() != null)
        {
            wrapper.eq("user_id", this.getUserId());
        }
        if (this.getStartTime() != null)
        {
            wrapper.ge("created_time", this.getStartTime());
        }
        if (this.getEndTime() != null)
        {
            wrapper.le("created_time", this.getEndTime());
        }
        return wrapper;
    }
}
