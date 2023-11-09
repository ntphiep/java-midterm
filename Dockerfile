FROM openjdk:17

WORKDIR /app

COPY .mvn .mvn

COPY mvnw pom.xml ./

COPY mvnw pom.xml ./

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]