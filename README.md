# Автотестирование API rickandmortyapi.com и reqres.in с использованием cucumber, junit, maven, rest assured, allure
Данный комплекс тестов предназначен для тестирования API.
Тест написан на Java 8

### Какие тесты проводит?
1. Тестирование API Рик и Морти на получение информации о персонаже, эпизодах и сравнение расы и локации персонажей
2. Тестирование создания и обновлення данных по json для API  reqres.in

## Как запустить?
1. Сохраните данный репозиторий в свою папку.
2. Запустите тест с консоли, использовав команду:
   ```
    mvn clean test
   ```

3. Либо, запустите JUnit тест, с помощью класса testApi, по пути:
   ```
    src\test\java\testApi
   ```
4. Либо, запустите Cucumber тест, с помощью runner класса RunnerTest или через feature, по пути:
   ```
    src\test\java\RunnerTest
    src\test\java\resources\feature
   ```
5. Далее сформируйте отчёт Allure, с помощью команды:
   ```
    mvn allure:serve
   ```

### Входные данные
Задаются в файле properties:
   ```java
    src\test\resources\props.properties
   ```