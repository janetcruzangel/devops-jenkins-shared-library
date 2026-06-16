#!/user/bin/env groovy
def call() {
    echo 'building the docker image...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
        sh '''
            echo "Logging in to Docker Hub with username: $DOCKER_USERNAME"
            echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
            docker build -t janetcruzangel/demo-app:jma-2.0 .
            docker push janetcruzangel/demo-app:jma-2.0
        '''
    }
}
