package com.lsm1998.football.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lsm1998.football.model.Betting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BettingMapper extends BaseMapper<Betting>
{
    default Betting getBettingByUserId(int userId)
    {
        return this.selectOne(new QueryChainWrapper<>(this).ge("user_id", userId));
    }
}
