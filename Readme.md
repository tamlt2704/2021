1. Hello world
    javac Main.java
    java Main
2. Add manifest and build jar
    WARNING:
        Warning: The text file must end with a new line or carriage return. The last line will not be parsed properly if it does not end with a new line or carriage return.
    jar cvfm app.jar Manifest.txt *.class
    java -jar app.jar   # would expect to see hello world