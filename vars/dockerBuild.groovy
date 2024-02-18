final DOCKER_REGISTRY_NAMESPACE = "duytung95nb"

def login() {
    withCredentials([usernamePassword(credentialsId: '89b42c75-2ff6-4dea-9574-ec25ea4ff01f', usernameVariable: 'username', passwordVariable: 'password')]) {
        sh """
            docker login --username="${username}" --password="${password}"
        """
    }
}

def build(String appName) {
    def scriptcontents = libraryResource "docker-compose.yaml"
    writeFile file:"docker-compose.yaml", text: scriptcontents

    sh """
        commit=${env.GIT_COMMIT} serviceName="${appName}" docker-compose build
    """
}

def push(String appName) {
    sh """
        docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}-nginx:${env.GIT_COMMIT}"
        docker push "${DOCKER_REGISTRY_NAMESPACE}/${appName}:${env.GIT_COMMIT}"
    """
}