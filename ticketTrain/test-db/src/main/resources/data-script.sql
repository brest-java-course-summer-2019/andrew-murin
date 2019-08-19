INSERT INTO ticket (ticket_id, ticket_direction, ticket_cost, ticket_date) VALUES (1, 'Warsaw', 35,'2019-5-24');
INSERT INTO ticket (ticket_id, ticket_direction, ticket_cost, ticket_date) VALUES (2, 'Praga', 25, '2019-12-13');
INSERT INTO ticket (ticket_id, ticket_direction, ticket_cost, ticket_date) VALUES (3, 'Brest', 15, '2019-11-13');

INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (1, '2019-1-24', 2);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (2, '2019-2-7', 3);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (3, '2019-3-15', 2);
INSERT INTO payment (payment_id, local_date, ticket_id) VALUES (4, '2019-4-17', 3);

