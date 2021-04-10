package com.khlopin.youtracktopostgres.domain.models;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IssueTest {
    @Test
    void create() {
        Issue issue = new Issue("TEST-1");
        assertEquals("TEST-1", issue.idReadable);
        MatcherAssert.assertThat("TEST-1", Matchers.equalTo(issue.getIdReadable()));
        MatcherAssert.assertThat(issue.getIdReadable(), new IssueIdMatcher());

        Issue wrongIssue = new Issue("TEST--1");
        MatcherAssert.assertThat(wrongIssue.getIdReadable(), new IsNot<>(new IssueIdMatcher()));
        Assertions.assertThat(wrongIssue.getIdReadable()).isEqualToIgnoringCase("test--1");
    }
}
