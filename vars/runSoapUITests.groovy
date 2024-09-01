#!/usr/bin/env groovy
def call() {
    def appDir = "${env.WORKSPACE}/" 
    def resultsDir = "${appDir}soapui-reports"
    // Chemin vers le répertoire contenant les fichiers SoapUI
    def soapuiDir = "${appDir}testsoapui"
    // Créer le répertoire pour les rapports s'il n'existe pas
    sh "mkdir -p ${resultsDir}"
    // Vérifier si le répertoire de tests existe
    if (!fileExists(soapuiDir)) {
        error "Le répertoire des tests SoapUI ${soapuiDir} n'existe pas."
    }    
    // Trouver tous les fichiers .xml dans le répertoire de tests et les exécuter
    def soapuiFiles = sh(script: "find ${soapuiDir} -name '*.xml'", returnStdout: true).trim().split('\n')
    
    if (soapuiFiles.size() == 0) {
        echo "Aucun fichier SoapUI trouvé dans ${soapuiDir}."
        return
    }
    
    // Exécuter SoapUI pour chaque fichier trouvé
    soapuiFiles.each { file ->
        echo "Exécution des tests SoapUI pour ${file}..."
        sh "/opt/SmartBear/SoapUI-5.7.2/bin/testrunner.sh -r -j -f ${resultsDir} ${file}"
    }
}


