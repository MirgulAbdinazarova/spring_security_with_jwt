package com.peaksoft.springsecuritywithjwt;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority {

    CUSTOMER,
    VENDOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
