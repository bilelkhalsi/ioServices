package io.services.autoconfigure;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

/**
 * Converter that transform Jwt claims to UserDetails
 */
public class JwtUserDetailsConverter implements Converter<Jwt, UserDetails> {
    private static final String WELL_KNOWN_EMAIL_CLAIM_NAME = "email";
    private static final String WELL_KNOWN_USERNAME_CLAIM_NAME = "preferred_username";
    private static final String WELL_KNOWN_FIRST_NAME_CLAIM_NAME = "given_name";
    private static final String WELL_KNOWN_LAST_NAME_CLAIM_NAME = "family_name";
    private static final String WELL_KNOWN_LOCALE_CLAIM_NAME = "locale";

    private final Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter;

    /**
     * Jwt claims to UserDetails converter constructor.
     *
     * @param jwtGrantedAuthoritiesConverter to get granted authorities from Jwt claims.
     */
    public JwtUserDetailsConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
    }

    @Override
    public UserDetails convert(Jwt jwt) {
        Map<String, Object> claims = jwt.getClaims();
        String email = (String) claims.getOrDefault(WELL_KNOWN_EMAIL_CLAIM_NAME, Strings.EMPTY);
        String username = (String) claims.getOrDefault(WELL_KNOWN_USERNAME_CLAIM_NAME, Strings.EMPTY);
        String firstName = (String) claims.getOrDefault(WELL_KNOWN_FIRST_NAME_CLAIM_NAME, Strings.EMPTY);
        String lastName = (String) claims.getOrDefault(WELL_KNOWN_LAST_NAME_CLAIM_NAME, Strings.EMPTY);
        Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
        String locale = (String) claims.getOrDefault(WELL_KNOWN_LOCALE_CLAIM_NAME, Locale.ENGLISH.getLanguage());
        return ImmutableIoUserDetails
                .builder()
                .id(jwt.getSubject())
                .email(email)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .authorities(authorities)
                .locale(locale)
                .password(Strings.EMPTY)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .build();
    }
}
