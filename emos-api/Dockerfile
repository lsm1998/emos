FROM openjdk:17

MAINTAINER lsm1998

RUN mkdir -p /opt/java

ADD /target/emos-api.jar /opt/java/emos-api.jar

WORKDIR /opt/java

EXPOSE 8888

ENTRYPOINT ["java", "-jar","emos-api.jar"]