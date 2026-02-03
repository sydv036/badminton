package com.example.tob.services.auth.services;

import com.example.tob.dtos.auth.UserDetailsCustomize;
import com.example.tob.dtos.responses.auth.MemberInfoResponse;
import com.example.tob.repository.IAccountRepository;
import com.example.tob.repository.IAccountRoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserDetailServiceCustomize implements UserDetailsService {

    private final IAccountRepository accountRepository;
    private final IAccountRoleRepository accountRoleRepository;

    private final MessageSource messageSource;

    private static final String MESE009 = "MESE009";

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberInfoResponse memberInfoResponse = accountRepository.findByUserName(username);
        if (ObjectUtils.isEmpty(memberInfoResponse))
            throw new UsernameNotFoundException(messageSource.getMessage(MESE009, new Object[]{username}, Locale.getDefault()));
        List<SimpleGrantedAuthority> authorities = accountRoleRepository.findBySystemId(memberInfoResponse.getSystemId())
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .toList();


        return new UserDetailsCustomize(
                memberInfoResponse.getEmail(),
                memberInfoResponse.getPassword(),
                memberInfoResponse.getLocked(),
                memberInfoResponse.getActived(),
                authorities);
    }
}
