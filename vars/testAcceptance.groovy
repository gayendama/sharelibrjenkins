#!/usr/bin/env groovy
def  call(String param1, String param2, String param3, String param4) {
    sh "docker run --name ${param1} -d -p ${param2}:${param3} ${param4}:latest"
    sleep 10
   
}
