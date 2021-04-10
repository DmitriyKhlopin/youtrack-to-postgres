package com.khlopin.youtracktopostgres.domain.models;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Arrays;

public class IssueIdMatcher extends BaseMatcher<String> {
    @Override
    public boolean matches(Object actual) {
        return ((String) actual).contains("-") && Arrays.stream(((String) actual).split("")).filter(item -> item.equals("-")).count() == 1;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Valid issue id should contain exactly 1 `-`");
    }
}
