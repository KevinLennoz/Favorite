pipeline {
    
    agent any 
    
    tools {
        maven "LOCAL_MAVEN"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "*********************CHECKOUT*********************"
                git branch: 'main', url: 'https://github.com/DuvCharles/favorite-orderWS.git'
            }
        }
        
        stage('Compile') {
            steps {
                echo "*********************COMPILE*********************"
                sh 'mvn compile'
            }
        }
        
        stage('Test') {
            steps {
                echo "*********************TEST*********************"
                sh 'mvn test'
            }
            
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "*********************PACKAGE*********************"
                sh 'mvn package -Dmaven.test.skip=true'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            } 
        }
        
        stage('SSH transfer') {
            steps {
                echo "*********************SSH TRANSFER*********************"
                sh 'scp -v -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/OrderWS-Pipeline/Dockerfile vagrant@192.168.33.20:/home/vagrant/'
                sh 'scp -v -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/OrderWS-Pipeline/target/*.jar vagrant@192.168.33.20:/home/vagrant/'
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo "*********************DEPLOY TO STAGING*********************"
                sh 'ssh -v -o StrictHostKeyChecking=no vagrant@192.168.33.20 sudo docker stop demo || true'
                sh 'ssh -v -o StrictHostKeyChecking=no vagrant@192.168.33.20 sudo docker rm demo || true'
                sh 'ssh -v -o StrictHostKeyChecking=no vagrant@192.168.33.20 sudo docker rmi demo || true'
                sh 'ssh -v -o StrictHostKeyChecking=no vagrant@192.168.33.20 sudo docker build -t demo .'
                sh 'ssh -v -o StrictHostKeyChecking=no vagrant@192.168.33.20 sudo docker run -d --name demo -p 8080:8080 demo'
            }    
        }
    }
}