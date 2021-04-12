package com.khlopin.youtracktopostgres.database.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table(value = "issues")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Issue {
    @Id
    Long id;
    @NonNull
    @Column("id_readable")
    String idReadable;
    Timestamp created;
    Timestamp updated;
    Timestamp resolved;
    @Column("number_in_project")
    Long numberInProject;
    String summary;
    String description;
    @Column("uses_markdown")
    Boolean usesMarkdown;
    @Column("wikified_description")
    String wikifiedDescription;
    @Column("is_draft")
    Boolean isDraft;
    Integer votes;
    @Column("comments_count")
    Integer commentsCount;

    public Issue(@NonNull String idReadable, Timestamp created, Timestamp updated) {
        this.idReadable = idReadable;
        this.created = created;
        this.updated = updated;
    }
}
