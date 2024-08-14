#!/usr/bin/env groovy
def call (String param1, String param2) {
    // Scanner l'image Docker avec Trivy
    sh "trivy image --no-progress --exit-code 0 --format json --output trivy_report.json --severity HIGH ${param1}/${param2}:latest"
    archiveArtifacts artifacts: 'trivy_report.json', allowEmptyArchive: true

  //  Publier le rapport Trivy en tant que rapport HTML
    publishHTML(target: [
        allowMissing: false,
        keepAll: true,
        reportDir: '',
        reportFiles: 'trivy_report.json',
        reportName: 'Rapport de Vulnérabilité Trivy'
    ])
    sleep 10
}
