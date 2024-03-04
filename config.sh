#!/bin/bash

# Set the broker ID
if [ ! -z "$KAFKA_BROKER_ID" ]; then
  sed -i "s/broker.id=0/broker.id=$KAFKA_BROKER_ID/" $KAFKA_HOME/config/server.properties
fi

# Configure the listeners if provided
if [ ! -z "$KAFKA_LISTENERS" ]; then
  sed -i "s/#listeners=PLAINTEXT:\/\/:9092/listeners=$KAFKA_LISTENERS/" $KAFKA_HOME/config/server.properties
fi

# Configure the Zookeeper connection if provided
if [ ! -z "$KAFKA_ZOOKEEPER_CONNECT" ]; then
  sed -i "s/zookeeper.connect=localhost:2181/zookeeper.connect=$KAFKA_ZOOKEEPER_CONNECT/" $KAFKA_HOME/config/server.properties
fi

# Start Kafka server
kafka-server-start.sh $KAFKA_HOME/config/server.properties
