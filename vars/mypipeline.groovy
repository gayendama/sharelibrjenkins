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
    stage ('test acceptence') {
            testAcceptance(p.IMAGE_NAME, p.DOCKER_ID, p.PortContainer, p.PortApp)
   }
   stage('Run JMeter Tests') {
            steps {
                script {
                    sh 'sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t testPlan.jmx -l results.jtl'
                    sh 'cat results.jtl'
                    perfReport 'results.jtl'
                }
            }
        }       
    }
}
