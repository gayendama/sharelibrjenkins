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
    stage ('test d'acceptence') {
            testAcceptance(p.IMAGE_NAME, p.DOCKER_ID, p.PortApp, p.PortContainer)
   }
   stage('Run JMeter Tests') {
                steps {
                    testDePerformance()
                }
            }


}
}
