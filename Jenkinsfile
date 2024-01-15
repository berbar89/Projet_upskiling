pipeline {
    agent any

    tools {
        // Installation Maven selon le nom donné dans la configuration globale de Jenkins
        maven "Maven"
    }

    stages {

        stage('Build') {
            steps {

                bat 'mvn clean install'
            }
        stage('Import results to Xray') {
            steps {
                step([$class: 'XrayImportBuilder', endpointName: '/testng', importFilePath: 'target/surefire-reports/testng-results.xml', importToSameExecution: 'true', projectKey: 'OrangeHRM', serverInstance: 'f542b9320-4710-43eb-90c6-0ff6940c8456'])
            }
        }
        }
    }
}