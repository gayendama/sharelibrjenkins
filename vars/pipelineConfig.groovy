#!/usr/bin/env groovy

def call() {
    Map pipelineConfig = readYaml(file: "${WORKSPACE}/pipeline.yaml")
    return pipelineConfig
}
