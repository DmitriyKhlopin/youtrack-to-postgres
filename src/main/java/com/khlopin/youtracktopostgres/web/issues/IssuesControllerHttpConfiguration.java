package com.khlopin.youtracktopostgres.web.issues;

import com.khlopin.youtracktopostgres.database.IssuesRepository;
import com.khlopin.youtracktopostgres.database.models.Issue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class IssuesControllerHttpConfiguration {
    @Bean
    RouterFunction<ServerResponse> routes(IssuesRepository repository) {
        return route()
                .GET("/api/issues", request -> ServerResponse.ok().body(repository.findAll(), Issue.class))
                .GET("/api/issues/{idReadable}", request -> ServerResponse.ok().body(repository.findByIdReadable(request.pathVariable("idReadable")), Issue.class))
                .build();
    }
}
