#!/usr/bin/env groovy
def call (String param) {
    sh "docker stop ${param}"
    sh "docker rm ${param}"
}
