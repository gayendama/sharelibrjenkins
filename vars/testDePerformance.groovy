#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
     def jmxDir = "${appDir}/testjmeter"

    // Vérifier si le répertoire de tests existe
    if (!fileExists(jmxDir)) {
        error "Le répertoire des tests Jmter ${jmxDir} n'existe pas."
    }
   
    def jmxFiles = sh(script: "find ${jmxDir} -name '*.jmx'", returnStdout: true).trim().split('\n')
    if (jmxFiles.size() == 0) {
        echo "Aucun fichier SoapUI trouvé dans ${jmxDir}."
        return
    }
      // Exécuter JMeter pour chaque fichier trouvé
    jmxFiles.each { file ->
        echo "Exécution des tests JMeter pour ${file}..."
        sh "sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t ${file} -l results.jtl"
        
        // Générer un rapport de performance
        perfReport "results.jtl"
    }
}
