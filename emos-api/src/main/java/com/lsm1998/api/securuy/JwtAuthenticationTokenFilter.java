package com.lsm1998.api.securuy;

import com.auth0.jwt.interfaces.Claim;
import com.lsm1998.common.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token))
        {
            log.error("缺少token");
            throw new RuntimeException("缺少token");
        }
        LoginUser loginUser = new LoginUser();

        Map<String, Claim> claims = JwtUtil.parse("", token);
        try
        {
            loginUser.setUserId(claims.get("user_id").asLong());
            loginUser.setUsername(claims.get("username").asString());
            loginUser.setAuths(claims.get("auths").asList(String.class));
        } catch (NullPointerException e)
        {
            log.error("用户未登录，token={}", token);
            throw new RuntimeException("用户未登录");
        } catch (Exception e)
        {
            log.error("登录信息校验失败，token={}", token);
            throw new RuntimeException("登录信息校验失败");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
