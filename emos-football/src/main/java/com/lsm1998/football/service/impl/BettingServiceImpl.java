package com.lsm1998.football.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsm1998.football.mapper.BettingMapper;
import com.lsm1998.football.mapper.OperationMapper;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.service.BettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BettingServiceImpl implements BettingService
{
    @Resource
    private BettingMapper bettingMapper;

    @Resource
    private OperationMapper operationMapper;

    @Transactional
    @Override
    public boolean betting(Betting betting)
    {
        Betting newBetting = bettingMapper.getBettingByUserId(betting.getUserId());
        if (newBetting == null && bettingMapper.insert(betting) > 0)
        {
            return true;
        }
        QueryWrapper<Betting> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("user_id", betting.getUserId());
        updateWrapper.eq("version", betting.getVersion());
        betting.setVersion(betting.getVersion() + 1);
        return bettingMapper.update(betting, updateWrapper) > 0;
    }

    @Override
    public List<Betting> list(Betting find)
    {
        return bettingMapper.selectList(new QueryWrapper<>());
    }
}
