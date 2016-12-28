#!/usr/bin/groovy

def notifyBuild(String buildStatus = 'STARTED') {
    buildStatus =  buildStatus ?: 'SUCCESSFUL'
    def buildHash = env.GIT_COMMIT ?: ''

    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    if (buildHash != '') {
        subject += " [${buildHash}]"
    }
    def summary = "${subject} (${env.BUILD_URL})"

    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
    }

    slackSend (color: colorCode, message: summary)
}