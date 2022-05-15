
### install

```
    git clone https://github.com/maksim-maslov/qa-rest-assured-dto
```

### run tests

```
    gradlew clean test 
    gradlew downloadAllure 
    gradlew allureServe  
```

### Структура файлов проекта

```
.allure/                                    --> allure reporter
buildSrc/                                   --> dependency versions  
gradle/                                     --> gradle wrapper                 	
src/ 
  main/ 
    java/
      org.example.qa.restassured/
        dto/                                --> dto
  test/
    java/
      org.example.qa.restassured/
        test/                               --> тесты   
.gitignore                                  --> gitignore     
build.gradle                                --> конфигурация gradle
gradlew                                     --> gradlew
gradlew.bat                                 --> gradlew.bat
README.md                                   --> описание проекта
settings.gradle                             --> модули gradle
```
