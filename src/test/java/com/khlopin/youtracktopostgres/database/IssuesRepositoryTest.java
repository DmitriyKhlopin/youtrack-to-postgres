package com.khlopin.youtracktopostgres.database;


import com.khlopin.youtracktopostgres.database.models.Issue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataR2dbcTest /*Указать базу для тестов можно с помощью properties = "datasource.database:test"*/
class IssuesRepositoryTest {
    @Autowired
    IssuesRepository issuesRepository;

    @Test
    public void getAll() {

        Flux<Issue> issues = issuesRepository.deleteAll()
                .thenMany(Flux.just("TEST-1", "TEST-2").map(e -> new Issue(e, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())))
                        .flatMap(issue -> issuesRepository.save(issue))
                        .thenMany(issuesRepository.findByIdReadable("TEST-2")));
        List<Issue> i = issues.collect(Collectors.toList()).block();
        assert i != null;
        Assertions.assertTrue(i.size() != 0);
    }

    @Test
    public void persist() {
        Issue issue = new Issue("TEST-1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        Mono<Issue> save = this.issuesRepository.deleteAll().then(this.issuesRepository.save(issue));
        StepVerifier
                .create(save)
                .expectNextMatches(i -> i.getIdReadable().equals(issue.getIdReadable()) && i.getCreated().equals(issue.getCreated()))
                .verifyComplete();
    }


    @Test
    void getByIdReadable() {
        //delete everything
        //add 4 records
        //query by idReadable
        Flux<Issue> issueFlux = this.issuesRepository.deleteAll()
                .thenMany(
                        Flux.just("TEST-1", "TEST-2", "TEST-3", "TEST-4")
                                .map(e -> new Issue(e, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())))
                                .flatMap(r -> this.issuesRepository.save(r))
                                .thenMany(this.issuesRepository.findByIdReadable("TEST-2"))
                );
        StepVerifier.create(issueFlux).expectNextCount(1).verifyComplete();

    }
}
