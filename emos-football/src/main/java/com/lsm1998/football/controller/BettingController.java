package com.lsm1998.football.controller;

import com.lsm1998.common.AjaxResponse;
import com.lsm1998.football.constant.ThreadLocalCache;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.model.User;
import com.lsm1998.football.service.BettingService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("api/v1/betting")
@Api(tags = "投注服务")
public class BettingController
{
    @Resource
    private BettingService bettingService;

    @PutMapping
    @ApiOperation(value = "投注")
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse betting(@RequestBody Betting betting)
    {
        User user = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        if (user == null)
        {
            return AjaxResponse.failWithParam();
        }
        betting.setUserId(user.getId());
        Date now = new Date();
        betting.setCreatedTime(now);
        betting.setUpdatedTime(now);
        return AjaxResponse.success(bettingService.betting(betting));
    }
}
