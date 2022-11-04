ALTER TABLE actions AUTO_INCREMENT=101;
ALTER TABLE cars AUTO_INCREMENT=201;
ALTER TABLE customers AUTO_INCREMENT=301;
ALTER TABLE files AUTO_INCREMENT=401;
ALTER TABLE invoices AUTO_INCREMENT=501;
ALTER TABLE logs AUTO_INCREMENT=601;
ALTER TABLE parts AUTO_INCREMENT=701;

INSERT INTO roles(rolename) VALUES ('ADMIN'), ('OFFICE'), ('MECH');

INSERT INTO users(username, password) VALUES ('Admin1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'),
                                             ('Mech1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'),
                                             ('Office1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'); /*password: VerySafePassword*/

INSERT INTO users_roles(users_username, roles_rolename) VALUES ('Admin1', 'ADMIN'), ('Mech1', 'MECH'), ('Office1', 'OFFICE');

INSERT INTO actions(id, action_cost, action_name) VALUES (101, 45.00, 'Check-up'), (102, 50.00, 'Change tires'), (103, 300.00, 'Change brakes'), (104, 125.00, 'Change oil'), (105, 100.00, 'Change battery');

INSERT INTO parts(id, part_name, unit_price) VALUES (701, 'Summer tires', 75.00), (702, 'Winter tires', 75.00), (703, 'Brakes', 50.00), (704, 'Battery', 100.00), (705, 'Wheelnuts', 2.50), (706, 'Screws', 1.00);

INSERT INTO cars(id, brand, licence_plate, model) VALUES (201, 'Aston Martin', '12-34-56', 'DB11'), (202, 'Mercedes', '34-56-78', 'AMG');

INSERT INTO customers(id, address, email, first_name, last_name, phone, postcode, residence, car_id) VALUES (301, '2 Bradbourne Drive', 'max@verstappen.nl', 'Max', 'Verstappen', '0612345678', 'MK78AT', 'Milton Keynes', 201),
                                                                                                            (302, '7 Operations Centre', 'george@russell.nl', 'George', 'Russell', '0612345678', 'NN13BD', 'Brackley', 202),
                                                                                                            (303, '8 Dadford Road', 'valtteri@bottas.nl', 'Valtteri', 'Bottas', '0612345678', 'NN12TJ', 'Towcester', null);

INSERT INTO logs(id, log_status, total_cost, total_parts_cost, action_id, car_id, invoice_id) VALUES (601, 4, 45.00, 00.00, 101, 201, 501), (602, 3, 45.00, 00.00, 101, 201, null), (603, 3, 200.00, 100.00, 105, 201, null);

INSERT INTO logs_parts(log_id, part_id) VALUES (60, 704);

INSERT INTO invoices(id, cost_after_tax, cost_before_tax, paid, car_id, customer_id) VALUES (501, 54.45, 45.00, true, 201, 301);

