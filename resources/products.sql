INSERT INTO product_category (name, description, department) VALUES ('cars', 'all the cars from the stock', 'transportation');

INSERT INTO product_supplier (name, description) VALUES ('mercedes', 'Mercedes-Benz is both a German automotive marque and, from late 2019 onwards, a subsidiary of Daimler AG. Mercedes-Benz is known for producing luxury vehicles and commercial vehicles. The headquarters is in Stuttgart, Baden-Württemberg. The name first appeared in 1926 under Daimler-Benz.');
INSERT INTO product_supplier (name, description) VALUES ('bmw', 'Bayerische Motoren Werke AG, commonly referred to as BMW, is a German multinational company which produces luxury vehicles and motorcycles. The company was founded in 1916 as a manufacturer of aircraft engines, which it produced from 1917 until 1918 and again from 1933 to 1945.');
INSERT INTO product_supplier (name, description) VALUES ('audi', 'Audi AG is a German automobile manufacturer that designs, engineers, produces, markets and distributes luxury vehicles. Audi is a member of the Volkswagen Group and has its roots at Ingolstadt, Bavaria, Germany. Audi-branded vehicles are produced in nine production facilities worldwide.');
INSERT INTO product_supplier (name, description) VALUES ('lamborghini', 'Automobili Lamborghini S.p.A. is an Italian brand and manufacturer of luxury sports cars and SUVs based in Sant''Agata Bolognese. The company is owned by the Volkswagen Group through its subsidiary Audi.');
INSERT INTO product_supplier (name, description) VALUES ('land rover', 'Jaguar Land Rover Automotive PLC is the holding company of Jaguar Land Rover Limited, a British multinational automotive company with its headquarters in Whitley, Coventry, United Kingdom, and a subsidiary of Indian automotive company Tata Motors.');
INSERT INTO product_supplier (name, description) VALUES ('porsche', 'Dr.-Ing. h.c. F. Porsche AG, usually shortened to Porsche AG, is a German automobile manufacturer specializing in high-performance sports cars, SUVs and sedans.');
INSERT INTO product_supplier (name, description) VALUES ('rolls royce', 'Rolls-Royce Holdings plc is a British multinational engineering company incorporated in February 2011 that owns Rolls-Royce, a business established in 1904 which today designs, manufactures and distributes power systems for aviation and other industries.');
INSERT INTO product_supplier (name, description) VALUES ('bentley', 'Bentley Motors Limited is a British manufacturer and marketer of luxury cars and SUVs—and a subsidiary of the Volkswagen Group since 1998. Headquartered in Crewe, England, the company was founded as Bentley Motors Limited by W. O');

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('BMW 5 Series', '', 57050, 'EUR', 1, 2);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('BMW 6 Series', '', 63850, 'EUR', 1, 2);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('BMW 6 Series Coupe', '', 89400, 'EUR', 1, 2);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Mercedes-Benz S 65 AMG', '', 105950, 'EUR', 1, 1);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Mercedes-Benz Mercedes-Benz AMG', '', 249500, 'EUR', 1, 1);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Mercedes-Benz G 63 AMG', '', 244760, 'EUR', 1, 1);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Audi R8 Coupe', '', 243980, 'EUR', 1, 3);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Audi RS6', '', 234900, 'EUR', 1, 3);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Audi RSQ8 quattro', '', 215700, 'EUR', 1, 3);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Bentley Continental GT V8', '', 235700, 'EUR', 1, 8);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Bentley Bentayga Speed', '', 244900, 'EUR', 1, 8);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Bentley Flying Spur', '', 234600, 'EUR', 1, 8);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Lamborghini URUS', '', 240000, 'EUR', 1, 4);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Lamborghini Huracan EVO', '', 244900, 'EUR', 1, 4);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Lamborghini Aventador Miura Edition', '', 234600, 'EUR', 1, 4);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Rolls-Royce Wraith', '', 189900, 'EUR', 1, 7);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Rolls-Royce Wraith DAS3', '', 179700, 'EUR', 1, 7);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Rolls-Royce Cullinan', '', 328300, 'EUR', 1, 7);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Range Rover 5.0 V8 SC', '', 285000, 'EUR', 1, 5);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Range Rover 5.0 V8', '', 221300, 'EUR', 1, 5);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Range Rover 3.0 V6', '', 221800, 'EUR', 1, 5);

INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Porsche Cayenne Turbo Coupe 4.0', '', 171900, 'EUR', 1, 6);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Porsche Cayman 718 GTS', '', 90880, 'EUR', 1, 6);
INSERT INTO products(name, description, default_price, default_currency, category_id, supplier_id) VALUES ('Porsche 718 Boxster GTS 4.0', '', 88980, 'EUR', 1, 6);