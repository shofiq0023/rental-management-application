package com.api.rms.configs;

import com.api.rms.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user) {
        this.username = user.getUsername();
        this.password = user.getPasswordEncoded();
        this.authorities = Arrays.stream(new String[]{user.getUserType() == 1 ? "ROLE_ADMIN" : "ROLE_USER"})
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
