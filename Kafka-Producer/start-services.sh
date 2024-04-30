#!/bin/bash

set -e
# Start Zookeeper server
#echo "Starting Zookeeper server..."
#bin/zookeeper-server-start.sh config/zookeeper.properties &
#
#sleep 5
#
## Start Kafka server
#echo "Starting Kafka server..."
#bin/kafka-server-start.sh config/server.properties &
## Wait for both processes to finish
#
#sleep 5
#
##Create topic
#bin/kafka-topics.sh --create --topic routersData --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
#
#sleep 10

java -jar demo-0.0.1-SNAPSHOT.jar
wait

/bin/bash
