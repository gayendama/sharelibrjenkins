#!/usr/bin/env groovy

def call(String param) {
    sh "sudo docker build -t ${param}/${param}:latest ."
}
