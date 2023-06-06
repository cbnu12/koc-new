pipeline {
    agent any

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/duckduck93/koc.git'
            }
        }

        stage('Gradle Build') {
            steps {
                echo 'Gradle Build'
                sh "./gradlew clean bootJar"
            }
        }
    }
}
