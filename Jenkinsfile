pipeline {
    agent any

    parameters {
    choice(choices: 'default\nremote\nqa\ndev', description: 'which profile to run', name: 'profile')

    }
    stages {
        stage("Package TestProject Jar") {
            agent {
                docker {
                   image "maven:3-alpine"
                   args "-v $HOME/mavenjars/.m2:/root/.m2"
                 }
            }
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Build Image") {
            steps {
                sh "docker build -t dragon12/springboot-selenium ."
            }
        }

        stage("Set up Selenium Grid") {
			steps{
            sh "docker-compose -d hub node chrome"
			}
        }

        stage("Run E2E Tests") {
			steps {
             sh """
                echo "PROFILE=${params.profile}" .env && \
                docker-compose up rune2e
                """
			}
         }

          stage("Destroy Selenium Grid") {
			steps {
              sh "docker-compose down"
			}
         }

    }

}