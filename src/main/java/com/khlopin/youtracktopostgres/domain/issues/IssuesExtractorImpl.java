package com.khlopin.youtracktopostgres.domain.issues;

import com.khlopin.youtracktopostgres.database.IssuesRepository;
import com.khlopin.youtracktopostgres.domain.models.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service

@Scope
public class IssuesExtractorImpl implements IssuesExtractor {
    @Autowired
    List<IssueHandler> handlers;

    @Autowired
    IssuesRepository repository;

    @Autowired
    private WebClient webClient;



    @Value("${youtrack.auth}")
    private String auth;

    @Value("${youtrack.issue.fields}")
    String issueFields;

    @Value("${youtrack.issue.size}")
    Integer downloadSize;

    @PostConstruct
    @Override
    public List<String> getIssueIds() {
        boolean hasNext = true;
        int skip = 0;
        Set<String> issueIds = new LinkedHashSet<>();
        while (hasNext && issueIds.size() <= 100) {
            Issue[] issues = Objects.requireNonNull(webClient
                    .get()
                    .uri("api/issues?fields=" + issueFields + "&$top=10&$skip=" + skip + "&query=сортировать: создана по возр. ")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", auth)
                    .retrieve()
                    .toEntity(Issue[].class).block()).getBody();
            if (issues != null) {
                /*System.out.println();*/
                issueIds.addAll(Arrays.stream(issues).map(Issue::getIdReadable).collect(Collectors.toList()));
                hasNext = !(issues.length < downloadSize);
                skip += downloadSize;
            }
        }
        issueIds.forEach(System.out::println);
        return new ArrayList<>(issueIds);
    }


    @Override
    public Flux<Issue> getIssues() {
        return webClient
                .get()
                .uri("api/issues?fields=" + issueFields + "&$top=10")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", auth)
                .retrieve()
                .bodyToFlux(Issue.class);
    }


    @Override
    public Issue getIssueById(String idReadable) {
        return null;
    }


    private void init() {
        this.getIssues().toStream().forEach(item -> {
            handlers.forEach(handler -> handler.handle(item.getIdReadable()));
            repository.save(item.toDatabaseModel()).block();
        });
        /*List<Issue> list = webClient
                .get()
                .uri("api/issues?fields=" + issueFields + "&$top=10")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", auth)
                .retrieve()
                .bodyToFlux(Issue.class)
                .toStream()
                .collect(Collectors.toList());
        *//*list.forEach(System.out::println);*//*
        list.forEach(e -> repository.insert(e.getIdReadable()).subscribe());
        System.out.println("Issues size = " + list.size());
        list.forEach(e -> handlers.forEach(h -> h.handle(e.getIdReadable())));*/

    }


}
