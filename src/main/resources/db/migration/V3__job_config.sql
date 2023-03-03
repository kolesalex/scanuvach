-- -----------------------------------------------------
-- Table `scanuvach`.`job_config`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `job_config` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `job_name` VARCHAR(190) NULL,
  `key` VARCHAR(512) NULL,
  `value` VARCHAR(512) NULL,
  PRIMARY KEY (`id`));


INSERT INTO
    job_config (job_name, `key`, `value`)
VALUES
    ('nmapPortsJob', 'nmap.ips.to.scan', '172.24.0.0/16 172.25.0.0/16 172.26.0.0/16 172.27.0.0/16 172.28.0.0/16 '),
    ('nmapPortsJob', 'nmap.cron.expression', '0 0 1 * * ?'), -- Every day at 1am
    ('nmapPortsJob', 'nmap.ports.to.scan', '23,80,5060,5061'),
    ('nmapPortsJob', 'nmap.command', 'nmap -p %s -oX - target %s');
