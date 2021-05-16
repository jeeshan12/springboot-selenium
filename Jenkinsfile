pipeline {
    agent none

    parameters {
    choice(choices: 'default\nremote\nqa\ndev', description: 'which profile to run', name: 'profile')

    }
    stages {
        stage('Package TestProject Jar') {
            agent {
                docker {
                   image 'maven:3-alpine'
                   args '-v $HOME/mavenjars/.m2:/root/.m2'
                 }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage(Build Image) {
            steps {
                sh 'docker build -t dragon12/springboot-selenium .'
            }
        }

        stage('Set up Selenium Grid') {
            sh 'docker-compose -d hub node chrome'
        }

        stage('Run E2E Tests') {
             sh """
                echo "PROFILE=${params.profile}" .env && \
                docker-compose up rune2e
                """
         }

          stage('Run E2E Tests') {
              sh 'docker-compose down'
         }

    }

}