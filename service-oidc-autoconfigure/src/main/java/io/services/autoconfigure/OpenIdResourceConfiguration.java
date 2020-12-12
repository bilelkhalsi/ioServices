package io.services.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class OpenIdResourceConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf()
                .disable()
                .authorizeExchange(
                        exchanges -> exchanges
                                // OpenApi json document is public.
                                .pathMatchers("/v3/api-docs/**").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor()));
                })
                .build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        // Jwt to GrantedAuthority's converter
        Converter<Jwt, Collection<GrantedAuthority>> jwtAuthoritiesConverter = new CompositeAuthoritiesExtractor(
                new GrantedRolesExtractor(),
                new JwtGrantedAuthoritiesConverter()
        );
        // Jwt to UserDetails converter
        Converter<Jwt, UserDetails> jwtUserDetailsConverter = new JwtUserDetailsConverter(jwtAuthoritiesConverter);
        // Jwt to AbstractAuthenticationToken converter
        Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter = new JwtAuthenticationWithUserDetailsConverter(jwtUserDetailsConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
