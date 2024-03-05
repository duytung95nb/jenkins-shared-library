final DOCKER_REGISTRY_NAMESPACE = "duytung95nb"
final DOCKER_REGISTRY_URL = "https://index.docker.io/v1";
def build(String appName) {
    docker.withRegistry(DOCKER_REGISTRY_URL, "89b42c75-2ff6-4dea-9574-ec25ea4ff01f") {
        sh "commit=${env.GIT_COMMIT} serviceName=${appName} docker compose build"
    }
}

def push(String appName) {
    docker.withRegistry(DOCKER_REGISTRY_URL, "89b42c75-2ff6-4dea-9574-ec25ea4ff01f") {
        sh """
            docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}-nginx:${env.GIT_COMMIT}"
            docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}:${env.GIT_COMMIT}"
        """
    }
}