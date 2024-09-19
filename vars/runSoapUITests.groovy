#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
    def resultsDir = "/home/ndama/soapui-reports"
    // Chemin vers le répertoire contenant les fichiers SoapUI
    def soapuiDir = "/home/ndama/testsoapui"
    // Créer le répertoire pour les rapports s'il n'existe pas
    sh "mkdir -p ${resultsDir}"
    // Vérifier si le répertoire de tests existe
    if (!fileExists(soapuiDir)) {
    echo "Le répertoire des tests SoapUI ${soapuiDir} n'existe pas, aucun test ne sera effectué."
    return false
} else {
    // Trouver tous les fichiers .xml dans le répertoire de tests et les exécuter
    def soapuiFiles = sh(script: "find ${soapuiDir} -name '*.xml'", returnStdout: true).trim().split('\n')

    if (soapuiFiles.size() == 0 || soapuiFiles[0] == "") {
        echo "Aucun fichier SoapUI trouvé dans ${soapuiDir}."
        return false
    }

    echo "Exécution des fichiers SoapUI trouvés : ${soapuiFiles.join(', ')}"
      def testFailed = false
    // Exécuter SoapUI pour chaque fichier trouvé
    soapuiFiles.each { file ->
        echo "Exécution des tests SoapUI pour ${file}..."
        def soapuiResult = sh(script: "/opt/SmartBear/SoapUI-5.7.2/bin/testrunner.sh -r -j -f ${WORKSPACE}/result ${file}", returnStatus: true)
        junit 'result/*.xml'
        if (soapuiResult != 0) {
                echo "Un test a échoué pour le fichier ${file}."
                testFailed = true  // Marquer un échec
        }
        if (testFailed) {
            currentBuild.result = 'UNSTABLE'
            echo "Certaines assertions ont échoué. Le pipeline est marqué comme instable."
        } else {
            echo "Tous les tests SoapUI ont réussi."
        }

    }
        return true
    }    
}


