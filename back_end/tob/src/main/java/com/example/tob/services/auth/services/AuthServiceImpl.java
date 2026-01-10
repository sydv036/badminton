package com.example.tob.services.auth.services;

import com.example.tob.common.enums.RoleEnum;
import com.example.tob.dtos.requests.LoginRequestDto;
import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.dtos.responses.auth.LoginResponse;
import com.example.tob.dtos.responses.auth.MemberInfoResponse;
import com.example.tob.entity.Account;
import com.example.tob.entity.Member;
import com.example.tob.entity.RoleAccount;
import com.example.tob.exceptions.BusinessException;
import com.example.tob.repository.IAccountRepository;
import com.example.tob.repository.IAccountRoleRepository;
import com.example.tob.repository.IMemberRepository;
import com.example.tob.services.auth.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
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

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

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
    public LoginResponse handlerLogin(LoginRequestDto loginRequestDto) {
        try {

            LoginResponse loginResponse = getMemberInfo(loginRequestDto);
            log.info("loginRes: {}", loginResponse);
            return loginResponse;

        } catch (RuntimeException e) {
            String[] args = new String[]{loginRequestDto.getEmail(), e.getMessage()};
            log.error(messageSource.getMessage(MESE003, args, Locale.getDefault()));
            throw new BusinessException(e);
        }

    }

    private LoginResponse getMemberInfo(LoginRequestDto loginRequestDto) {

        MemberInfoResponse account = accountRepository.findByUserName(loginRequestDto.getEmail());
        Set<String> roles = accountRoleRepository.findBySystemId(account.getSystemId());

        return LoginResponse.builder()
                .systemId(account.getSystemId())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .roles(roles)
                .build();
    }

    private Account settingAccountInfo(RegisterRequestDto registerRequestDto) {
        String emailRegister = registerRequestDto.getEmail();

        Account accountRegister = new Account();
        accountRegister.setUserName(emailRegister);
        accountRegister.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        accountRegister.setLocked(false);
        accountRegister.setActived(true);
        accountRegister.setCreatedBy(emailRegister);
        accountRegister.setUpdatedBy(emailRegister);

        return accountRegister;
    }

    private Member settingMemberInfo(RegisterRequestDto registerRequestDto, Long systemId) {
        String emailRegister = registerRequestDto.getEmail();

        Member memberMapper = modelMapper.map(registerRequestDto, Member.class);
        memberMapper.setSystemId(systemId);
        memberMapper.setUserName(emailRegister);
        memberMapper.setCreatedBy(emailRegister);
        memberMapper.setUpdatedBy(emailRegister);

        return memberMapper;
    }

    private RoleAccount settingRoleAccount(RoleEnum roleEnum, Long systemId) {
        return RoleAccount.builder()
                .roleId(roleEnum)
                .systemId(systemId)
                .build();
    }

}
