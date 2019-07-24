DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
  ticket_id INT NOT NULL AUTO_INCREMENT,
  ticket_direction VARCHAR(255) NOT NULL,
  PRIMARY KEY (ticket_id)
);

-- DROP TABLE IF EXISTS employee;
-- CREATE TABLE employee (
--   employee_id INT NOT NULL AUTO_INCREMENT,
--   first_name VARCHAR(255) NOT NULL,
--   last_name VARCHAR(255) NOT NULL,
--   email VARCHAR(255) NOT NULL UNIQUE,
--   salary INT NOT NULL,
--   department_id INT NOT NULL,
--   PRIMARY KEY (employee_id),
--   FOREIGN KEY (department_id) REFERENCES department(department_id)
-- );