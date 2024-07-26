#!/usr/bin/env groovy

def call() {
    Map pipelineConfig = readYaml(file: "${Workspaces}/pipeline.yaml")
    return pipelineConfig
}
