FROM java:8
COPY target/grouper-api-1.0.0.jar grouper-api-1.0.0.jar


## For debug purposes
ENTRYPOINT ["java","-Ddebug", "-Xrunjdwp:transport=dt_socket,address=8001,server=y","-jar","grouper-api-1.0.0.jar"]

#ENTRYPOINT ["java", "-Ddebug", "-jar", "grouper-api-1.0.0.jar"]

