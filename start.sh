#!/bin/bash

docker run --name final_customers_db -e POSTGRES_USER=oscirt -e POSTGRES_PASSWORD=12345 \
-e POSTGRES_DB=customers -d -p 5431:5432 postgres:16.2
docker run --name final_deposits_db -e POSTGRES_USER=tricso -e POSTGRES_PASSWORD=54321 \
-e POSTGRES_DB=deposits -d -p 5432:5432 postgres:16.2
docker run --name final_accounts_db -e POSTGRES_USER=o -e POSTGRES_PASSWORD=34215 \
-e POSTGRES_DB=accounts -d -p 5433:5432 postgres:16.2