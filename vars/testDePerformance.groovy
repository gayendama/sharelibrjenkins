#!/usr/bin/env groovy
def call() {
    def jmeterDir = "/home/ndama/testjmeter"
    def jmeterFiles = findTestFiles(jmeterDir, "jmx")

    if (jmeterFiles.size() == 0) {
        return false
    }

    jmeterFiles.each { file ->
        echo "Exécution des tests JMeter pour ${file}..."
        sh(script: "sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t ${file} -l results.jtl", returnStatus: true)

        // Ajouter les rapports de performance avec les seuils configurés
        perfReport errorFailedThreshold: 20, errorUnstableThreshold: 20, filterRegex: '', sourceDataFiles: 'results.jtl'
    }
    return true
}

