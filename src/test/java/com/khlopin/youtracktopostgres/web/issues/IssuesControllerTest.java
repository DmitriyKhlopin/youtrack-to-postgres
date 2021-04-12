package com.khlopin.youtracktopostgres.web.issues;

import com.khlopin.youtracktopostgres.database.IssuesRepository;
import com.khlopin.youtracktopostgres.database.models.Issue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@WebFluxTest(value = IssuesControllerHttpConfiguration.class)
@RunWith(SpringRunner.class)
class IssuesControllerTest {

    @MockBean
    private IssuesRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAll() {
        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(new Issue("TEST-1"), new Issue("TEST-2")));

        this.webTestClient
                .get()
                .uri("/api/issues")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("@.[0].idReadable").isEqualTo("TEST-1")
                .jsonPath("@.[1].idReadable").isEqualTo("TEST-2")
                .jsonPath("@.[2]").doesNotExist();
    }

    @Test
    void getById() {
        Mockito.when(repository.findByIdReadable("TEST-1"))
                .thenReturn(Mono.just(new Issue("TEST-1")));

        this.webTestClient
                .get()
                .uri("/api/issues/TEST-1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("@.idReadable").isEqualTo("TEST-1");
    }
}
