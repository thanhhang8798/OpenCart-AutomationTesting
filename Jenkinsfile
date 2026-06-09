node {
    stage('1 - Get code') { // for display purposes
        // Get some code from a GitHub repository
        git branch: 'master', url: 'https://github.com/thanhhang8798/OpenCart-AutomationTesting'

        ansiColor('xterm') {}
        timestamps {}
    }
    stage('2 - Compiler') {
        // Run the build. You must have Maven installed.
        bat 'mvn clean'
    }
    stage('3 - Run test') {
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
         bat 'mvn test'
        }
    }
    stage('4 - Public report') {
        allure commandline: 'AllureReport', includeProperties: false, jdk: '', resultPolicy: 'LEAVE_AS_IS', results: [[path: 'allure-results']]
    }
}
