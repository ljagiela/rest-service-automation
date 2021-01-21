pipeline {
    agent {
        label "master"
    }

    triggers {
        cron('50 6 * * *')
    }

    tools {
        jdk "jdk11"
    }

    parameters {
        choice(choices: ['stg', 'dev', 'prod'], description: 'environment to run', name: 'Deployment_Environment')
    }

    options {
//        gitLabConnection('gitlab')
        timeout(time: 15, unit: 'MINUTES')
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', numToKeepStr: '30')
//        gitlabBuilds(builds: ['api-automation-build', 'api-automation-publish'])
    }

    environment {
        SLACK_NAME = "<${JOB_URL}|tested-service Test Automation>"
        SLACK_CHANNEL = "jenkins"
        SLACK_TOKEN = "Slack"
    }

    stages {
        stage("Initialization") {
            steps {
                buildName "#${BUILD_NUMBER} : ${params.Deployment_Environment}"
            }
        }
        stage('Run tests') {
            steps {
                sh "./gradlew clean test -Penv=${params.Deployment_Environment}"
            }
        }
    }

    post {
        always {
            junit 'build/test-results/test/*.xml'
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
        success {
            slackSend channel: "${env.SLACK_CHANNEL}",
                    color: 'good',
                    message: "${env.SLACK_NAME} on '${params.Deployment_Environment}' tests succeeded: <${currentBuild.absoluteUrl}|${currentBuild.fullDisplayName}>",
                    teamDomain: 'peer39workspace',
                    tokenCredentialId: "${env.SLACK_TOKEN}"
        }
        failure {
            archiveArtifacts artifacts: 'build/reports/tests/test/**/*', fingerprint: true
            slackSend channel: "${env.SLACK_CHANNEL}",
                    color: 'danger',
                    message: "${env.SLACK_NAME} on '${params.Deployment_Environment}' tests failed: <${currentBuild.absoluteUrl}|${currentBuild.fullDisplayName}>",
                    teamDomain: 'peer39workspace',
                    tokenCredentialId: "${env.SLACK_TOKEN}"
        }
    }
}
