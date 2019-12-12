INSERT INTO city (city_name) VALUES ('BREST');
INSERT INTO city (city_name) VALUES ('MINSK');
INSERT INTO city (city_name) VALUES ('VITEBSK');
INSERT INTO city (city_name) VALUES ('GOMEL');
INSERT INTO city (city_name) VALUES ('GRODNO');
INSERT INTO city (city_name) VALUES ('MOGILEV');

INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (1, 6, 7.25,'2019-09-25');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (1, 3, 10.65, '2019-09-25');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (1, 5, 11.17, '2019-09-26');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (2, 1, 12.17, '2019-09-26');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (2, 3, 10.14, '2019-09-26');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (2, 5, 13.17, '2019-09-26');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (3, 4, 10.17, '2019-09-27');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (3, 5, 8.17, '2019-09-27');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (3, 6, 12.65, '2019-09-27');
INSERT INTO ticket (from_city, to_city, ticket_cost, ticket_date) VALUES (4, 1, 9.34, '2019-09-28');


INSERT INTO payment (payment_date, ticket_id) VALUES ('2019-09-18', 2);
INSERT INTO payment (payment_date, ticket_id) VALUES ('2019-09-18', 7);
