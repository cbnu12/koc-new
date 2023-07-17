pipeline {
    agent any

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/cbnu12/koc-place.git', credentialsId: 'GitHub-duckduck-id'
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
                sh 'sudo docker stop koc || true'
                sh 'sudo docker rm koc || true'
                sh 'sudo docker run --name koc --network koc-network -dit -p 8100:8080 koc:latest'
            }
        }
    }
}
