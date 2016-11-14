#!/bin/bash
cd /project
git pull origin master
cd /project/ebadge-backend
mvn clean package
cd /project/ebadge-frontend
grunt build
cd
