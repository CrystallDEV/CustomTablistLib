pipeline {
    agent {
        label 'java-17'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                git credentialsId: '9eed7478-7c3f-472d-874d-1294c687815d', url: 'git@github.com:CrystallDEV/CustomTablistLib.git', branch: env.BRANCH_NAME

                withMaven(maven: 'maven-3') {
                    sh "mvn -Dmaven.test.failure.ignore=true clean install"
                }

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
            //post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            //success {
            //    junit '**/target/surefire-reports/TEST-*.xml'
            //    archiveArtifacts 'target/*.jar'
            //}
            //}
        }
        stage('SonarQube') {
            when {
                branch "main"
            }
            stages {
                stage('Analysis') {
                    steps {
                        withSonarQubeEnv('crystall-sonarqube') {
                            withMaven(maven: 'maven-3') {
                                sh "mvn clean verify sonar:sonar"
                            }
                        }
                    }
                }
                stage("Quality Gate") {
                    steps {
                        timeout(time: 2, unit: 'MINUTES') {
                            waitForQualityGate(abortPipeline: true)
                        }
                    }
                }
            }
        }
        stage('Deploy to snapshot') {
            when {
                not {
                    branch 'main'
                }
            }
            steps {
                withMaven(maven: 'maven-3') {
                    configFileProvider([configFile(fileId: '16f65a71-8abe-4a5e-ae13-1aa36c88c4a9', variable: 'MAVEN_SETTINGS')]) {
                        sh 'mvn -s $MAVEN_SETTINGS clean deploy'
                    }
                }
            }
        }
        stage('Deploy to release') {
            when {
                branch "main"
            }
            steps {
                withMaven(maven: 'maven-3') {
                    configFileProvider([configFile(fileId: '16f65a71-8abe-4a5e-ae13-1aa36c88c4a9', variable: 'MAVEN_SETTINGS')]) {
                        sh 'mvn -s $MAVEN_SETTINGS clean deploy'
                    }
                }
            }
        }
        stage('Notify Discord') {
            steps {
                echo 'Notifying Discord..'
//                discordSend(
//                        description: '''''',
//                        footer: "",
//                        link: env.BUILD_URL,
//                        result: currentBuild.currentResult,
//                        title: env.BUILD_TAG,
//                        webhookURL: env.DISCORD_WEBHOOK_URL
//
//                )
            }
        }
    }
}
