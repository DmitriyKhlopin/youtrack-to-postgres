package com.khlopin.youtracktopostgres.domain.issues.fields;

import com.khlopin.youtracktopostgres.domain.issues.IssueHandler;
import com.khlopin.youtracktopostgres.domain.models.IssueCustomField;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Order(value = 1)
public class IssueCustomFieldsExtractorImpl implements IssueCustomFieldsExtractor, IssueHandler {


    @Override
    public List<IssueCustomField> getIssueCustomFields(String idReadable) {
        return new ArrayList<>();
    }

    @Override
    public void handle(String idReadable) {
        System.out.println("Getting custom fields of issue " + idReadable);
    }
}
