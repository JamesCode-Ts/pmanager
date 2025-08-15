package com.java360.pmanager.infrastructure.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final  String apikey;

    public ApiKeyAuthenticationToken(String apikey){
        super(NO_AUTHORITIES);
        this.apikey = apikey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apikey;
    }
}
