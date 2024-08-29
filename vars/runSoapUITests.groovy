#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
    def resultsDir = "${appDir}soapui-reports"

    // Créer le répertoire pour les rapports s'il n'existe pas
    sh "mkdir -p ${resultsDir}"

    // Exécuter SoapUI et générer les rapports HTML
    sh "/opt/SmartBear/SoapUI-5.7.2/bin/testrunner.sh -r -j -f${resultsDir}  calculator-soapui-project.xml"
    publishHTML(target: [
        allowMissing: false,
        keepAll: true,
        reportDir: "${resultsDir}",
        reportFiles: 'index.html',
        reportName: 'Rapport de Test Soapui'
    ])
    // Archiver les rapports
    archiveArtifacts artifacts: "${resultsDir}**/*", allowEmptyArchive: true
}
