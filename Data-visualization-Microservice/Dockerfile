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
FROM openjdk:21

# Copy Maven setup from the maven-setup stage
COPY --from=maven-setup /usr/share/maven /usr/share/maven

# Set environment variables for Maven
ENV MAVEN_HOME /usr/share/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}

#Deploy Java Application

WORKDIR /app
COPY target/jfree-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8081
CMD ["java", "-jar", "jfree-0.0.1-SNAPSHOT.jar"]
