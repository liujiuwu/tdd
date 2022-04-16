CREATE TABLE books
(
    id     BIGINT UNSIGNED AUTO_INCREMENT,
    name   varchar(255) DEFAULT NULL,
    author varchar(255) DEFAULT NULL,
    price  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);