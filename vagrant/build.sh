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

cp /project/scripts/ /home/vagrant
chmod +x /home/vagrant/*.sh

echo "=================================================="
echo "============= BUILD COMPLETE =============="
echo "=================================================="
echo "=================================================="
echo "============ INSTRUCTIONS ======================="
echo "=================================================="
echo "Connect: vagrant ssh"
echo "=================================================="
echo "ebadge-frontend:"
echo "  ./startFrontend.sh"
echo "  ./stopFrontend.sh"
echo "Access the application on:" 
echo "   http://192.168.99.100:9000"
echo "=================================================="
echo "ebadge-backend:"
echo "  ./startBackend.sh"
echo "  ./stopBackend.sh"
echo "Access the application on:" 
echo "   http://192.168.99.100:8080/rest/requests"
echo "=================================================="
