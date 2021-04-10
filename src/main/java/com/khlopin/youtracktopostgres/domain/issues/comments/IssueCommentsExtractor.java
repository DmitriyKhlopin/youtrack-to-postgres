package com.khlopin.youtracktopostgres.domain.issues.comments;

import com.khlopin.youtracktopostgres.domain.models.IssueComment;

import java.util.List;

public interface IssueCommentsExtractor {
    List<IssueComment> getIssueComments(String idReadable);
}
