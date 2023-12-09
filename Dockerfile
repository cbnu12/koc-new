FROM eclipse-temurin:17-alpine

WORKDIR /app
COPY build/libs/koc-0.0.1-SNAPSHOT.jar ./koc-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prd", "place-0.0.1-SNAPSHOT.jar"]
