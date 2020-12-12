package io.services.autoconfigure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * Jwt to JwtAuthenticationToken converter that set JwtAuthenticationToken
 * userDetails in addition to principal and authorities.
 */
public class JwtAuthenticationWithUserDetailsConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, UserDetails> jwtUserDetailsConverter;

    public JwtAuthenticationWithUserDetailsConverter(Converter<Jwt, UserDetails> jwtUserDetailsConverter) {
        this.jwtUserDetailsConverter = jwtUserDetailsConverter;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        UserDetails userDetails = jwtUserDetailsConverter.convert(jwt);
        AbstractAuthenticationToken authentication = new JwtAuthenticationToken(jwt, userDetails.getAuthorities());
        authentication.setDetails(userDetails);
        return authentication;
    }
}
