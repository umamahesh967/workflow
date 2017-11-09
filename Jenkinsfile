pipeline {
    agent { dockerfile true }
    stages {
        stage('Build') {
            steps {
                 sh "docker build -f Dockerfile -t imagename ."
                 sh "docker run -p 8085:8085 imagename"
            }
        }
    }
}
