package com.lsm1998.api.securuy;

import com.lsm1998.api.model.User;
import com.lsm1998.api.service.UserAuthService;
import com.lsm1998.api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Resource
    private UserService userService;

    @Resource
    private UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userService.getUserByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("该用户不存在");
        }
        //获取用户权限
        List<String> auths = userAuthService.userAuthKeyList(user.getId());
        return new LoginUser(user, auths);
    }
}
