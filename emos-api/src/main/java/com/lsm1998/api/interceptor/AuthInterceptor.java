package com.lsm1998.api.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.lsm1998.api.model.User;
import com.lsm1998.common.constant.Constant;
import com.lsm1998.common.jwt.JwtUtil;
import com.lsm1998.common.threadLocal.LoginUser;
import com.lsm1998.common.threadLocal.ThreadLocalCache;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception
    {
        String token = request.getHeader("authorization");
        Map<String, Claim> claimMap = JwtUtil.parse(Constant.TOKEN_SECRET, token);
        if (claimMap == null)
        {
            response.setStatus(401);
            return false;
        }
        LoginUser user = new LoginUser();
        user.setId(Long.valueOf(claimMap.get("user_id").asInt()));
        user.setUsername(claimMap.get("username").asString());

        ThreadLocalCache.baseSignatureRequestThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception
    {
        ThreadLocalCache.baseSignatureRequestThreadLocal.remove();
    }
}
