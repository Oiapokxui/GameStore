#!/usr/bin/env bash

mkdir -p $HOME/docker/volumes/postgres
rm -rf $HOME/docker/volumes/postgres/data

docker run --rm --name pg-gamestore -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432  postgres
sleep 3

export PGPASSWORD=postgres
psql -U postgres -d dev -h localhost -f sql/schema.sql
psql -U postgres -d dev -h localhost -f sql/data.sql
