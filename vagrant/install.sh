#!/usr/bin/env bash

echo "=================================================="
echo "Aloha! Now we will try to Install Ubuntu 14.04 LTS"
echo "with Java and npm for the Interhack event"
echo "Good luck :P"
echo "=================================================="
echo ""
echo ""
#versions a variables
network_address=192.168.99.99
java_pkg_version=8u102
java_version=1.8.0_102
mvn_version=3.3.9
node_version=4.6.0

echo "=================================================="
echo "RUN UPDATE"
echo "=================================================="
apt-add-repository ppa:git-core/ppa
apt-get -qq update
apt-get -qq upgrade


echo "=================================================="
echo "CLEANING..."
echo "=================================================="
apt-get -y autoremove
apt-get -y autoclean

echo "=================================================="
echo "INSTALL JAVA ${java_version}"
echo "=================================================="
if [ ! -f /vagrant/jdk-${java_pkg_version}-linux-x64.tar.gz ]; then
	echo "Missing jdk binaries for linux-x64 and version ${java_version}"
	exit 1
fi
cp /vagrant/jdk-${java_pkg_version}-linux-x64.tar.gz /opt
cd /opt
tar -xf jdk-${java_pkg_version}-linux-x64.tar.gz
rm jdk-${java_pkg_version}-linux-x64.tar.gz
if [ ! -L java ]; then
	ln -s jdk${java_version} java
fi
if [ ! -L /usr/bin/java ]; then
	ln -s /opt/java/jre/bin/java /usr/bin
	echo "export JAVA_HOME=/opt/java/" >> ~/.profile
fi
java -version

echo "=================================================="
echo "INSTALL MAVEN ${mvn_version}"
echo "=================================================="
cd /opt
wget http://apache.belnet.be/maven/maven-3/3.3.9/binaries/apache-maven-${mvn_version}-bin.tar.gz
tar -xf apache-maven-${mvn_version}-bin.tar.gz
rm apache-maven-${mvn_version}-bin.tar.gz
if [ ! -L maven ]; then
	ln -s apache-maven-${mvn_version} maven
fi
if [ ! -L /usr/bin/maven ]; then
	ln -s /opt/maven/bin/mvn /usr/bin
fi
mvn -version

echo "=================================================="
echo "INSTALL GIT from repository"
echo "=================================================="
apt-get install -y git
git --version

echo "=================================================="
echo "INSTALL NodeJS from repository"
echo "=================================================="
curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
apt-get install -y nodejs
npm --version

echo "=================================================="
echo "INSTALL npm dependencies"
echo "=================================================="
npm install -g grunt-cli bower yo generator-karma generator-angular
apt-get ruby-dev
gem install compass

echo "=================================================="
echo "============= INSTALLATION COMPLETE =============="
echo "=================================================="

