def call(Map config = [:]) {
    sh "printenv"
    sh "echo Hello ${config.name}. Today is ${config.dayOfWeek}."
}