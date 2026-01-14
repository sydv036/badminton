package com.example.tob.configuration.mapper;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtils.isNotEmpty(authentication) && authentication.getAuthorities()
                .stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ANONYMOUS"))) {
            return Optional.of(authentication.getName());
        }
        return Optional.of("guest");
    }
}
