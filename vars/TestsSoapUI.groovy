#!/usr/bin/env groovy
def runSoapUITests() {    
    sh '''               
        # Exécuter les tests SoapUI
        /opt/SmartBear/SoapUI-5.7.2/bin/testrunner.sh -s"CalculatorTestSuite" -c"AdditionTestCase"  calculator-soapui-project.xml
    '''
    
    // Archiver les résultats de SoapUI
    archiveArtifacts artifacts: 'test_soapui/results/**/*', allowEmptyArchive: true
}
