package com.lsm1998.football.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsm1998.common.AbstractPageQueryParam;
import lombok.Data;

import java.util.List;

@Data
public class BettingListParam extends AbstractPageQueryParam
{
    private Long userId;

    private String startTime;

    private String endTime;

    private List<Integer> statusList;

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

        if (this.getStatusList() != null && !this.getStatusList().isEmpty())
        {
            wrapper.in("status", this.getStatusList());
        }
        return wrapper;
    }
}
