pipeline {
    agent {
        docker { image 'node:7-alpine' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'node --version'
		sh 'mvn --version'
		 sh 'mvn -B'
            }
        }
    }
}
