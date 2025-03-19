pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure Maven version matches Jenkins global tool configuration
    }

    environment {
        DOCKER_IMAGE = 'javacodewiz/employee-service:latest'
    }

    stages {
        stage('Poll From SCM') {
            steps {
                git branch: 'main', url: 'https://github.com/javacodewiz/employee-service.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t $DOCKER_IMAGE ."
                }
            }
        }

        stage('Run Docker Image') {
            steps {
                script {
                    sh '''
                    docker stop employee-service || true
                    docker rm employee-service || true
                    docker run -d -p 9001:9001 --name employee-service $DOCKER_IMAGE
                    '''
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', passwordVariable: 'docker-password', usernameVariable: 'docker-username')]) {
                                     // some block where you can access $docker-username and $docker-password
                                     sh '''
                                         echo "Username: $docker-username"
                                         echo "Password: $docker-password"
                                         docker push $DOCKER_IMAGE
                                     '''
                                 }
                }
            }
        }
    }
}