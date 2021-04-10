package com.khlopin.youtracktopostgres.domain.issues.comments;

import com.khlopin.youtracktopostgres.domain.issues.IssueHandler;
import com.khlopin.youtracktopostgres.domain.models.IssueComment;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Order(value = 2)
public class IssueCommentsExtractorImpl implements IssueCommentsExtractor, IssueHandler {
    @SneakyThrows
    @Override
    public List<IssueComment> getIssueComments(String idReadable) {
        return null;
    }

    @Override
    public void handle(String idReadable) {
        System.out.println("Getting comments of issue " + idReadable);
    }
}
