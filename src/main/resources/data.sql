INSERT INTO roles(rolename) VALUES ('ADMIN'), ('OFFICE'), ('MECH');

INSERT INTO customers(id, address, email, first_name, last_name, phone, postcode, residence) VALUES (1001, '2 Bradbourne Drive', 'max@verstappen.nl', 'Max', 'Verstappen', '0612345678', 'MK78AT', 'Milton Keynes'),
                                                                                                    (1002, '7 Operations Centre', 'george@russell.nl', 'George', 'Russell', '0612345678', 'NN13BD', 'Brackley'),
                                                                                                    (1003, '8 Dadford Road', 'valtteri@bottas.nl', 'Valtteri', 'Bottas', '0612345678', 'NN12TJ', 'Towcester');

INSERT INTO actions(id, action_cost, action_name) VALUES (1004, 45.00, 'Check-up'), (1005, 50.00, 'Change tires'), (1006, 300.00, 'Change brakes'), (1007, 125.00, 'Change oil'), (1008, 100.00, 'Change battery');

INSERT INTO parts(id, part_name, unit_price) VALUES (1009, 'Summer tires', 75.00), (1010, 'Winter tires', 75.00), (1011, 'Brakes', 50.00), (1012, 'Battery', 100.00), (1013, 'Wheelnuts', 2.50), (1014, 'Screws', 1.00);