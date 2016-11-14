#!/bin/bash
java -jar ../ebadge-backend/target/ebadge-backend.jar > backend.out 2>&1 &
PID=$!
echo $PID > backend.pid
