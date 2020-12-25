1. Hello world
    javac Main.java
    java Main
2. Add manifest and build jar
    WARNING:
        Warning: The text file must end with a new line or carriage return. The last line will not be parsed properly if it does not end with a new line or carriage return.
    jar cvfm app.jar Manifest.txt *.class
    java -jar app.jar   # would expect to see hello world

3. Compile with other javafile
    javac *.java
    java Main java
    jar cvfm app.jar Manifest.txt *.class
    java -jar app.jar java # would expect to see my name is java

4. Using external library
  Download log4j
  Create log4j.properties
  javac -cp .:lib/log4j-1.2.17.jar *.java
  java -cp .:lib/log4j-1.2.17.jar Main java

    run
      jar cvfm app.jar Manifest.txt *.class
      java -cp lib/log4j-1.2.17.jar:. Main java

5. Using package
  package io.code.spweb; Main
  package io.code.spweb.model: User
  javac -cp .:lib/log4j-1.2.17.jar io/code/spweb/Main.java

  It will create .class file under each folder
  ex: io/code/spweb/Main.class
      io/code/spweb/model/User.class

    Run:
      java -jar .:lib/log4j-1.2.17.jar io.code.spweb.Main hello
      should expect there are messages in logexample/log.out

6. Add Gradle build
  gradle run --args hello
  
7. Spring boot
  gradle bootRun
  curl localhost:8080
  actuator endpoint
  http://localhost:8080/actuator/health
  
8. User Repository
   lombok annotation
   User controller
9. Data initialize
    data-h2.sql
    config application.properties to use h2
    initialize sql
10. front end and backend
    ./gradlew :frontend:run
    
11. in frontend
   npm run build && cp -r build/* ../backend/src/main/resources/static 