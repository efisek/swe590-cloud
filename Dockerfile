FROM openjdk:11

LABEL maintainer="emre.fisek"

ADD target/termproject-0.0.1-SNAPSHOT.jar termproject.jar

ENTRYPOINT ["java", "-jar", "termproject.jar"]