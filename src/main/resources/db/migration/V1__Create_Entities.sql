CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table payment_provider
(
    id          bigserial
        primary key,
    name        varchar(50) not null,
    description varchar(256)
);

create table payment_method
(
    id                   bigserial
        primary key,
    type                 varchar(32)                                         not null,
    created_at           timestamp   default now()                           not null,
    modified_at          timestamp,
    name                 varchar(64)                                         not null,
    is_active            boolean     default true,
    provider_unique_id   varchar(128)                                        not null,
    provider_method_type varchar(32)                                         not null,
    logo                 text,
    profile_type         varchar(24) default 'INDIVIDUAL'::character varying not null,
    provider_id          integer     default 1                               not null
        references payment_provider
            on delete cascade
);

create table payment_method_meta_info
(
    id                bigserial
        primary key,
    payment_method_id bigint not null
        references payment_method
            on delete cascade,
    country           varchar(32),
    required_fields   text
);

create table payment_method_definitions
(
    payment_method_id   bigint not null
        references payment_method
            on delete cascade,
    currency_code       varchar(3),
    country_alpha3_code varchar(3),
    is_all_currencies   boolean default false,
    is_all_countries    boolean default false,
    is_priority         boolean,
    id                  bigserial
        primary key,
    is_active           boolean default true
);

create table payment_method_required_fields
(
    uid                 uuid      default uuid_generate_v4() not null
        primary key,
    created_at          timestamp default now()              not null,
    modified_at         timestamp default now()              not null,
    payment_method_id   bigint                               not null
        references payment_method,
    payment_type        varchar(64)                          not null,
    country_alpha3_code varchar(3),
    name                varchar(128)                         not null,
    data_type           varchar(128)                         not null,
    validation_type     varchar(128),
    validation_rule     varchar(128),
    default_value       varchar(128),
    values_options      text,
    description         varchar(255),
    placeholder         varchar(255),
    representation_name varchar(255),
    language            varchar(2),
    is_active           boolean   default true,
    constraint lang_name_method_id_type_country
        unique (language, name, payment_method_id, payment_type, country_alpha3_code)
);

create table security_rules_type
(
    id                      serial
        primary key,
    created_at              timestamp default now(),
    modified_at             timestamp default now(),
    rule_type               varchar(50) not null,
    transaction_type        varchar(50),
    is_all_transaction_type boolean,
    payment_method_id       integer
        references payment_method,
    amount_to               integer,
    amount_from             integer,
    time_value              integer,
    time_definition         varchar(50),
    transactions_count      integer,
    status                  varchar(50),
    constraint check_amount_range
        check (amount_to >= amount_from)
);

create table security_triggered_rules
(
    id                  serial
        primary key,
    payment_request_uid uuid,
    general_rule        boolean,
    profile_uid         uuid         not null,
    wallet_uid          uuid         not null,
    created_at          timestamp default now(),
    modified_at         timestamp default now(),
    rule_type           varchar(255) not null,
    rule_id             integer references security_rules_type on delete cascade,
    status              varchar(255) not null
);


create table personal_security_rules_type
(
    id                      serial
        primary key,
    created_at              timestamp default now(),
    profile_uid             uuid        not null,
    modified_at             timestamp default now(),
    rule_type               varchar(50) not null,
    transaction_type        varchar(50),
    is_all_transaction_type boolean,
    payment_method_id       integer
        references payment_method,
    amount_to               integer,
    amount_from             integer,
    time_value              integer,
    time_definition         varchar(50),
    transactions_count      integer,
    status                  varchar,
    constraint check_amount_range
        check (amount_to >= amount_from)
);