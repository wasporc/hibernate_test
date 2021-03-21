create table if not exists persons
(
	id bigserial not null
		constraint persons_pkey
			primary key,
	first_name varchar(255),
	last_name varchar(255)
);

alter table persons owner to postgres;

create table if not exists product
(
	id bigserial not null
		constraint product_pkey
			primary key,
	name varchar(255),
	price double precision
);

alter table product owner to postgres;

create table if not exists persons_product
(
	id bigserial not null
		constraint persons_product_pkey
			primary key,
	persons_id bigint not null
		constraint person_fk
			references persons,
	product_id bigint not null
		constraint product_fk
			references product
);

alter table persons_product owner to postgres;

INSERT INTO public.persons (id, first_name, last_name) VALUES (1, 'p1', 'p1');
INSERT INTO public.persons (id, first_name, last_name) VALUES (2, 'p2', 'p2');
INSERT INTO public.persons (id, first_name, last_name) VALUES (3, 'p3', 'p3');

INSERT INTO public.persons_product (id, persons_id, product_id) VALUES (1, 1, 1);
INSERT INTO public.persons_product (id, persons_id, product_id) VALUES (2, 1, 2);
INSERT INTO public.persons_product (id, persons_id, product_id) VALUES (3, 1, 3);
INSERT INTO public.persons_product (id, persons_id, product_id) VALUES (4, 2, 1);
INSERT INTO public.persons_product (id, persons_id, product_id) VALUES (5, 3, 2);

INSERT INTO public.product (id, name, price) VALUES (2, 'banana', 2.2);
INSERT INTO public.product (id, name, price) VALUES (3, 'potato', 3.3);
INSERT INTO public.product (id, name, price) VALUES (1, 'Green Apple', 1.2);
INSERT INTO public.product (id, name, price) VALUES (5, 'Red Apple', 1.5);
INSERT INTO public.product (id, name, price) VALUES (6, 'Red Apple', 1.5);