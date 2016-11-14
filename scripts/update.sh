#!/bin/bash
cd ..
git pull origin master
cd ebadge-backend
mvn clean package
cd ../ebadge-frontend
grunt build
cd ../scripts
