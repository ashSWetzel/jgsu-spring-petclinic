pipeline {
    agent any

    stages {
        
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ashSWetzel/jgsu-spring-petclinic.git'
            }
        }
        
        stage('Build') {
            steps {
                bat "mvnw.cmd clean package"
            }

           post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts artifacts: '**/target/*.jar', followSymlinks: false
                }
           }
        }
        
    }
}