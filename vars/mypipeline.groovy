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
   stage('Run JMeter Tests') {
           testDePerformance()
        }
        */
    stage('Run Functional Tests with SoapUI') {
           runSoapUITests()
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
