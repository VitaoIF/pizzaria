CREATE SEQUENCE product_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE product(
    id BIGINT PRIMARY KEY DEFAULT nextval('product_seq'),
    name varchar(255) NOT NULL,
    description TEXT NOT NULL,
    banner varchar(500) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    price NUMERIC(10,2) NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)

);