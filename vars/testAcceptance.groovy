#!/usr/bin/env groovy
def  call(String param1, String param2, int param3, int param4) {
    sh "docker run --name ${param1} -d -p ${param3}:${param4} ${param1}:latest"
    sleep 10
   
}
