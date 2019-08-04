INSERT INTO ticket (ticket_id, ticket_direction) VALUES (1, 'Warsaw');
INSERT INTO ticket (ticket_id, ticket_direction) VALUES (2, 'Praga');

INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (1, '2019-1-24', 1);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (2, '2019-2-7', 2);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (3, '2019-3-15', 1);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (4, '2019-4-17', 2);
