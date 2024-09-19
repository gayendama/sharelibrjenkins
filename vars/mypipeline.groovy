#!/usr/bin/env groovy
def call() {
    node {
        def testsRun = false
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
          testsRun = runSoapUITests()
        if (!testsRun) {
            echo "Les tests SoapUI n'ont pas été exécutés, le stage est marqué comme désactivé."
            currentBuild.result = 'UNSTABLE'
            return
            
        }
    } 
        stage('Deploy') {
        if (false) {  // Désactive cette étape en mettant la condition à false
            echo "Déploiement"
        } else {
            echo "Étape de déploiement ignorée."
        }
    }
    if (testsRun) {
            stage('deploy') {
                echo "Déploiement en cours..."
            }
        } else {
            echo "Le déploiement est ignoré car les tests SoapUI n'ont pas été exécutés."
        }
    stage('Run JMeter Tests') {
              def testsRun = testDePerformance()
              if (!testsRun) {
              echo "Les tests Jmeter n'ont pas été exécutés"
              currentBuild.result = 'UNSTABLE'
              }
        
          }
         
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
