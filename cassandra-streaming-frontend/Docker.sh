#!/usr/bin/env bash

IMAGE=pokemon-frontend
CONTAINER=pokemon-frontend-cnt
PORT=8080

docker build -t $IMAGE .

if docker container ls | grep $CONTAINER > /dev/null; then
	docker container stop $CONTAINER
fi

if docker container ls -a | grep $CONTAINER > /dev/null; then
  docker container rm $CONTAINER
fi

docker run --name $CONTAINER -d -p $PORT:$PORT $IMAGE