package com.lsm1998.football.controller;

import com.lsm1998.common.AjaxResponse;
import com.lsm1998.common.jwt.JwtUtil;
import com.lsm1998.football.constant.Constant;
import com.lsm1998.football.constant.ThreadLocalCache;
import com.lsm1998.football.model.User;
import com.lsm1998.football.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@Api(tags = "用户服务")
public class UserController
{
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public AjaxResponse login(@RequestBody User user)
    {
        if (Strings.isEmpty(user.getUsername()) || Strings.isEmpty(user.getPassword()))
        {
            return AjaxResponse.failWithParam();
        }
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null)
        {
            String token = JwtUtil.signature(Constant.TOKEN_SECRET, Map.of("user_id", loginUser.getId(), "username", loginUser.getUsername()));
            return AjaxResponse.success(token);
        }
        return AjaxResponse.fail("登录失败");
    }

    @GetMapping("/profile")
    @ApiOperation(value = "查询用户信息",
            authorizations = {
                    @Authorization("authorization")
            })
    @ApiImplicitParam(name = "authorization", value = "String 类型", required = true, dataType = "String", paramType = "header")
    public AjaxResponse profile()
    {
        System.out.println("profile:" + Thread.currentThread().getName());
        User user = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        if (user == null)
        {
            return AjaxResponse.failWithParam();
        }
        return AjaxResponse.success(userService.getUser(user.getId().intValue()));
    }
}
