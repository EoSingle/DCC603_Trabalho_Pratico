#!/bin/bash

# Clean and compile the project
mvn clean compile

# Run the project
mvn exec:java -Dexec.mainClass="com.rpg.Main"