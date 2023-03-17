FROM maven:3.8.7-eclipse-temurin-17 AS build

COPY . /app

WORKDIR /app

RUN mvn clean install -Dmaven.test.skip=true

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17.0.6_10-jdk-alpine

RUN apk update && apk upgrade

ARG DEPENDENCY=/app/target/dependency

COPY --from=build ${DEPENDENCY}/e2etestservice*.jar /app/dataexchange.jar

RUN adduser -DH dataex && addgroup dataex dataex

USER dataex

ENTRYPOINT ["java", "-jar", "dataexchange.jar"]

EXPOSE 8080
