create sequence vehicle_id_seq start with 10 increment by 50;

create table vehicles (
    id bigint default vehicle_id_seq.nextval,
    name  varchar(255) not null,
	brand varchar(255) not null,
	model varchar(255) not null,
	factory_date timestamp,
	street_average_fuel double,	
	road_average_fuel double,
    primary key (id),
    UNIQUE KEY user_name_unique (name)
);