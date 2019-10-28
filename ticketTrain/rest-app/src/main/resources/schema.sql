DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS city;


CREATE TABLE city (
  city_id INT NOT NULL AUTO_INCREMENT,
  city_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (city_id)
);


CREATE TABLE ticket (
  ticket_id INT NOT NULL AUTO_INCREMENT,
    ticket_direction_from INT NOT NULL,
    ticket_direction_to INT NOT NULL,
    ticket_cost DECIMAL NOT NULL,
    ticket_date DATE NOT NULL,
  PRIMARY KEY (ticket_id),
  CONSTRAINT FK_Direction_from FOREIGN KEY (ticket_direction_from) REFERENCES city (city_id),
  CONSTRAINT FK_Direction_to FOREIGN KEY (ticket_direction_to) REFERENCES city (city_id)
);


CREATE TABLE payment (
  payment_id INT NOT NULL AUTO_INCREMENT,
  payment_date  DATE NOT NULL,
  ticket_id INT NOT NULL,
  PRIMARY KEY (payment_id),
  FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id)
);

