package com.lsm1998.football.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.football.mapper.BettingMapper;
import com.lsm1998.football.mapper.OperationMapper;
import com.lsm1998.football.mapper.UserMapper;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.model.User;
import com.lsm1998.football.model.dto.BettingDto;
import com.lsm1998.football.request.BettingListParam;
import com.lsm1998.football.service.BettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BettingServiceImpl implements BettingService
{
    @Resource
    private BettingMapper bettingMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional
    @Override
    public boolean saveBetting(Betting betting)
    {
        return bettingMapper.insert(betting) > 0;
    }

    @Override
    public Page<BettingDto> list(BettingListParam find)
    {
        Page<Betting> page = bettingMapper.selectPage(find.getPageInfo(), find.queryWrapper());
        List<Betting> records = page.getRecords();
        List<Long> userIdList = records.stream().map(Betting::getUserId).distinct().collect(Collectors.toList());
        List<User> users = userMapper.selectBatchIds(userIdList);

        Map<Long, String> usernameMap = new HashMap<>(users.size());
        users.forEach(e -> usernameMap.put(e.getId(), e.getRealName()));

        Page<BettingDto> result = new Page<>();
        result.setTotal(page.getTotal());
        result.setRecords(new ArrayList<>(records.size()));

        records.forEach(e ->
        {
            BettingDto temp = new BettingDto();
            BeanUtils.copyProperties(e, temp);
            temp.setRealName(usernameMap.get(temp.getUserId()));
            result.getRecords().add(temp);
        });
        return result;
    }

    @Override
    public boolean updateBetting(Betting betting)
    {
        QueryWrapper<Betting> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("user_id", betting.getUserId());
        updateWrapper.eq("version", betting.getVersion());
        betting.setVersion(betting.getVersion() + 1);
        return bettingMapper.update(betting, updateWrapper) > 0;
    }

    @Override
    public boolean delete(Long id, Long userId)
    {
        QueryWrapper<Betting> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("id", id);
        deleteWrapper.eq("user_id", userId);
        return bettingMapper.delete(deleteWrapper) > 0;
    }
}
