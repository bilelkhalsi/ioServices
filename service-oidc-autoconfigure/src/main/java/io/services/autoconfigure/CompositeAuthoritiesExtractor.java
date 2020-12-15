package io.services.autoconfigure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Composite converter that takes a list of Jwt to Collection<GrantedAuthority> converter and apply them on Jwt
 * and return the whole list of GrantedAuthority.
 */
public class CompositeAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final List<Converter<Jwt, Collection<GrantedAuthority>>> converterList;

    public CompositeAuthoritiesExtractor(Converter<Jwt, Collection<GrantedAuthority>>... converterList) {
        this.converterList = Optional.ofNullable(converterList)
                .map(Arrays::asList)
                .orElseGet(Collections::emptyList);
    }

    public CompositeAuthoritiesExtractor(List<Converter<Jwt, Collection<GrantedAuthority>>> converterList) {
        this.converterList = Optional.ofNullable(converterList)
                .orElseGet(Collections::emptyList);
    }

    public Collection<GrantedAuthority> convert(Jwt jwt) {
        return converterList.stream().map(converter -> converter.convert(jwt))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }
}
