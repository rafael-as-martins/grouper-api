DELETE instituition;
DELETE country;

INSERT INTO country (id, name) values (1, 'Portugal');
INSERT INTO instituition (id, name, address, country) VALUES (1, 'Faculdade de Ciências', 'address', 1);