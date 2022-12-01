package com.lsm1998.football.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.lsm1998.common.jwt.JwtUtil;
import com.lsm1998.football.constant.Constant;
import com.lsm1998.football.constant.ThreadLocalCache;
import com.lsm1998.football.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getHeader("authorization");
        Map<String, Claim> claimMap = JwtUtil.parse(Constant.TOKEN_SECRET, token);
        if (claimMap == null)
        {
            response.setStatus(401);
            return false;
        }
        User user = new User();
        user.setId(Long.valueOf(claimMap.get("user_id").asInt()));
        user.setUsername(claimMap.get("username").asString());

        ThreadLocalCache.baseSignatureRequestThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        ThreadLocalCache.baseSignatureRequestThreadLocal.remove();
    }
}
