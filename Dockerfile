FROM openjdk:21-jdk-slim-buster
WORKDIR /payments

COPY build/libs/payments-1.0-SNAPSHOT.jar /payments/build/

WORKDIR /payments/build

EXPOSE 8082

ENTRYPOINT java -jar payments-1.0-SNAPSHOT.jar