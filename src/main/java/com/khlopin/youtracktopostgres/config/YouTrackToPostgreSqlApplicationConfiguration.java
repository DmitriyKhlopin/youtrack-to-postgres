package com.khlopin.youtracktopostgres.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(YouTrackConfigurationProperties.class)
public class YouTrackToPostgreSqlApplicationConfiguration {
}
