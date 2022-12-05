FROM alpine:latest

RUN mkdir -p /app/configuration

ADD app.env /app/configuration/app.env
ADD ca-cert.pem /app/configuration/ca-cert.pem
ADD cert-chain.pem /app/configuration/cert-chain.pem
ADD test.properties /app/configuration/test.properties

VOLUME "/app/configuration/"