# Use the openjdk:21 image as the base image
FROM openjdk:21 AS maven-setup

# Set the version of Maven
ARG MAVEN_VERSION=3.9.6

# Define the URL for downloading Maven
ENV MAVEN_DOWNLOAD_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz

# Download Maven and extract it
RUN mkdir -p /usr/share/maven \
    && curl -fsSL -o /tmp/apache-maven.tar.gz ${MAVEN_DOWNLOAD_URL} \
    && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
    && rm /tmp/apache-maven.tar.gz

# Set environment variables for Maven
ENV MAVEN_HOME /usr/share/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}

# Switch back to openjdk:21 image
FROM openjdk:21 AS kafka-zookeeper-setup


# Define versions
ARG KAFKA_VERSION=3.7.0
ARG ZOOKEEPER_VERSION=3.7.0

# Download and install Kafka using curl
RUN curl -fsSL -o kafka.tgz "https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_2.13-${KAFKA_VERSION}.tgz" \
    && tar -xzf kafka.tgz -C /opt \
    && ln -s /opt/kafka_2.13-${KAFKA_VERSION} /opt/kafka \
    && rm kafka.tgz

# Download and install Zookeeper using curl
RUN curl -fsSL -o zookeeper.tgz "https://archive.apache.org/dist/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/apache-zookeeper-${ZOOKEEPER_VERSION}-bin.tar.gz" \
    && tar -xzf zookeeper.tgz -C /opt \
    && ln -s /opt/apache-zookeeper-${ZOOKEEPER_VERSION}-bin /opt/zookeeper \
    && rm zookeeper.tgz

# Switch back to openjdk:21 image
FROM openjdk:21

# Copy Maven setup from the maven-setup stage
COPY --from=maven-setup /usr/share/maven /usr/share/maven

# Copy Kafka and Zookeeper setup from the kafka-zookeeper-setup stage
COPY --from=kafka-zookeeper-setup /opt/kafka /opt/kafka
COPY --from=kafka-zookeeper-setup /opt/zookeeper /opt/zookeeper

# Set environment variables for Maven
ENV MAVEN_HOME /usr/share/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}

# Set environment variables for Kafka and Zookeeper
ENV KAFKA_HOME /opt/kafka
ENV ZOOKEEPER_HOME /opt/zookeeper

# Set PATH to include Kafka and Zookeeper bin directories
ENV PATH $PATH:/opt/kafka/bin:/opt/zookeeper/bin

#Set Working directory
WORKDIR /opt/kafka/
COPY . .
# COPY start-services.sh .
# RUN chmod +x start-services.sh

COPY target/demo-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "./demo-0.0.1-SNAPSHOT.jar"]