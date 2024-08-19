#!/usr/bin/env groovy
def call() {
    def appDir = '/var/lib/jenkins/workspace/testperfomance/' 
    def jmxFile = ''

    // Rechercher un fichier .jmx dans le répertoire de l'application
    def files = new File(appDir).listFiles().find { it.name.endsWith('.jmx') }

    if (files) {
        jmxFile = files.name
        echo "Fichier .jmx trouvé : ${jmxFile}"
        sh 'sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter  -n -t ${appDir}${jmxFile} -l results.jtl"
        perfReport 'results.jtl'
    } else {
        echo "Aucun fichier .jmx trouvé dans le répertoire ${appDir}, aucun test ne sera exécuté."
    }
}
