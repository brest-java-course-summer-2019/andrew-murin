DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
  ticket_id INT NOT NULL AUTO_INCREMENT,
  ticket_direction VARCHAR(255) NOT NULL,
  PRIMARY KEY (ticket_id)
);

