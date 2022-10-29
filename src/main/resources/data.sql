insert into roles(rolename) values ('ADMIN'), ('OFFICE'), ('MECH');

insert into customers(id, address, email, first_name, last_name, phone, postcode, residence) values (1, '2 Bradbourne Drive', 'max@verstappen.nl', 'Max', 'Verstappen', '0612345678', 'MK78AT', 'Milton Keynes');

insert into actions(id, action_cost, action_name) values (1, 45.00, 'Check-up'), (2, 50.00, 'Change tires'), (3, 300.00, 'Change brakes'), (4, 125.00, 'Change oil'), (5, 100.00, 'Change battery');

insert into parts(id, part_name, unit_price) values (1, 'Summer tires', 75.00), (2, 'Winter tires', 75.00), (3, 'Brakes', 50.00), (4, 'Battery', 100.00), (5, 'Wheelnuts', 2.50), (6, 'Screws', 1.00);