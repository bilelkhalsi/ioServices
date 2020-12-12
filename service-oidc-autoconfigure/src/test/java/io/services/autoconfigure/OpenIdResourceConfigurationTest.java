package io.services.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.assertj.core.api.Assertions.assertThat;


class OpenIdAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(OpenIdResourceAutoConfiguration.class));


    @Test
    void defaultServiceBacksOff() {
        this.contextRunner.withUserConfiguration(OpenIdConfiguration.class)
                .run((context) -> {
                    assertThat(context).hasSingleBean(SecurityWebFilterChain.class);
                    assertThat(context.getBean(SecurityWebFilterChain.class)).isSameAs(
                            context.getBean(OpenIdConfiguration.class).springSecurityFilterChain());
                });
    }


    @Configuration
    static class OpenIdConfiguration {


        @Bean
        public SecurityWebFilterChain springSecurityFilterChain() {
            return ServerHttpSecurity.http().build();
        }
    }

}