package com.khlopin.youtracktopostgres.database;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class InfrastructureConfiguration {

    @Configuration
    @EnableR2dbcRepositories
    public class TestDatabaseConfig extends AbstractR2dbcConfiguration {
        @Value("${datasource.host}")
        private String host;
        @Value("${datasource.port}")
        private int port;
        @Value("${datasource.database.test}")
        private String database;
        @Value("${datasource.username}")
        private String username;
        @Value("${datasource.password}")
        private String password;


        @Override
        @Bean(name = "testDatabase")
        public @NonNull PostgresqlConnectionFactory connectionFactory() {
            return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                    .builder()
                    .host(host)
                    .database(database)
                    .username(username)
                    .password(password)
                    .port(port)
                    .build());
        }
    }
}
