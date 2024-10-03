#!/usr/bin/env groovy
def findTestFiles(directory, fileExtension) {
    if (!fileExists(directory)) {
        echo "Le répertoire ${directory} n'existe pas, aucun test ne sera effectué."
        return []
    }
    
    def testFiles = sh(script: "find ${directory} -name '*.${fileExtension}'", returnStdout: true).trim().split('\n')
    
    if (testFiles.size() == 0 || testFiles[0] == "") {
        echo "Aucun fichier ${fileExtension} trouvé dans ${directory}."
        return []
    }
    
    echo "Fichiers ${fileExtension} trouvés : ${testFiles.join(', ')}"
    return testFiles
}

