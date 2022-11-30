package com.lsm1998.football.controller;

import com.lsm1998.common.AjaxResponse;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.service.BettingService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/v1/betting")
@Api(tags = "投注服务")
public class BettingController
{
    @Resource
    private BettingService bettingService;

    @PutMapping
    public AjaxResponse betting(@RequestBody Betting betting)
    {
        return AjaxResponse.success(bettingService.betting(betting));
    }
}
