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
        def jmeterResult = sh(script: "sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter  -n -t ${jmxFile} -l results.jtl")
        perfReport 'results.jtl'
        if (jmeterResult != 0) {
                echo "Un test a échoué pour le fichier ${file}."
                testFailed = true  // Marquer un échec
        }
        if (testFailed) {
            currentBuild.result = 'UNSTABLE'
            echo "Certaines tests ont échoué. Le pipeline est marqué comme instable."
        } else {
            echo "Tous les tests jmeter ont réussi."
        }

    }
        return true
    }    
}
