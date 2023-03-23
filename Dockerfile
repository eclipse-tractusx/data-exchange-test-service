FROM maven:3.8.7-eclipse-temurin-17 AS build

COPY . /app

WORKDIR /app

RUN mvn clean install -Dmaven.test.skip=true

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17.0.6_10-jdk-alpine

RUN apk update && apk upgrade

ARG DEPENDENCY=/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

RUN adduser -DH dataex && addgroup dataex dataex

USER dataex

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "org.connector.e2etestservice.E2etestserviceApplication"]

EXPOSE 8080

HEALTHCHECK CMD curl --fail http://localhost:8080 || exit 1
