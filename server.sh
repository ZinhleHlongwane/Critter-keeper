#!/bin/sh
# Builds the project (if needed) and starts the shelter server.
# Usage: ./server.sh [port] [config-file]
mvn -q package -DskipTests
java -cp target/classes:$(find ~/.m2 -name 'gson-*.jar' | head -1):$(find ~/.m2 -name 'slf4j-nop-*.jar' | head -1) \
    com.zinhle.critterkeeper.server.Server "$@"
