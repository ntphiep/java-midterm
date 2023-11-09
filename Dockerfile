FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install



# ------------


FROM openjdk:17-oracle

WORKDIR /app

COPY --from=build /app/target/app.jar ./app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]