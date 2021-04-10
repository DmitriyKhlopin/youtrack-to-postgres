package com.khlopin.youtracktopostgres.database;


import com.khlopin.youtracktopostgres.database.models.Issue;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface IssuesRepository extends ReactiveCrudRepository<Issue, String> {
    @Query("select  * from issues where id_readable = :idReabale")
    Flux<Issue> findByIdReadable(String idReadable);

    /*@Override
    @Query("insert into issues (id_readable, created, updated) values (:#{#entity.idReadable},:#{#entity.created},:#{#entity.updated}) on conflict do nothing")
    Mono<Issue> save(Issue entity);*/

    /*@Query("select id_readable from issues")
    Flux<Issue> getAll();

    @Query("select id_readable from issues where id_readable = :idReadable")
    Mono<Issue> getById(String idReadable);

    @Query("insert into issues (id_readable) values(:idReadable) on conflict do nothing")
    Mono<Issue> insert(Issue issue);

    @Query("insert into issues (id_readable) values(:issue.idReadable) on conflict do nothing")
    Mono<Issue> save(Issue issue);


    @Query("delete from issues")
    Mono<Void> deleteAll();*/
}
