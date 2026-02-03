package com.example.tob.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@AllArgsConstructor
public class UserDetailsCustomize implements UserDetails {

    private final String userName;
    private final String password;
    private final Boolean locked;
    private final Boolean actived;
    private final Collection<? extends GrantedAuthority> authorities;


    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return Boolean.FALSE.equals(locked);
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return Boolean.FALSE.equals(locked);
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.FALSE.equals(locked);
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(actived);
    }
}
