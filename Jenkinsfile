pipeline {
    agent any

    tools {
            jdk 'jdk17'
            gradle 'gradle 8.3'
        }

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
                sh 'docker build -t koc-place:latest .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker stop koc-place || true'
                sh 'docker rm koc-place || true'
                sh 'docker run --name koc-place -dit -p 8100:8080 koc-place:latest'
            }
        }
    }
}
