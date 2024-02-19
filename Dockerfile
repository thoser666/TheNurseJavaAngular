FROM ubuntu:latest
LABEL authors="Steffen"

ENTRYPOINT ["top", "-b"]

ARG USER_HOME_DIR="/root"


# Install Java.
RUN apk --update --no-cache add openjdk7 curl

RUN <<EOF

mkdir /opt/maven

maven_version=$(curl -fsSL https://repo1.maven.org/maven2/org/apache/maven/apache-maven/maven-metadata.xml  \
      | grep -Ev "alpha|beta" \
      | grep -oP '(?<=version>).*(?=</version)'  \
      | tail -n1)

maven_download_url="https://repo1.maven.org/maven2/org/apache/maven/apache-maven/$maven_version/apache-maven-${maven_version}-bin.tar.gz"

echo "Downloading [$maven_download_url]..."

curl -fL $maven_download_url | tar zxv -C /opt/maven --strip-components=1

EOF

ENV MAVEN_HOME /opt/maven
ENV M2_HOME /opt/maven
ENV PATH="/opt/maven/bin:${PATH}"

ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Define working directory.
WORKDIR /data

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/default-jvm/

# Define default command.
CMD ["mvn", "--version"]



# Spring docker sample
# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]