create table books (
id     int GENERATED ALWAYS AS IDENTITY (cache 100),
name   varchar(255) NULL,
author varchar(255) NULL,
price  varchar(255) NULL
);