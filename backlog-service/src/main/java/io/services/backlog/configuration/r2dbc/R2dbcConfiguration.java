package io.services.backlog.configuration.r2dbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
@PropertySource("classpath:sql-queries.properties")
public class R2dbcConfiguration {
}
