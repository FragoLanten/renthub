package ru.jdbcfighters.renthub.domain.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MANAGER,
    BUYER,
    SELLER;

    @Override
    public String getAuthority() {
        return name();
    }
}
