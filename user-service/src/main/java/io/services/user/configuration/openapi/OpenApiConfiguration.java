package io.services.user.configuration.openapi;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OAuthFlowProperties.class)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI(OAuthFlowProperties properties) {

        OAuthFlow oAuthFlow = new OAuthFlow();
        oAuthFlow.authorizationUrl(properties.getAuthorizationUrl());
        oAuthFlow.setTokenUrl(properties.getTokenUrl());
        oAuthFlow.setScopes(properties.getScopes());

        OpenAPI openAPI = new OpenAPI()

                .info(
                        new Info()
                                .title("User Resource")
                                .contact(new Contact().email("support@iozone.com"))
                                .version("v1")
                )
                .components(new Components()
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .scheme("bearer")
                                .bearerFormat("jwt")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .flows(new OAuthFlows().authorizationCode(oAuthFlow))))
                .addSecurityItem(new SecurityRequirement().addList("oauth2"));

        return openAPI;
    }

}
