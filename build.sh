#!/usr/bin/env bash

mvn clean package -DskipTests
docker build -t vrfvitor/interview-sched:latest -f Dockerfile .