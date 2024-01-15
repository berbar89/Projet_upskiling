pipeline {
    agent any


    stages {

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Import results to Xray') {
            steps {
                step([$class: 'XrayImportBuilder', endpointName: '/testng', importFilePath: 'target/surefire-reports/testng-results.xml', importToSameExecution: 'true', projectKey: 'OR', serverInstance: '542b9320-4710-43eb-90c6-0ff6940c8456'])
            }
        }

    }
}