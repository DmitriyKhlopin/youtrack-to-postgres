package com.khlopin.youtracktopostgres.domain.issues.workitems;

import com.khlopin.youtracktopostgres.domain.issues.IssueHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(value =  3)
public class IssueWorkItemExtractorImpl implements IssueWorkItemExtractor, IssueHandler {

    @Override
    public void handle(String idReadable) {
        System.out.println("Getting work items of issue " + idReadable);
    }
}
