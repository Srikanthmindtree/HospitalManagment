FROM openjdk:17
EXPOSE 9095
ADD target/doctor-spring-boot.jar doctor-spring-boot.jar
ENTRYPOINT ["java", "-jar","/doctor-spring-boot.jar"]