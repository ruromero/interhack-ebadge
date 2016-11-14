#!/bin/bash
cd /project/ebadge-frontend
grunt serve > ~/frontend.out 2>&1 &
PID=$!
echo $PID > ~/frontend.pid
cd