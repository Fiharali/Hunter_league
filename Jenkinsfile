pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKER_IMAGE = "samurai"
        DB_HOST = "postgres"
        DB_PORT = "5432"
        DB_NAME = "samurai"
        DB_USER = "samurai"
        DB_PASSWORD = "password"
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
                        // Build with build args for database configuration
                        sh """
                            docker build -t ${DOCKER_IMAGE}:latest \
                            --build-arg DB_URL="jdbc:postgresql://\${DB_HOST}:\${DB_PORT}/\${DB_NAME}" \
                            --build-arg DB_USERNAME="\${DB_USER}" \
                            --build-arg DB_PASSWORD="\${DB_PASSWORD}" \
                            .
                        """
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.withServer('unix:///var/run/docker.sock') {
                        // Stop and remove existing container if it exists
                        sh '''
                            docker ps -q --filter "name=hunter-league-app" | grep -q . && docker stop hunter-league-app && docker rm -f hunter-league-app || true
                        '''

                        // Run new container with network configuration
                        sh """
                            docker run -d \
                            --name hunter-league-app \
                            --network samurai-network \
                            -p 8080:8080 \
                            -e SPRING_DATASOURCE_URL="jdbc:postgresql://\${DB_HOST}:\${DB_PORT}/\${DB_NAME}" \
                            -e SPRING_DATASOURCE_USERNAME="\${DB_USER}" \
                            -e SPRING_DATASOURCE_PASSWORD="\${DB_PASSWORD}" \
                            ${DOCKER_IMAGE}:latest
                        """
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