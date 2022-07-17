FROM amazoncorretto:11

MAINTAINER Vitor Ferreira <vrfvitor@hotmail.com>

RUN mkdir /opt/vrfvitor

COPY target/interview-sched.jar /opt/vrfvitor/interview-sched.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar /opt/vrfvitor/interview-sched.jar"]