#!/usr/bin/env groovy
def call (String param) {
    // Scanner l'image Docker avec Trivy
    sh "trivy image --no-progress --exit-code 0 --severity HIGH ${param}:latest"
    sleep 10
}
