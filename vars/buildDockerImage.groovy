#!/usr/bin/env groovy

def call(String imagename) {
    sh "sudo docker build -t ndamagaye268/${imagename}:latest ."
}
