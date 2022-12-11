package com.lsm1998.api.securuy;

import com.lsm1998.api.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails
{
    private Long userId;

    private String username;

    private List<String> auths;

    public LoginUser()
    {
    }

    public LoginUser(User user, List<String> auths)
    {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.auths = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return auths.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        return false;
    }
}
