
CREATE TABLE hosts (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NULL,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE ports (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `host_id` BIGINT NULL,
  `port` INT NULL,
  `status` VARCHAR(45) NULL,
  `service` VARCHAR(45) NULL,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_host_idx` (`host_id` ASC),
  CONSTRAINT `fk_host`
    FOREIGN KEY (`host_id`)
    REFERENCES `hosts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
