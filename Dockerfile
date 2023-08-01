FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY pom.xml pom.xml
COPY jpa jpa
COPY backend_generated_app_f93255d7_1a62_4a8b_a9f9_3e6f195cf1f9 backend_generated_app_f93255d7_1a62_4a8b_a9f9_3e6f195cf1f9
RUN mvn clean package -DnoTest=true

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/backend_generated_app_f93255d7_1a62_4a8b_a9f9_3e6f195cf1f9/target/generated_app-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar generated_app-0.0.1-SNAPSHOT.jar"]