INSERT INTO city (city_id, city_name) VALUES (1, 'BREST');
INSERT INTO city (city_id, city_name) VALUES (2, 'MINSK');
INSERT INTO city (city_id, city_name) VALUES (3, 'VITEBSK');
INSERT INTO city (city_id, city_name) VALUES (4, 'GOMEL');
INSERT INTO city (city_id, city_name) VALUES (5, 'GRODNO');
INSERT INTO city (city_id, city_name) VALUES (6, 'MOGILEV');

INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (1, 1, 6, 7.25,'2019-09-25');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (2, 1, 3, 10.65, '2019-09-25');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (3, 1, 5, 11.17, '2019-09-26');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (4, 2, 1, 12.17, '2019-09-26');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (5, 2, 3, 10.14, '2019-09-26');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (6, 2, 5, 13.17, '2019-09-26');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (7, 3, 4, 10.17, '2019-09-27');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (8, 3, 5, 8.17, '2019-09-27');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (9, 3, 6, 12.65, '2019-09-27');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (10, 4, 1, 9.34, '2019-09-28');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (11, 4, 2, 12.25, '2019-09-28');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (12, 4, 3, 13.65, '2019-09-28');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (13, 4, 6, 10.78, '2019-09-28');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (14, 5, 1, 8.14, '2019-09-29');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (15, 5, 2, 14.14, '2019-09-29');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (16, 5, 3, 11.69, '2019-09-29');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (17, 5, 4, 9.24, '2019-09-29');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (18, 6, 1, 10.46, '2019-09-30');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (19, 6, 2, 13.25, '2019-09-30');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (20, 6, 3, 11.49, '2019-09-30');
INSERT INTO ticket (ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date) VALUES (21, 6, 1, 8.50, '2019-09-30');

INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (1, '2019-09-18', 2);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (2, '2019-09-18', 3);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (3, '2019-09-22', 5);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (4, '2019-09-23', 4);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (5, '2020-09-25', 1);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (6, '2019-09-28', 4);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (7, '2019-09-19', 1);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (8, '2019-09-22', 2);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (9, '2019-09-23', 1);
INSERT INTO payment (payment_id, payment_date, ticket_id) VALUES (10, '2020-09-25', 1);



