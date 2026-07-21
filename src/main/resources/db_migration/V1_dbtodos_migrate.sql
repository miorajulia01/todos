create extension if not exists "uuid-ossp";

create table if not exists "todo"(
    id varchar primary key default uuid_generate_v4(),
    title varchar (255) not null,
    description text,
    is_completed boolean default false,
    created_at timestamptz default now(),
    updated_at timestamptz default now()
);
