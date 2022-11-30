FROM openjdk:17

MAINTAINER lsm1998

RUN mkdir -p /opt/java

ADD /target/emos-football.jar /opt/java/emos-football.jar

WORKDIR /opt/java

EXPOSE 9876

ENTRYPOINT ["java", "-jar","emos-football.jar"]