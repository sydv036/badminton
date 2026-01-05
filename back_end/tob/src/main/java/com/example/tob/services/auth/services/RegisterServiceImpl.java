package com.example.tob.services.auth.services;

import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.entity.Account;
import com.example.tob.entity.Member;
import com.example.tob.exceptions.BusinessException;
import com.example.tob.repository.IAccountRepository;
import com.example.tob.repository.IMemberRepository;
import com.example.tob.services.auth.interfaces.IRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegisterServiceImpl implements IRegisterService {

    private final IMemberRepository memberRepository;

    private final IAccountRepository accountRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

    private static final String MESE002 = "MESE002";
    private static final String MESI003 = "MESI003";

    /**
     * Handle register
     *
     * @param registerRequestDto info register
     * @return email of user registered
     */
    @Override
    public String handlerRegister(RegisterRequestDto registerRequestDto) {
        try {

            String emailRegister = registerRequestDto.getEmail();

            // Handle processing account
            Account accountRegister = new Account();
            accountRegister.setUserName(emailRegister);
            accountRegister.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
            accountRegister.setLocked(false);
            accountRegister.setActived(true);
            accountRegister.setCreatedBy(emailRegister);
            accountRegister.setUpdatedBy(emailRegister);

            accountRegister = accountRepository.saveAndFlush(accountRegister);

            // Handle processing member
            Member memberMapper = modelMapper.map(registerRequestDto, Member.class);
            memberMapper.setSystemId(accountRegister.getSystemId());
            memberMapper.setUserName(emailRegister);
            memberMapper.setCreatedBy(emailRegister);
            memberMapper.setUpdatedBy(emailRegister);

            memberRepository.save(memberMapper);

            log.info(messageSource.getMessage(MESI003, new String[]{emailRegister}, Locale.getDefault()));
            return registerRequestDto.getEmail();

        } catch (RuntimeException e) {
            String[] args = new String[]{registerRequestDto.getEmail(), e.getMessage()};
            log.error(messageSource.getMessage(MESE002, args, Locale.getDefault()));
            throw new BusinessException(e);
        }
    }

}
