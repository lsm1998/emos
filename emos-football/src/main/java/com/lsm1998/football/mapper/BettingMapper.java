package com.lsm1998.football.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lsm1998.football.model.Betting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BettingMapper extends BaseMapper<Betting>
{
    default Betting getBettingByUserId(Long userId)
    {
        QueryWrapper<Betting> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.selectOne(wrapper);
    }
}
