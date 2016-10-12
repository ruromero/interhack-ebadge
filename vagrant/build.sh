#!/usr/bin/env bash

echo "=================================================="
echo "Cloning GIT repository"
echo "=================================================="
echo ""
echo ""
git clone https://github.com/ruromero/interhack-ebadge.git /project/

echo "=================================================="
echo "Build NPM Frontend"
echo "=================================================="
cd /project/ebadge-frontend
npm install
bower install
grunt 

echo "=================================================="
echo "Build Maven Backend"
echo "=================================================="
cd /project/ebadge-backend
mvn clean package

echo "=================================================="
echo "============= BUILD COMPLETE =============="
echo "=================================================="
echo "=================================================="
echo "============ INSTRUCTIONS ======================="
echo "=================================================="
echo "Connect: vagrant ssh"
echo "=================================================="
echo "ebadge-frontend:"
echo "  cd /project/ebadge-frontend"
echo "  grunt serve"
echo "=================================================="
echo "ebadge-backend:"
echo "  cd /project/ebadge-backend"
echo "  java -jar target/ebadge-backend.jar"
echo "Access the application on:" 
echo "   http://192.168.99.100:8080/hello"
echo "   http://192.168.99.100:8080/rest/requests"
echo "=================================================="
