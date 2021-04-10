package com.khlopin.youtracktopostgres.web.issues;


import com.khlopin.youtracktopostgres.database.IssuesRepository;
import com.khlopin.youtracktopostgres.database.models.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class IssuesController {
    @Autowired
    IssuesRepository repository;

    @GetMapping("api/issues")
    Flux<Issue> getAll() {
        return repository.findAll();
    }

    @GetMapping("api/issues/{idReadable}")
    Mono<Issue> getById(@PathVariable("idReadable") String idReadable) {
        return repository.findById(idReadable);
    }
}
