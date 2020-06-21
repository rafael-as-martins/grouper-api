#!/bin/bash


#Getting current dir

result=${PWD##*/}

#Checking if user is in gruper-admin folder
if [ $result == "grouper-api" ]; then

 #building spring app
 mvn clean install -Pdocker -Ddir=src/docker/admin-api/target/

 #Applying changes to docker containers
 docker-compose build 

 #Containers start up
 docker-compose -f docker-compose.yml up -d
else
 echo "Error: get inside grouper-api folder"

fi
