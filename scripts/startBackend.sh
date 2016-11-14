#!/bin/bash
java -jar /project/ebadge-backend/target/ebadge-backend.jar > ~/backend.out 2>&1 &
PID=$!
echo $PID > ~/backend.pid
cd