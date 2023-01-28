CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cep VARCHAR(9) NOT NULL,
    street VARCHAR(150) NOT NULL,
    number INT,
    city VARCHAR(68),
    main_address BOOLEAN
);