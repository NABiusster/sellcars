FROM openjdk:17-alpine
LABEL authors="NABius"
MAINTAINER Some Dev

RUN apk add bash

RUN mkdir /sellcars
WORKDIR /sellcars