CREATE TABLE component_details (id INT AUTO_INCREMENT PRIMARY KEY, component_name VARCHAR(45),component_identifier VARCHAR(45) ,created_date date DEFAULT NULL,updated_date date DEFAULT NULL );
INSERT INTO component_details (id, component_name, component_identifier,created_date,updated_date) VALUES (1,'Unique Component','94ffcdfb-64a2-4f5b-9baa-81cde9cfe754',now(),now());
