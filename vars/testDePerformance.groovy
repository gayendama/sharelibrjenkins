#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
   
    def jmxFile = sh(script: "ls ${appDir}*.jmx | head -n 1", returnStdout: true).trim()

     if (jmxFile) {
        echo "Fichier .jmx trouvé : ${jmxFile}"
        sh "sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter  -n -t ${jmxFile} -l results.jtl"
        perfReport 'results.jtl'
    } else {
        echo "Aucun fichier .jmx trouvé dans le répertoire ${appDir}, aucun test ne sera exécuté."
    }
}
