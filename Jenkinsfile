pipeline {
    agent none
    stages {
        stage('Back-end') {
            agent {
                docker { image 'maven:3-alpine' }
            }
            steps {
                sh 'mvn --version'
            }
        }
        stage('Front-end') {
            steps {
                sh "echo 'hello 2'"
                sh 'docker build -f Dockerfile -t test11 .'
                sh "docker run -d -p 8101:8101 --name='something' -t test11"
           		sh "echo 'hello 3...........'"
            }
        }
    }
}