pipeline {
    agent any
    environment {
        // JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        // PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    stages {
        stage('Git Checkout') {
            steps {
                git url: 'https://github.com/fullstackjava082023/fullstack-project-tacos'
            }
        }
        stage('Build the application') {
            steps {
                sh   'echo $JAVA_HOME'
                // Use the sh or bat step to execute commands
                sh 'mvn clean package'
            }
        }
        stage('Build the docker image') {
            steps {
                // Use the sh or bat step to execute commands
                sh 'docker build -t shashkist/fullstack-project-tacos .'
            }
        }
        stage('Push the docker image') {
            steps {
                // Use the sh or bat step to execute commands
                sh 'docker login -u shashkist -p $DOCKER_PASSWORD'
                sh 'docker push shashkist/fullstack-project-tacos'
            }
        }

        stage('Compose the application with docker') {
            steps {
                // Use the sh or bat step to execute commands
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }


    }

}
