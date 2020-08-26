create sequence vehicle_id_seq start with 10 increment by 50;

create table vehicles (
    id bigint DEFAULT nextval('vehicle_id_seq') not null,
    name  varchar(255) not null CONSTRAINT vehicle_name_unique UNIQUE,
	brand varchar(255) not null,
	model varchar(255) not null,
	factory_date timestamp,
	street_average_fuel float ,	
	road_average_fuel float8,
    primary key (id)
);