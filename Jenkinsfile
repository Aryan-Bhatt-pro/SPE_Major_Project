pipeline{
    environment{
        backend = 'backend' // Specify your backend Docker image name/tag
        frontend = 'frontend' // Specify your frontend Docker image name/tag
        mysqlImage = 'mysql:latest'
        mysqlContainerName = 'mysql-container'
        MYSQL_ROOT_PASSWORD = 'mysql@123'
        MYSQL_PORT = '3306'
        docker_image = ''
        NETWORK = 'deployment_my-network'
    }

    agent any

    stages{

        stage('Stage 0: Pull MySQL Docker Image') {
            steps {
                echo 'Pulling MySQL Docker image from DockerHub'
                script {
                    docker.withRegistry('', 'docker') {
                        docker.image("${mysqlImage}").pull()
                    }
                }
            }
        }

        stage('Stage 0.1: Run MySQL Container') {
            steps {
                script {
                    sh  'docker container stop mysqldb'
                    sh  'docker container rm mysqldb'
                    sh  'docker run --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -d -v "/var/lib/mysql" --network=${NETWORK} mysql:latest'
                }
            }
        }

        stage('1. Git Clone'){
            steps{
                echo 'Cloning the git repo'
                git branch: 'master', url: 'https://github.com/Aryan-Bhatt-pro/SPE_Major_Project.git'
            }
        }

        stage('2. Build Spring Boot App'){
            steps {
                echo 'Building Spring Boot backend'
                dir('backend'){
                    sh 'mvn clean install'
                }
            }
        }

        stage('3. Build backend Docker Image'){
            steps {
                echo 'Building backend Docker image'
                dir('backend'){
                    sh "docker build -t aryanbhatt1812/${backend} ."
                }
            }
        }

        stage('4. Build frontend Docker image') {
            steps {
                echo 'Building frontend Docker image'
                dir('frontend') {
                    echo 'Changing to frontend directory'
                    sh "docker build -t aryanbhatt1812/${frontend} ."
                }
            }
        }

        stage('5. Push backend Docker image to DockerHub') {
            steps {
                echo 'Pushing backend Docker image to DockerHub'
                script {
                    docker.withRegistry('', 'docker') {
                        sh 'docker push aryanbhatt1812/${backend}'
                    }
                }
            }
        }

        stage('6: Push frontend Docker image to DockerHub') {
            steps {
                echo 'Pushing frontend Docker image to DockerHub'
                script {
                    docker.withRegistry('', 'docker') {
                        sh 'docker push aryanbhatt1812/${frontend}'
                    }
                }
            }
        }

        stage('Stage 7: Clean docker images') {
            steps {
                script {
                    sh 'docker container prune -f'
                    sh 'docker image prune -f'
                }
            }
        }

        stage('8. Ansible Deployment'){
            steps{
                // dir('Deployment'){
                //     sh 'ansible-playbook -i inventory deploy.yml'
                // }
                ansiblePlaybook becomeUser: null,
                colorized: true,
                credentialsId: 'localhost',
                disableHostKeyChecking: true,
                installation: 'Ansible',
                inventory: 'Deployment/inventory',
                playbook: 'Deployment/deploy.yml',
                sudoUser: null
            }
        }
    }
}