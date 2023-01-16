create sequence if not exists public.seq_todo;

create table if not exists public.todo
(
    id        integer default nextval('public.seq_todo'::regclass) not null,
    task      varchar,
    completed boolean,
    constraint todo_pk
        primary key (id)
);

insert into public.todo (task, completed)
select 'hit the gym', false
union
select 'pay bills', false
union
select 'read a book', false
union
select 'organize office', false