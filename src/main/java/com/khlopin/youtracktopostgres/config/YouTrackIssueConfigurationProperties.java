package com.khlopin.youtracktopostgres.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

@Data
@Getter
@Setter
public class YouTrackIssueConfigurationProperties {
    Integer size;
    List<String> fields;
    @NestedConfigurationProperty
    YouTrackIssueCommentConfigurationProperties comment;
}
