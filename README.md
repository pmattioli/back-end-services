# back-end-services

docker run --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest

Download Flyway Community Edition from https://flywaydb.org/download/

git clone https://git-codecommit.eu-west-1.amazonaws.com/v1/repos/RetinaLyze_WebApp

Set flyway.conf to:

flyway.url=jdbc:mysql://localhost:3306?useSSL=false
flyway.user=root
flyway.password=123456
flyway.schemas=retinalyzeapi
flyway.table=schema_version
flyway.locations=filesystem:/home/pmattioli/polyglot/RetinaLyze_WebApp/database

Make sure no other configurations are replacing these (some are already present in the config file)

Add this to Create Users script:

INSERT INTO retinalyzeapi.ret_users (userID, username, password, email, typeID, disabled, s3usingUserID) VALUES (2, 'pmattioli@polyglot-outsourcing.com', '$2y$11$89dc29d7d8f313c4734b4uBxMzKwIfNj18fQ0gLH30Fqp4dSbHhlq', 'pmattioli@polyglot-outsourcing.com', '1', '0', '0');

./flyway info migrate

./mvnw spring-boot:build-image

docker run --name back-end-services -p 8083:8083 --link mysql:mysql back-end-services:0.0.1-SNAPSHOT

docker run --link mysql:mysql --link back-end-services:back-end-services -p 8080:80 -v ~/polyglot/RetinaLyze_WebApp:/var/www/html/ retinalyze_webapp:latest
