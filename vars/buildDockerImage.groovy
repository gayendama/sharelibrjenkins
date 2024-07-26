#!/usr/bin/env groovy

def call(String param1, String param2) {
    sh "sudo docker build -t ${param2}/${param1}:latest ."
}
