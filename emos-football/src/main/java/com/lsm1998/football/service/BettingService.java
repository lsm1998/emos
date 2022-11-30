package com.lsm1998.football.service;

import com.lsm1998.football.model.Betting;

import java.util.List;

public interface BettingService
{
    boolean betting(Betting betting);

    List<Betting> list(Betting find);
}
