#!/usr/bin/env groovy
def testDePerformance() {
   sh 'sudo ${param}/bin/jmeter -n -t testPlan.jmx -l results.jtl'
   sh 'cat results.jtl'
   perfReport 'results.jtl'
} 

        
