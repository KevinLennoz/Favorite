echo "Updating apt-get******************************************************************"
sudo apt-get update -y

echo "Install the zip utilityt******************************************************************"
sudo apt-get install zip -y

echo "Download sonarqubet******************************************************************"
sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.2.4.50792.zip
sudo unzip sonarqube-9.2.4.50792.zip
sudo mv sonarqube-9.2.4.50792 /opt/sonarqube


echo "Add SonarQube Group and Usert******************************************************************"
sudo groupadd sonar
sudo useradd -d /opt/sonarqube -g sonar sonar
sudo chown sonar:sonar /opt/sonarqube -R

echo "Update User in property file sonar.sht******************************************************************"
sudo sed -i "s/#RUN_AS_USER=/RUN_AS_USER=sonar/g" /opt/sonarqube/bin/linux-x86-64/sonar.sh



echo "Systemd task creationt******************************************************************"
sudo /bin/sh -c "cat <<EOF >> /etc/systemd/system/sonar.service

[Unit]
Description=SonarQube service
After=syslog.target network.target

[Service]
Type=forking

ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop

User=sonar
Group=sonar
Restart=always

LimitNOFILE=65536
LimitNPROC=4096

[Install]
WantedBy=multi-user.target
EOF"

echo "Start sonarqubet******************************************************************"
sudo systemctl enable sonar
sudo systemctl start sonar
sudo systemctl status sonar



