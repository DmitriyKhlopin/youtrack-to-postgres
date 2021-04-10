package com.khlopin.youtracktopostgres.domain.issues.fields;

import com.khlopin.youtracktopostgres.domain.models.IssueCustomField;

import java.util.List;

public interface IssueCustomFieldsExtractor {
    List<IssueCustomField> getIssueCustomFields(String idReadable);
}
