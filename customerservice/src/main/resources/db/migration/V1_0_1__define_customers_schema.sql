CREATE SCHEMA IF NOT EXISTS customers;

CREATE TABLE IF NOT EXISTS customers.customers
(
    id_customer       BIGSERIAL,
    customer_username VARCHAR(100) NOT NULL,
    customer_password VARCHAR(255) NOT NULL,
    customer_role     VARCHAR(20)  NOT NULL,
    bank_account_id   INT          NOT NULL,
    phone_number      VARCHAR(11)  NOT NULL,
    PRIMARY KEY (id_customer)
);

CREATE TABLE IF NOT EXISTS customers.request_statuses
(
    id_request_status   SERIAL,
    request_status_name VARCHAR(21) NOT NULL,
    PRIMARY KEY (id_request_status)
);

CREATE TABLE IF NOT EXISTS customers.requests
(
    id_request   BIGSERIAL,
    customer_id  BIGINT NOT NULL,
    request_date DATE   NOT NULL,
    deposits_id  BIGINT,
    PRIMARY KEY (id_request)
);

CREATE TABLE IF NOT EXISTS customers.current_request_status
(
    request_id        BIGINT,
    request_status_id INT,
    change_datetime   TIMESTAMPTZ,
    PRIMARY KEY (request_id, request_status_id),
    FOREIGN KEY (request_id) REFERENCES requests (id_request),
    FOREIGN KEY (request_status_id) REFERENCES request_statuses (id_request_status)
);