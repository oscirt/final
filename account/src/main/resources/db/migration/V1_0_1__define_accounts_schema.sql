CREATE SCHEMA IF NOT EXISTS accounts;

CREATE TABLE IF NOT EXISTS bank_accounts
(
    id_bank_account  SERIAL,
    num_bank_account VARCHAR(20)    NOT NULL,
    amount           DECIMAL(20, 2) NOT NULL
);