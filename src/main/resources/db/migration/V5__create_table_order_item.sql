CREATE SEQUENCE order_item_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE order_item (
    id BIGINT PRIMARY KEY DEFAULT nextval('order_item_seq'),
    quantity INTEGER NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL,

    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
);