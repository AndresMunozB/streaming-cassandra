#!/usr/bin/env bash

IMAGE=pokemon-ingest
CONTAINER=pokemon-ingest-cnt
PORT=3030

docker build -t $IMAGE .

if docker container ls | grep $CONTAINER > /dev/null; then
	docker container stop $CONTAINER
fi

if docker container ls -a | grep $CONTAINER > /dev/null; then
  docker container rm $CONTAINER
fi

docker run --name $CONTAINER -d -p $PORT:$PORT $IMAGE