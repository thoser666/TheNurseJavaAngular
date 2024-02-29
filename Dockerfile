#FROM ubuntu:latest
#FROM openjdk:17-jdk
#LABEL authors="Steffen"


ARG kafka_version=3.7.0
ARG scala_version=2.13
ARG vcs_ref=unspecified
ARG build_date=unspecified

# Set the working directory in the container
#WORKDIR /app

# Copy the local application code into the container
#COPY . .

# Build the Rust application
#RUN cargo build --release

# Specify the command to run when the container starts
#CMD ["./target/release/internationalbot"]




# Builds an image for Apache Kafka from binary distribution.
#
# The netflixoss/java base image runs Oracle Java 8 installed atop the
# ubuntu:trusty (14.04) official image. Docker's official java images are
# OpenJDK-only currently, and the Kafka project, Confluent, and most other
# major Java projects test and recommend Oracle Java for production for optimal
# performance.
FROM ubuntu:latest
MAINTAINER Ches Martin <ches@whiskeyandgrits.net>
#
# The Scala 2.12 build is currently recommended by the project.
ENV KAFKA_VERSION=3.7.0 KAFKA_SCALA_VERSION=2.13 JMX_PORT=7203
ENV KAFKA_RELEASE_ARCHIVE kafka_${KAFKA_SCALA_VERSION}-${KAFKA_VERSION}.tgz

RUN mkdir /kafka /data /logs

RUN apt-get update && \
  DEBIAN_FRONTEND=noninteractive apt-get install -y \
    ca-certificates

# Download Kafka binary distribution
ADD https://dist.apache.org/repos/dist/release/kafka/${KAFKA_VERSION}/${KAFKA_RELEASE_ARCHIVE} /tmp/
ADD https://dist.apache.org/repos/dist/release/kafka/${KAFKA_VERSION}/${KAFKA_RELEASE_ARCHIVE}.md5 /tmp/

#WORKDIR /tmp
#
## Check artifact digest integrity
#RUN echo VERIFY CHECKSUM: && \
#  gpg --print-md MD5 ${KAFKA_RELEASE_ARCHIVE} 2>/dev/null && \
#  cat ${KAFKA_RELEASE_ARCHIVE}.md5

## Install Kafka to /kafka
#RUN tar -zx -C /kafka --strip-components=1 -f ${KAFKA_RELEASE_ARCHIVE} && \
#  rm -rf kafka_*

#ADD config /kafka/config
#ADD start.sh /start.sh

## Set up a user to run Kafka
#RUN groupadd kafka && \
#  useradd -d /kafka -g kafka -s /bin/false kafka && \
#  chown -R kafka:kafka /kafka /data /logs
#USER kafka
#ENV PATH /kafka/bin:$PATH
#WORKDIR /kafka

## broker, jmx
#EXPOSE 9092 ${JMX_PORT}
#VOLUME [ "/data", "/logs" ]
#
#CMD ["/start.sh"]















#FROM ubuntu:latest
#LABEL org.label-schema.name="kafka" \
#      org.label-schema.description="Apache Kafka" \
#      org.label-schema.build-date="${build_date}" \
#      org.label-schema.vcs-url="https://github.com/wurstmeister/kafka-docker" \
#      org.label-schema.vcs-ref="${vcs_ref}" \
#      org.label-schema.version="${scala_version}_${kafka_version}" \
#      org.label-schema.schema-version="1.0" \
#      maintainer="wurstmeister"
#
#ENV KAFKA_VERSION=$kafka_version \
#    SCALA_VERSION=$scala_version \
#    KAFKA_HOME=/opt/kafka
#
#ENV PATH=${PATH}:${KAFKA_HOME}/bin
#
#COPY download-kafka.sh start-kafka.sh broker-list.sh create-topics.sh versions.sh /tmp2/
#
#RUN set -eux ; \
#    apt-get update ; \
#    apt-get upgrade -y ; \
#    apt-get install -y --no-install-recommends jq net-tools curl wget ; \
#### BEGIN docker for CI tests
#    apt-get install -y --no-install-recommends gnupg lsb-release ; \
#	curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg ; \
#	echo \
#  		"deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian \
#  		$(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null ; \
#    apt-get update ; \
#    apt-get install -y --no-install-recommends docker-ce-cli ; \
#    apt remove -y gnupg lsb-release ; \
#    apt clean ; \
#    apt autoremove -y ; \
#    apt -f install ; \
#### END docker for CI tests
#### BEGIN other for CI tests
#    apt-get install -y --no-install-recommends netcat ; \
#### END other for CI tests
#    chmod a+x /tmp2/*.sh ; \
#    mv /tmp2/start-kafka.sh /tmp2/broker-list.sh /tmp2/create-topics.sh /tmp2/versions.sh /usr/bin ; \
#    sync ; \
#    /tmp2/download-kafka.sh ; \
#    tar xfz /tmp2/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt ; \
#    rm /tmp2/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz ; \
#    ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME} ; \
#    rm -rf /tmp2 ; \
#    rm -rf /var/lib/apt/lists/*
#
# COPY overrides /opt/overrides
#
#VOLUME ["/kafka"]
#
## Use "exec" form so that it runs as PID 1 (useful for graceful shutdown)
#CMD ["start-kafka.sh"]
