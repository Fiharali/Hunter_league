pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKER_IMAGE = "samurai"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'security',
                    url: 'https://github.com/Fiharali/Hunter_league.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('samurai-sonarqube') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=spring-boot-samurai \
                        -Dsonar.host.url=http://sonar:9000 \
                        -Dsonar.login=\$SONAR_TOKEN
                    """
                }
            }
        }

        stage('Quality Gate Check') {
            steps {
                script {
                    echo "Checking SonarQube Quality Gate..."
                    def qualityGate = sh(
                        script: """
                        curl -s -u "\$SONAR_TOKEN:" \
                        "http://sonar:9000/api/qualitygates/project_status?projectKey=spring-boot-samurai" \
                        | jq -r '.projectStatus.status'
                        """,
                        returnStdout: true
                    ).trim()
                    if (qualityGate != "OK") {
                        error "Quality Gate failed! Stopping the build."
                    }
                    echo "Quality Gate passed! Proceeding..."
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.withServer('unix:///var/run/docker.sock') {
                        docker.build("${DOCKER_IMAGE}:latest")
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.withServer('unix:///var/run/docker.sock') {

                        sh '''
                            docker ps -q --filter "name=hunter-league-app" | grep -q . && docker stop hunter-league-app && docker rm -f hunter-league-app || true
                        '''


                        docker.image("${DOCKER_IMAGE}:latest").run("""
                            --name hunter-league-app \
                            -p 8080:8080 \
                            --detach
                        """)
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
        always {
            cleanWs()
        }
    }
}