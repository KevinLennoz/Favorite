#Parametrage du repository
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg lsb-release -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

#Installation de docker
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io -y

#Rajouter un utilisateur au groupe docker pour éviter d'utiliser sudo à chaque commande
sudo usermod -aG docker vagrant
sudo apt-get install -y docker-compose

#Démarrage du service pour créer les bds
cd /vagrant/docker-compose-files/
docker-compose up -d --no-recreate