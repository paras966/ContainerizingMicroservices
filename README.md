# ContainerizingMicroservices
Uploaded Dockerfiles for containerizing Data Visualization, Data Ingestion microservices, and the Kafka Producer. Simplifying deployment, ensuring consistency, and enabling scalability.
<br>
`Follow the below commands to bring up the multi-container application.`
<br><br>
- docker network create microservices --driver bridge
- docker run -d --name zookeeper -p 2181:2181 -e ALLOW_ANONYMOUS_LOGIN=yes --network microservices docker.io/bitnami/zookeeper:3.9
- docker run -d --name kafka -p 9092:9092 -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 --add-host=zookeeper:ZOOKEEPER_IP --network microservices docker.io/bitnami/kafka:3.4
- docker exec kafka opt/bitnami/kafka/bin/kafka-topics.sh --create --topic routersData --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
- docker run --name mysql -e MYSQL_USER=user -e MYSQL_PASSWORD=user -e MYSQL_ROOT_PASSWORD=root -d --network microservices docker.io/paraspatil/data-ingestion-mysql
- docker run -d --name data-ingestion -p 8080:8080 -e MYSQL_HOST=mysql -e KAFKA_SERVER=kafka --network microservices docker.io/paraspatil/data-ingestion
- docker run -d --name data-visualization -p 8081:8081 -e DATA_INGESTION=data-ingestion --network microservices docker.io/paraspatil/data-visualization
- docker run -d --name kafka-producer -e KAFKA_SERVER=kafka --network microservices docker.io/paraspatil/kafka-producer
<br><br><br>

- ALLOW_ANONYMOUS_LOGIN=yes: Allows anonymous login in Zookeeper container.
- KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181: Specifies the Zookeeper connection for Kafka.
- MYSQL_USER, MYSQL_PASSWORD, MYSQL_ROOT_PASSWORD: Sets MySQL user and root password.
- MYSQL_HOST=MYSQL_IP: Specifies the MySQL host IP for the Data Ingestion container.
- KAFKA_SERVER=KAFKA_IP: Specifies the Kafka server IP for the Data Ingestion container and Kafka Producer.
- DATA_INGESTION=DATA_INGESTION_IP: Specifies the IP address of the Data Ingestion microservice for the Data Visualization container.
