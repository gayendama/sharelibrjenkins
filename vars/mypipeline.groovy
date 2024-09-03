#!/usr/bin/env groovy
def call() {
    node {
    stage('Checkout') {
        checkout scm
    }
    
    def p = pipelineConfig()

    stage('Auto discovery') {
        autoDiscovery(p)
    }
  /*  stage ('Build docker images') {
            buildDockerImage(p.IMAGE_NAME, p.DOCKER_ID)
    }
    stage ('test acceptence') {
            testAcceptance(p.IMAGE_NAME, p.DOCKER_ID, p.PortContainer, p.PortApp)
   }
           */
    stage('Run Functional Tests with SoapUI') {
        def testsRun = runSoapUITests()
        if (!testsRun) {
            echo "Les tests SoapUI n'ont pas été exécutés, le stage est marqué comme désactivé."
            currentBuild.result = 'UNSTABLE'
            return
            
        }
    } 
    stage('deploy') {
        if (testsRun) {
                    echo "Déploiement en cours..."
                    // Code de déploiement ici
                } else {
                    echo "Le déploiement est ignoré car les tests SoapUI n'ont pas été exécutés."
                }
    }
   // stage('Run JMeter Tests') {
     // testDePerformance()
    // }          
/*
    stage('Scan vurlnerablite') {
           scanVulnerabilities(p.DOCKER_ID, p.IMAGE_NAME)
        }
    stage('Cleanup') {
           Cleanup p.IMAGE_NAME
        }

      */ 
    }
}
