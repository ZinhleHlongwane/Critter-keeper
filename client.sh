#!/bin/sh
# Starts an interactive shelter client.
# Usage: ./client.sh <keeperName> [host] [port]
java -cp target/classes:$(find ~/.m2 -name 'gson-*.jar' | head -1) \
    com.zinhle.critterkeeper.client.Client "$@"
