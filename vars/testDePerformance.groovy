#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
    //def resultsDir = "/home/ndama/jmeter-reports"
    // Chemin vers le répertoire contenant les fichiers jmeter
    def jmeteriDir = "/home/ndama/testjmeter"
    // Créer le répertoire pour les rapports s'il n'existe pas
    //sh "mkdir -p ${resultsDir}"
    // Vérifier si le répertoire de tests existe
    if (!fileExists(jmeteriDir)) {
    echo "Le répertoire des tests jmeter ${jmeteriDir} n'existe pas, aucun test ne sera effectué."
    return false
} else {
    // Trouver tous les fichiers .jmx dans le répertoire de tests et les exécuter
    def jmeterFiles = sh(script: "find ${jmeteriDir} -name '*.jmx'", returnStdout: true).trim().split('\n')

    if (jmeterFiles.size() == 0 || jmeterFiles[0] == "") {
        echo "Aucun fichier jmeter trouvé dans ${jmeteriDir}."
        return false
    }

    echo "Exécution des fichiers jmeter trouvés : ${jmeterFiles.join(', ')}"
      def testFailed = false
    // Exécuter jmeter pour chaque fichier trouvé
    jmeterFiles.each { file ->
        echo "Exécution des tests jmeter pour ${file}..."
        sh(script: "sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t ${fichier} -l results.jtl", returnStatus: true)
    
    // Ajouter les rapports de performance avec les seuils configurés
    perfReport errorFailedThreshold: 20, errorUnstableThreshold: 20, filterRegex: '', sourceDataFiles: 'results.jtl'
}

        return true
    }    
}
