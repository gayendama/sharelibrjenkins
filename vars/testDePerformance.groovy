#!/usr/bin/env groovy
def call() {
   def jmxFile = 'testPlan.jmx'
   if (fileExists(jmxFile)) {
   sh 'sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t testPlan.jmx -l results.jtl'
   sh 'cat results.jtl'
   perfReport 'results.jtl'
    } else {
        echo "Le fichier ${jmxFile} n'existe pas, aucun test ne sera exécuté."
    }
} 

        
