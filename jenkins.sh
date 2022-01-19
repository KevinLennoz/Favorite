#!/bin/bash
echo "Adding apt-keys"
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -

sudo sh -c 'echo deb https://pkg.jenkins.io/debian binary/ > \
    /etc/apt/sources.list.d/jenkins.list'
   
echo "Updating apt-get"
sudo apt-get update -y

sudo apt install ca-certificates

echo "Updating apt-get"
sudo apt-get update -y

echo "Installing default-java"
sudo apt-get -y install default-jre 
sudo apt-get -y install default-jdk

echo "Installing jenkins"
sudo apt-get install jenkins -y
sudo service jenkins start

sleep 1m

echo "Installing Maven"
sudo apt-get install maven -y

echo "Installing git"
sudo apt-get -y install git

echo "Installing Jenkins Plugins"
