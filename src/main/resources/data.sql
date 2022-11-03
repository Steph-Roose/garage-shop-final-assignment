INSERT INTO roles(rolename) VALUES ('ADMIN'), ('OFFICE'), ('MECH');

INSERT INTO users(username, password) VALUES ('Admin1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'),
                                             ('Mech1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'),
                                             ('Office1', '$2a$10$lIDPLSUIs/23ng6AT0.XM.ohPSupfZU50pte8WUS/tPZvwAJSUrKu'); /*password: VerySafePassword*/

INSERT INTO users_roles(users_username, roles_rolename) VALUES ('Admin1', 'ADMIN'), ('Mech1', 'MECH'), ('Office1', 'OFFICE');

INSERT INTO actions(id, action_cost, action_name) VALUES (2001, 45.00, 'Check-up'), (2002, 50.00, 'Change tires'), (2003, 300.00, 'Change brakes'), (2004, 125.00, 'Change oil'), (2005, 100.00, 'Change battery');

INSERT INTO parts(id, part_name, unit_price) VALUES (2006, 'Summer tires', 75.00), (2007, 'Winter tires', 75.00), (2008, 'Brakes', 50.00), (2009, 'Battery', 100.00), (2010, 'Wheelnuts', 2.50), (2011, 'Screws', 1.00);

INSERT INTO cars(id, brand, licence_plate, model) VALUES (1004, 'Aston Martin', '12-34-56', 'DB11'), (1005, 'Mercedes', '34-56-78', 'AMG'), (1006, 'McLaren', '56-78-90', '675LT');

INSERT INTO customers(id, address, email, first_name, last_name, phone, postcode, residence, car_id) VALUES (1001, '2 Bradbourne Drive', 'max@verstappen.nl', 'Max', 'Verstappen', '0612345678', 'MK78AT', 'Milton Keynes', 1004),
                                                                                                            (1002, '7 Operations Centre', 'george@russell.nl', 'George', 'Russell', '0612345678', 'NN13BD', 'Brackley', 1005),
                                                                                                            (1003, '8 Dadford Road', 'valtteri@bottas.nl', 'Valtteri', 'Bottas', '0612345678', 'NN12TJ', 'Towcester', 1006);

INSERT INTO logs(id, log_status, total_cost, total_parts_cost, action_id, car_id) VALUES (1007, 3, 45.00, 00.00, 2001, 1004);

