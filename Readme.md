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
