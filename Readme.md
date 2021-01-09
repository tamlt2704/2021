1. gradle list all projects

      gradle -q projects
      
2. build sub project

    gralde :spring-mega-tutorial:build
  
3. run spring boot applicaiton

    run application
    
          gralde :spring-mega-tutorial:bootRun
  
    open this link in a browser
    
        http://localhost:8080/home
      
4. publish to maven local

    list all projects
    
        gradle -q projects
    
    publish to maven local
    
        gradle :spring-mega-tutorial:utilities:publishToMavenLocal 
        or
        gradle :spring-mega-tutorial:utilities:pTML        

5. run a hkrank test
      gralde ":hackerank:test" 
      or       
      cd hackerank
      gradle clean test --tests "hkrank.MainTests.testCompareTripleTest"
