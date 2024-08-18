# myuser / 1234 계정 생성후, 데이터베이스 spring 에 대한 모든 권한 부여
# CREATE USER 'myuser'@'%' IDENTIFIED BY '1234';
# GRANT ALL PRIVILEGES ON spring.* TO 'myuser'@'%';

CREATE TABLE IF NOT EXISTS `spring`.`users`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(45) NOT NULL,
    `password`  TEXT        NOT NULL,
    `algorithm` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `spring`.`authorities`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NOT NULL,
    `user_entity` INT         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `spring`.`products`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NOT NULL,
    `price`    VARCHAR(45) NOT NULL,
    `currency` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);