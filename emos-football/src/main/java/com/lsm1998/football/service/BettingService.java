package com.lsm1998.football.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.model.dto.BettingDto;
import com.lsm1998.football.request.BettingListParam;

public interface BettingService
{
    boolean saveBetting(Betting betting);

    boolean updateBetting(Betting betting);

    Page<BettingDto> list(BettingListParam find);

    boolean delete(Long id,Long userId);
}
