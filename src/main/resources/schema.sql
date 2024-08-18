DROP TABLE coffee IF EXISTS;
DROP TABLE batch_process_overview IF EXISTS;

CREATE TABLE coffee(
    coffee_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    brand VARCHAR(20),
    origin VARCHAR(20),
    characteristics VARCHAR(30)
);

CREATE TABLE batch_process_overview(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    job_name VARCHAR(255) NOT NULL,
    job_instance_id BIGINT NOT NULL,
    job_execution_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    exit_code VARCHAR(50),
    exit_message TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);