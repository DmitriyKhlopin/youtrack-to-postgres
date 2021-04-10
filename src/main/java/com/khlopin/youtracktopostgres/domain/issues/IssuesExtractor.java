package com.khlopin.youtracktopostgres.domain.issues;

import com.khlopin.youtracktopostgres.domain.models.Issue;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IssuesExtractor {
    List<String> getIssueIds();

    Flux<Issue> getIssues();

    Issue getIssueById(String idReadable);
}
