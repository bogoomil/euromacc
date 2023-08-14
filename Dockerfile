FROM maven:3.8.3-openjdk-17 as maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package

FROM openjdk:17
ARG JAR_FILE=elastic-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","elastic-0.0.1-SNAPSHOT.jar"]