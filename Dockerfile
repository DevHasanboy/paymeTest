FROM openjdk:17-jdk-alpine

# Jar faylini konteynerga ko'chirish
COPY target/payme-test.jar /app/payme-test.jar

# Konteyner ichida "/app" papkasini ishga tushirish
WORKDIR /app

# Spring Boot ilovasini ishga tushirish
ENTRYPOINT ["java", "-jar", "/app/payme-test.jar"]