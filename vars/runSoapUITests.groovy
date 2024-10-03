#!/usr/bin/env groovy
def call() {
    def soapuiDir = "/home/ndama/testsoapui"
    def soapuiFiles = findCheckFiles(soapuiDir, "xml")
    
    if (soapuiFiles.size() == 0) {
        return false
    }

    soapuiFiles.each { file ->
        echo "Exécution des tests SoapUI pour ${file}..."
        def soapuiResult = sh(script: "/opt/SmartBear/SoapUI-5.7.2/bin/testrunner.sh -r -j -f ${WORKSPACE}/result ${file}", returnStatus: true)
        junit 'result/*.xml'
        
        if (soapuiResult != 0) {
            echo "Un test a échoué pour le fichier ${file}."
            currentBuild.result = 'UNSTABLE'
            return false
        }
    }
    return true
}


