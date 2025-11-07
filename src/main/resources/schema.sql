DROP TABLE if EXISTS customers;
CREATE TABLE customers (
	id SERIAL, 
	cust_name VARCHAR(255), 
	cust_description VARCHAR(255), 
	age INT, 
	course VARCHAR(255),
	passwd VARCHAR(255),
	image_path VARCHAR(500) NULL,
	dataCreated Timestamp DEFAULT CURRENT_TIMESTAMP, 							-- afegeix la data quan crea el registre
	dataUpdated Timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- afegeix la data quan s'actualitza el registre
);