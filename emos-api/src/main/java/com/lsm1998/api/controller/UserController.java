package com.lsm1998.api.controller;

import com.lsm1998.api.model.User;
import com.lsm1998.api.service.UserService;
import com.lsm1998.common.AjaxResponse;
import com.lsm1998.api.controller.request.UserListParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Map;

import static com.lsm1998.common.constant.code.*;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "用户服务")
public class UserController
{
    @Resource
    private UserService userService;

    @PostMapping("login")
    public AjaxResponse login(@RequestBody User user)
    {
        return AjaxResponse.success(Map.of("token", "123"));
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    public AjaxResponse getList(UserListParam param)
    {
        return AjaxResponse.success(userService.getPageList(param));
    }

    @PostMapping
    public AjaxResponse save(@RequestBody User user)
    {
        if (Strings.isEmpty(user.getPassword()) || Strings.isEmpty(user.getUsername()) || Strings.isEmpty(user.getNickname()))
        {
            return AjaxResponse.of(CODE_PARAM_ERROR, MESSAGE_PARAM_ERROR);
        }
        if (userService.insert(user))
        {
            return AjaxResponse.success();
        } else
        {
            return AjaxResponse.fail();
        }
    }
}
