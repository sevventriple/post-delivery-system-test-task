create sequence ADDRESS_SEQ;

create table ADDRESS (
    ID bigint primary key not null,
    CITY character varying(255),
    DISTRICT character varying(255),
    HOME_NUMBER integer,
    REGION character varying(255),
    STREET character varying(255)
);

create sequence POST_OFFICE_SEQ;

create table POST_OFFICE (
    POST_INDEX integer primary key not null,
    NAME character varying(255),
    POSTAL_OFFICE_ADDRESS_ID bigint,
    foreign key (POSTAL_OFFICE_ADDRESS_ID) references public.address (ID)
    match simple on update no action on delete no action
);

create sequence POSTAL_DELIVERY_SEQ;

create table POSTAL_DELIVERY (
    ID bigint primary key not null,
    ADDRESSEE_NAME character varying(255),
    DELIVERY_STATUS character varying(255),
    POSTAL_ITEM_TYPE character varying(255),
    ADDRESSEE_ADDRESS_ID bigint,
    TARGET_POST_OFFICE_ID integer,
    foreign key (TARGET_POST_OFFICE_ID) references public.post_office (POST_INDEX)
    match simple on update no action on delete no action,
    foreign key (ADDRESSEE_ADDRESS_ID) references public.address (ID)
    match simple on update no action on delete no action
);

create sequence DELIVERY_EVENT_SEQ;

create table DELIVERY_EVENT (
    ID bigint primary key not null,
    DELIVERY_EVENT_TYPE character varying(255),
    TIME timestamp(6) with time zone,
    POST_OFFICE_ID integer,
    POSTAL_DELIVERY_ID bigint,
    foreign key (POSTAL_DELIVERY_ID) references public.postal_delivery (ID)
    match simple on update no action on delete no action,
    foreign key (POST_OFFICE_ID) references public.post_office (POST_INDEX)
    match simple on update no action on delete no action
);