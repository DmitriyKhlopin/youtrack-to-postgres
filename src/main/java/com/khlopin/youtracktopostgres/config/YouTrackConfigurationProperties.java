package com.khlopin.youtracktopostgres.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties("youtrack")
public class YouTrackConfigurationProperties {
    String url;
    Integer timeout;
    String auth;
    @NestedConfigurationProperty
    YouTrackIssueConfigurationProperties issue;
}
