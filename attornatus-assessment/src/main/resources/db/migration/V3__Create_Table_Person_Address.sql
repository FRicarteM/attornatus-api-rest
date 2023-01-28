CREATE TABLE person_address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    person_fk BIGINT,
	address_fk BIGINT,
    FOREIGN KEY (person_fk) REFERENCES person(id),
	FOREIGN KEY (address_fk) REFERENCES address(id)
);