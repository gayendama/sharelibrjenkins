#!/usr/bin/env groovy
def call (String param1, String param2) {
    // Scanner l'image Docker avec Trivy
    sh "trivy image --no-progress --exit-code 0 --severity HIGH ${param1}/${param2}:latest"
    sleep 10
}
