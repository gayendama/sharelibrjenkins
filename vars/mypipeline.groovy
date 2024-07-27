def call() {
    node {
    stage('Checkout') {
        checkout scm
    }
    
    def p = pipelineConfig()

    stage('Auto discovery') {
        autoDiscovery(p)
    }

    stage ('Build docker images') {
            buildDockerImage(p.IMAGE_NAME, p.DOCKER_ID)
    }
<<<<<<< HEAD
    stage ('test d'acceptence') {
            testAcceptance(p.IMAGE_NAME, p.DOCKER_ID, p.PortContainer, p.PortApp)
   }
   stage('Run JMeter Tests') {
                steps {
                    testDePerformance()
                }
            }
=======
    stage ('test acceptence') {
            testAcceptance(p.IMAGE_NAME, p.DOCKER_ID, p.PortApp, p.PortContainer)
    }
>>>>>>> 0c480c99930f034969271d405f5159a8dbbc5b28


}
}
