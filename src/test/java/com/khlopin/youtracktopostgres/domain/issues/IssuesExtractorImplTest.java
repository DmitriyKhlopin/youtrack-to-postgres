package com.khlopin.youtracktopostgres.domain.issues;

import com.khlopin.youtracktopostgres.domain.models.Issue;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class IssuesExtractorImplTest {


    @SneakyThrows
    @Test
    void getIssueIdsTest() {
        Issue issue = new Issue("TEST-1");
        /*when(webClientMock.get())*/
    }

    @Test
    void testGetIssueIds() {
    }

    @Test
    void getIssues() {
    }

    @Test
    void getIssueById() {
    }
}
