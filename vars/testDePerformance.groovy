#!/usr/bin/env groovy
def call() {
   sh 'sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t testPlan.jmx -l results.jtl'
   sh 'cat results.jtl'
   perfReport 'results.jtl'
} 

        
