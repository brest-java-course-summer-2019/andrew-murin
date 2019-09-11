DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
  ticket_id INT NOT NULL AUTO_INCREMENT,
    ticket_direction VARCHAR(255) NOT NULL,
    ticket_cost INT NOT NULL,
    ticket_date DATE NOT NULL,
  PRIMARY KEY (ticket_id)
);


DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
  payment_id INT NOT NULL AUTO_INCREMENT,
  local_date  DATE NOT NULL,
  ticket_id INT NOT NULL,
  PRIMARY KEY (payment_id),
  FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id)
);
