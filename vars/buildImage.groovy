#!/user/bin/env groovy
def call(String imageName) {
    echo 'building the docker image...'
    //Use double quotes to pass a parameter
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
        sh 'echo "Logging in to Docker Hub with username: $DOCKER_USERNAME"'
        sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
        sh "docker build -t $imageName ."
        sh "docker push $imageName"
    }
}
