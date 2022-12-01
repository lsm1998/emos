package com.lsm1998.football.controller;

import com.lsm1998.common.AjaxResponse;
import com.lsm1998.football.constant.ThreadLocalCache;
import com.lsm1998.football.model.Betting;
import com.lsm1998.football.model.User;
import com.lsm1998.football.request.BettingListParam;
import com.lsm1998.football.service.BettingService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("api/v1/betting")
@Api(tags = "投注服务")
public class BettingController
{
    @Resource
    private BettingService bettingService;

    @PostMapping
    @ApiOperation(value = "投注")
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse save(@RequestBody Betting betting)
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
        return AjaxResponse.success(bettingService.saveBetting(betting));
    }

    @GetMapping
    @ApiOperation(value = "投注列表")
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse list(BettingListParam param)
    {
        User user = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        if (user == null)
        {
            return AjaxResponse.failWithParam();
        }
        return AjaxResponse.success(bettingService.list(param));
    }

    @DeleteMapping()
    @ApiOperation(value = "删除记录")
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse delete(@RequestParam Long id)
    {
        User user = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        if (user == null)
        {
            return AjaxResponse.failWithParam();
        }
        return AjaxResponse.success(bettingService.delete(id, user.getId()));
    }

    @PutMapping()
    @ApiOperation(value = "修改记录")
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse update(@RequestBody Betting betting)
    {
        User user = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        if(betting.getId() == null || user == null)
        {
            return AjaxResponse.failWithParam();
        }
        betting.setUpdatedTime(new Date());
        betting.setUserId(user.getId());
        return AjaxResponse.success(bettingService.updateBetting(betting));
    }
}
