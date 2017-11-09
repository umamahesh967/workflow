pipeline {
    agent { dockerfile true }
    stages {
        stage('Build') {
            steps {
                 sh "docker build -t imagename ."
                 sh "docker run -p 8085:8085 imagename"
            }
        }
    }
}
