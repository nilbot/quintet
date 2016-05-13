Quintet
====

### CI by Codeship
[![Codeship Status for master](https://www.codeship.io/projects/7e6bde90-aa98-0133-94fa-4a9cc2c4d260/status?branch=master)](https://codeship.com/projects/131067)

## Note on running code
### Choose running Main Class
There are currently two main classes in the project. 
* `ConsoleApp` for console IO
* `WSApp` for interacting with layered web service [quintet-ui](https://github.com/nilbot/quintet-ui/).

To select the main class you have to modify the `build.gradle` file to specify the main class.

## Develop

* `git clone https://github.com/nilbot/quintet.git`
* `cd quintet`
* `./setup_once.sh`
* `./gradlew check`
* import project using IntellijIdea

## Run (build)

### using git run against quintet-ui
* `git clone https://github.com/nilbot/quintet.git`
* `cd quintet`
* `./gradlew clean jar`
* `java -jar build/libs/quintet-1.1.0.jar`

### run console app
* modify build.gradle to point Main-Class to ConsoleApp
* `java -jar build/libs/quintet-1.1.0.jar`
* 

### use downloaded zip or tarball
* (tar xvf|unzip) quintet-v1.2.0.(tar.gz|zip)
* `./gradlew clean jar`
* `java -jar build/libs/quintet-1.1.0.jar`
* 

## Auto Deploy
Quintet is already setup as auto deploy and run in background on my server. Head to the https://demo.nilbot.net to see the result

