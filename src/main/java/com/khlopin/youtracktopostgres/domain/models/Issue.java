package com.khlopin.youtracktopostgres.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @NonNull
    @Id
    @Column("id_readable")
    String idReadable;
    Long created;
    Long updated;
    Long resolved;
    Long numberInProject;
    Project project;
    String summary;
    String description;
    Boolean usesMarkdown;
    String wikifiedDescription;
    User reporter;
    User updater;
    User draftOwner;
    Boolean isDraft;
    Visibility visibility;
    Integer votes;
    List<IssueComment> comments;
    Integer commentsCount;

    public Issue(@NonNull String idReadable) {
        this.idReadable = idReadable;
    }

    public Issue(@NonNull String idReadable, Long created) {
        this.idReadable = idReadable;
        this.created = created;
    }

    public com.khlopin.youtracktopostgres.database.models.Issue toDatabaseModel() {
        com.khlopin.youtracktopostgres.database.models.Issue issue = new com.khlopin.youtracktopostgres.database.models.Issue();
        issue.setIdReadable(this.idReadable);
        issue.setCreated(Timestamp.from(Instant.ofEpochMilli(this.created)));
        issue.setUpdated(Timestamp.from(Instant.ofEpochMilli(this.updated)));
        return issue;
    }
}
