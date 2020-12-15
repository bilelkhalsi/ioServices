package io.services.autoconfigure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Jwt to user granted roles converter.
 */
public final class GrantedRolesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String AUTHORITY_PREFIX = "ROLE_";
    private static final List<String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES = Arrays.asList("realm_access");
    private static final String WELL_KNOWN_ROLES_FIELD_NAME = "roles";


    public Collection<GrantedAuthority> convert(Jwt jwt) {

        List<String> claimRoles = WELL_KNOWN_AUTHORITIES_CLAIM_NAMES.stream()
                .filter(name -> jwt.getClaims().containsKey(name))
                .map(name -> (Map<String, Object>) jwt.getClaims().get(name))
                .map(claimAuthorities -> (List<String>) claimAuthorities.getOrDefault(WELL_KNOWN_ROLES_FIELD_NAME, Collections.emptyList()))
                .findFirst()
                .orElse(Collections.emptyList());

        return claimRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(AUTHORITY_PREFIX + role))
                .collect(Collectors.toList());
    }
}