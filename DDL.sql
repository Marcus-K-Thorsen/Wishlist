CREATE DATABASE genie;
USE genie;

CREATE TABLE users
(
    email     varchar(50) NOT NULL UNIQUE,
    pw        varchar(50) NOT NULL,
    givenName varchar(50) NOT NULL,
    lastName  varchar(50) NOT NULL,
    amount_WL int         NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE wishlists
(
    id_WL    int         NOT NULL UNIQUE AUTO_INCREMENT,
    email    varchar(50),
    titel_WL varchar(50) NOT NULL,
    descr_WL varchar(200),
    PRIMARY KEY (id_WL),
    FOREIGN KEY (email) REFERENCES users (email)
);

CREATE TABLE wishes
(
    id_W    int         NOT NULL UNIQUE AUTO_INCREMENT,
    id_WL   int,
    titel_W varchar(50) NOT NULL,
    descr_W varchar(200),
    price   decimal(10, 2),
    link    varchar(200),
    PRIMARY KEY (id_W),
    FOREIGN KEY (id_WL) REFERENCES wishlists (id_WL)
);

DROP DATABASE genie;