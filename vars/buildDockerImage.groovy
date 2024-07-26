#!/usr/bin/env groovy

def call(String IMAGE_NAME) {
    sh "sudo docker build -t ndamagaye268/${IMAGE_NAME}:latest ."
}
