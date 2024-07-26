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
            buildDockerImage p.IMAGE_NAME
        }

}
}
