package com.lsm1998.api.controller;

import com.lsm1998.api.mongo.model.Temp;
import com.lsm1998.api.service.TempService;
import com.lsm1998.common.AjaxResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("temp")
@Api(tags = "temp服务")
public class TempController
{
    @Resource
    private TempService tempService;

    @PostMapping
    public AjaxResponse save(@RequestBody Temp temp)
    {
        return AjaxResponse.success(tempService.save(temp));
    }
}
