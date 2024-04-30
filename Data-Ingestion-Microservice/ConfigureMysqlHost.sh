#!/bin/bash
# Replace the placeholder in the configuration file
sed "s|\${MYSQL_HOST}|$MYSQL_HOST|g" hibernate.cfg.xml.template > hibernate.cfg.xml
mv hibernate.cfg.xml src/main/resources/
mvn clean install
java -jar target/API-0.0.1-SNAPSHOT.jar

/bin/bash