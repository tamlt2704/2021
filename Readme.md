1. hello world with gradle
    gradle -q helloWorld

    -q: quiet

    doLast: last action that's executed for a task


2. Dynamic task definition
    startSession -> yayGradle0  -> yayGradle1  -> yayGradle2

3. gralde commands

    All tasks runable from root project
        gralde -q tasks
        gralde -q tasks --all
    Task execution
        gradle yayGradle0 groupTherapy
    Task name abbreviation
        gradle yG0 gT
    Excluding a task from execution
        gradle groupTherapy -x yayGradle0
    Commandline options
        gradle groupTherapy -is

        -i: info
        -s: stack trace
        -b: build file
        --offline: load local repository
    Gradle daemon
        gradle --daemon