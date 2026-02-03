package com.example.tob.services.auth.services;

import com.example.tob.common.enums.RoleEnum;
import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.dtos.responses.auth.MemberInfoResponse;
import com.example.tob.entity.Account;
import com.example.tob.entity.Member;
import com.example.tob.entity.RoleAccount;
import com.example.tob.exceptions.BusinessException;
import com.example.tob.mapper.moduleAccount.AccountAuthMapper;
import com.example.tob.mapper.moduleMember.MemberAuthMapper;
import com.example.tob.repository.IAccountRepository;
import com.example.tob.repository.IAccountRoleRepository;
import com.example.tob.repository.IMemberRepository;
import com.example.tob.services.auth.interfaces.IAuthService;
import com.example.tob.services.auth.interfaces.ITokenBlacklistService;
import com.example.tob.utils.auth.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IMemberRepository memberRepository;

    private final IAccountRepository accountRepository;

    private final IAccountRoleRepository accountRoleRepository;

    private final ITokenBlacklistService tokenBlacklistService;

    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

    private final AuthProfiles authProfiles;

    private final MemberAuthMapper memberAuthMapper;

    private final AccountAuthMapper accountAuthMapper;

    private final AuthenticationManager authenticationManager;

    private final AuthUtils authUtils;

    private static final String MESE002 = "MESE002";
    private static final String MESI003 = "MESI003";
    private static final String MESE003 = "MESE003";

    /**
     * Handle register
     *
     * @param registerRequestDto info register
     * @return email of user registered
     */
    @Override
    public String handlerRegister(RegisterRequestDto registerRequestDto) {
        try {

            // Handle processing account
            Account accountRegister = settingAccountInfo(registerRequestDto);
            accountRegister = accountRepository.saveAndFlush(accountRegister);

            Long systemId = accountRegister.getSystemId();

            // Handle processing member
            Member memberRegister = settingMemberInfo(registerRequestDto, systemId);
            memberRepository.save(memberRegister);

            // Hander processing add role
            RoleAccount roleAccount = settingRoleAccount(RoleEnum.ROLE_USER, systemId);
            accountRoleRepository.save(roleAccount);

            log.info(messageSource.getMessage(MESI003, new String[]{registerRequestDto.getEmail()}, Locale.getDefault()));
            return registerRequestDto.getEmail();

        } catch (RuntimeException e) {
            String[] args = new String[]{registerRequestDto.getEmail(), e.getMessage()};
            log.error(messageSource.getMessage(MESE002, args, Locale.getDefault()));
            throw new BusinessException(e);
        }
    }

    /**
     * Handle login
     *
     * @param loginRequestDto info login
     * @return MemberInfoResponse contain user info
     */
    @Override
    public ResponseEntity<LoginResponse> handlerLogin(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
            );

            LoginResponse loginResponse = getMemberInfo(loginRequestDto);
            String accessToken = authUtils.createAccessToken(loginResponse);
            String refreshToken = authUtils.createRefreshToken(loginResponse);

            return ResponseEntity.ok()
                    .headers(httpHeaders -> {
                        httpHeaders.add(HttpHeaders.SET_COOKIE, authUtils.cookieSetting(authProfiles.getAccessToken(), accessToken, authProfiles.getCookieMaxAgeAccessToken()));
                        httpHeaders.add(HttpHeaders.SET_COOKIE, authUtils.cookieSetting(authProfiles.getRefreshToken(), refreshToken, authProfiles.getCookieMaxAgeRefreshToken()));
                    }).body(loginResponse);

        } catch (RuntimeException e) {
            String[] args = new String[]{loginRequestDto.getEmail(), e.getMessage()};
            log.error(messageSource.getMessage(MESE003, args, Locale.getDefault()));
            throw new BusinessException(e);
        }

    }

    /**
     * Handler refresh token when access token expire
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Object> handlerRefreshToken(HttpServletRequest request) {
        LoginResponse loginResponse = authUtils.getUserWithToken(request, authProfiles.getRefreshToken());

        String accessToken = authUtils.createAccessToken(loginResponse);

        log.info("Check {}",tokenBlacklistService.getAllBlacklistedTokens());

        return ResponseEntity.ok()
                .headers(httpHeaders ->
                        httpHeaders.add(HttpHeaders.SET_COOKIE, authUtils.cookieSetting(authProfiles.getAccessToken(), accessToken, authProfiles.getCookieMaxAgeAccessToken()))
                ).body(loginResponse);
    }

    /**
     * Handler logout
     *
     * @return
     */
    @Override
    public ResponseEntity<Object> handlerLogout(HttpServletResponse response, HttpServletRequest request) {

        String accessToken = authUtils.getCookieValue(request, authProfiles.getAccessToken());
        String refreshToken = authUtils.getCookieValue(request, authProfiles.getRefreshToken());

        if (StringUtils.isNotEmpty(accessToken)) {
            tokenBlacklistService.blackListToken(accessToken);
        }
        if (StringUtils.isNotEmpty(refreshToken)) {
            tokenBlacklistService.blackListToken(refreshToken);
        }

        authUtils.clearCookieSetting(response);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    private LoginResponse getMemberInfo(LoginRequestDto loginRequestDto) {

        MemberInfoResponse account = accountRepository.findByUserName(loginRequestDto.getEmail());
        Set<String> roles = accountRoleRepository.findBySystemId(account.getSystemId());

        if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(roles)) return null;

        return LoginResponse.builder()
                .publicId(account.getPublicId())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .roles(roles)
                .build();
    }

    private Account settingAccountInfo(RegisterRequestDto registerRequestDto) {

        if (ObjectUtils.isEmpty(registerRequestDto)) return null;

        Account accountRegister = accountAuthMapper.toEntity(registerRequestDto);
        accountRegister.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        return accountRegister;
    }

    private Member settingMemberInfo(RegisterRequestDto registerRequestDto, Long systemId) {

        if (ObjectUtils.isEmpty(registerRequestDto)) return null;

        Member memberMapper = memberAuthMapper.toEntity(registerRequestDto);
        memberMapper.setSystemId(systemId);
        return memberMapper;
    }

    private RoleAccount settingRoleAccount(RoleEnum roleEnum, Long systemId) {
        return RoleAccount.builder()
                .roleId(roleEnum)
                .systemId(systemId)
                .build();
    }

}
