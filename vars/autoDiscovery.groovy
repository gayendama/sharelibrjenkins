#!/usr/bin/env groovy

def call(Map ctx) {
    script {
        // Ajoute la version de l'application en utilisant le numéro de build
        ctx.BUILD_NUMBER = env.BUILD_NUMBER
        ctx.APP_VERSION = "${ctx.IMAGE_NAME}-${ctx.BUILD_NUMBER}"
        ctx.GIT_BRANCH = sh (script: 'git name-rev --name-only HEAD', returnStdout: true).trim().drop(15)
        println ctx
    }
}
