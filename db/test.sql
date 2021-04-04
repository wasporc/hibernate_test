create table if not exists product
(
	id bigserial not null
		constraint product_pkey
			primary key,
	name varchar(255),
	price double precision
);

alter table product owner to postgres;

INSERT INTO public.product (name, price) VALUES ('banana', 2.2);
INSERT INTO public.product (name, price) VALUES ('potato', 3.3);
INSERT INTO public.product (name, price) VALUES ('Green Apple', 4.2);
INSERT INTO public.product (name, price) VALUES ('Blue Apple', 5.2);
INSERT INTO public.product (name, price) VALUES ('Red Apple', 6.2);
INSERT INTO public.product (name, price) VALUES ('Black Apple', 7.2);
INSERT INTO public.product (name, price) VALUES ('Onion', 8.2);
INSERT INTO public.product (name, price) VALUES ('Fish', 9.2);
INSERT INTO public.product (name, price) VALUES ('Dog', 1.3);
INSERT INTO public.product (name, price) VALUES ('Car', 1.4);
INSERT INTO public.product (name, price) VALUES ('It', 1.5);
INSERT INTO public.product (name, price) VALUES ('Tea', 1.6);
INSERT INTO public.product (name, price) VALUES ('Juice', 1.7);
INSERT INTO public.product (name, price) VALUES ('Water', 1.8);
INSERT INTO public.product (name, price) VALUES ('Mineral water', 1.9);
INSERT INTO public.product (name, price) VALUES ('Beer', 3.3);
INSERT INTO public.product (name, price) VALUES ('Vodka', 4.3);
