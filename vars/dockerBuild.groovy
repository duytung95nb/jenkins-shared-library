def build(String appName) {
    sh "commit=${env.GIT_COMMIT} serviceName=${appName} docker compose build --no-cache"
}

def push(String appName) {
    docker.withRegistry("https://index.docker.io/v1/", "89b42c75-2ff6-4dea-9574-ec25ea4ff01f") {
        sh """
            commit=${env.GIT_COMMIT} serviceName=${appName} docker compose push
        """
    }
}