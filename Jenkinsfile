pipeline {
    agent any

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/cbnu12/koc-place.git', credentialsId: '18f7a807-9a0e-496f-9ebe-730697ad337a'
            }
        }

        stage('Gradle Build') {
            steps {
                sh './gradlew clean bootJar'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'sudo docker build -t koc:latest .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'sudo docker stop koc-place || true'
                sh 'sudo docker rm koc-place || true'
                sh 'sudo docker run --name koc-place -dit -p 8100:8080 koc-place:latest'
            }
        }
    }
}
