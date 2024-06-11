CREATE SCHEMA IF NOT EXISTS deposits;

CREATE TABLE IF NOT EXISTS deposits.deposits_types
(
    id_deposit_types    SERIAL,
    deposits_types_name VARCHAR(28) NOT NULL,
    PRIMARY KEY (id_deposit_types)
);

CREATE TABLE IF NOT EXISTS deposits.types_percent_payment
(
    id_type_percent_payment     SERIAL,
    type_percent_payment_period VARCHAR(13) NOT NULL,
    PRIMARY KEY (id_type_percent_payment)
);

CREATE TABLE IF NOT EXISTS deposits.deposits
(
    id_deposit                 SERIAL,
    deposit_account_id         INT            NOT NULL,
    deposit_type_id            INT            NOT NULL,
    deposit_refill             BOOLEAN        NOT NULL,
    deposit_amount             DECIMAL(20, 2) NOT NULL,
    start_date                 DATE           NOT NULL,
    end_date                   DATE           NOT NULL,
    deposit_rate               DECIMAL(4, 2)  NOT NULL,
    type_percent_payment_id    INT,
    percent_payment_account_id INT,
    percent_payment_date       DATE,
    capitalization             BOOLEAN,
    deposit_refund_account_id  INT,
    PRIMARY KEY (id_deposit),
    FOREIGN KEY (deposit_type_id) REFERENCES deposits.deposits_types (id_deposit_types),
    FOREIGN KEY (type_percent_payment_id) REFERENCES deposits.types_percent_payment (id_type_percent_payment)
);

CREATE TABLE IF NOT EXISTS deposits.customers_deposits
(
    customer_id INT,
    deposit_id  INT,
    PRIMARY KEY (customer_id, deposit_id),
    FOREIGN KEY (deposit_id) REFERENCES deposits(id_deposit)
);
