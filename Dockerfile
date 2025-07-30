FROM eclipse-temurin:17
COPY target/Madhan.jar Madhan.jar
CMD [ "java","-jar","Madhan.jar" ]