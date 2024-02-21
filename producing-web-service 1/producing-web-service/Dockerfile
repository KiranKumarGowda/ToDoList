FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} nextgen-soap-connector.jar
ENTRYPOINT ["java","-jar","/nextgen-soap-connector.jar"]