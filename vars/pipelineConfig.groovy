#!/usr/bin/env groovy

def call() {
    Map pipelineConfig = readYaml(file: "${workspace}/pipeline.yaml")
    return pipelineConfig
}
