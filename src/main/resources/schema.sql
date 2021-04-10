create table if not exists issues
(
    id                   bigserial not null,
    id_readable          text      not null
        constraint issues_pk
            primary key,
    created              timestamp,
    updated              timestamp,
    resolved             timestamp,
    number_in_project    integer,
    summary              text,
    description          text,
    uses_markdown        boolean,
    wikified_description text,
    is_draft             boolean,
    votes                integer,
    comments_count       integer
);

alter table issues
    owner to postgres;

create unique index issues_id_readable_uindex
    on issues (id_readable);

create unique index issues_id_uindex
    on issues (id);

