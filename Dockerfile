# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jre-alpine

# Set environment variables
ENV KAFKA_VERSION=3.7.0
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka
ENV PATH=${PATH}:${KAFKA_HOME}/bin

# Install dependencies
RUN apk add --no-cache bash curl jq

# Download and extract Kafka
# doesnt work at the moment
RUN mkdir /opt \
  && curl -Ls "https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz" | tar -xz -C /opt \
  && ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME}

 Copy configuration script and set up configuration

COPY config.sh /usr/bin/config.sh
RUN chmod +x /usr/bin/config.sh

# Expose Kafka ports
# 9092: Kafka
EXPOSE 9092

# Set up a user to run Kafka
RUN adduser -D kafka
USER kafka
WORKDIR ${KAFKA_HOME}

# Entry point to start Kafka with the configuration script
ENTRYPOINT ["config.sh"]
