
pipeline {

    agent any

    parameters {
        choice(
            name: 'PROFILE',
            choices: ['dev', 'test'],
            description: 'Select Maven profile'
        )
    }

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Display Profile') {
            steps {
                echo "Selected Maven Profile: ${params.PROFILE}"
            }
        }

        stage('Maven Build') {
            steps {
                bat "mvn clean package -P${params.PROFILE}"
            }
        }

        stage('Verify Build Output') {
            steps {
                bat 'type target\\classes\\build-info.properties'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

    }

    post {
        success {
            echo 'Maven Profile Build Successful!'
        }

        failure {
            echo 'Maven Profile Build Failed!'
        }
    }
}
