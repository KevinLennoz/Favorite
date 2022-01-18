Vagrant.configure("2") do |config|
	
	config.vm.define "jenkins", autostart: false do |jenkins|
	    jenkins.vm.box = "bento/ubuntu-18.04"
		jenkins.vm.hostname = "jenkins" 
		jenkins.vm.network "private_network", ip: "192.168.33.10"
		jenkins.vm.provision "jenkins-shell", type:"shell", path: "jenkins.sh"
		jenkins.vm.provision "sonarqube-shell", after: "jenkins-shell", type: "shell", path: "sonarqube.sh"
		jenkins.vm.provider "virtualbox" do |vb|
			vb.name ="jenkins"
			vb.memory = "2048"
			vb.cpus = 1
		end
	end 
	
	config.vm.define "docker", autostart: false  do |docker|
	    docker.vm.box = "bento/ubuntu-20.04"
		docker.vm.hostname = "docker" 
		docker.vm.network "private_network", ip: "192.168.33.20" 
		docker.vm.provision "shell", path: "docker.sh"
		docker.vm.provider "virtualbox" do |vb|
			vb.name ="docker"
			vb.memory = "2048"
			vb.cpus = 1
		end
	end
	
end
