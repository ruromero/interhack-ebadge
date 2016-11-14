#!/bin/bash
cd ../ebadge-frontend
grunt serve > ../scripts/frontend.out 2>&1 &
PID=$!
cd ../scripts
echo $PID > frontend.pid
