final DOCKER_REGISTRY_NAMESPACE = "duytung95nb"

def build(String appName) {
    docker.withRegistry("https://index.docker.io/v1", "89b42c75-2ff6-4dea-9574-ec25ea4ff01f") {
        docker.build("${DOCKER_REGISTRY_NAMESPACE}/${appName}:${env.GIT_COMMIT}", 'app')
    }
}

def push(String appName) {
    sh """
        docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}-nginx:${env.GIT_COMMIT}"
        docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}:${env.GIT_COMMIT}"
    """
}