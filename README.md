# HTML-parser
HTML-parser представляет собой приложение, которое по заданному URL получает HTML-код страницы, выделяет текстовые поля из него на основе разделителей, выдает статистику по количеству использованных слов и сохраняет ее в базу данных.

## Используемые технологии
- [JDK 15](https://docs.oracle.com/en/java/javase/15/)
- [Apache Maven 3.6.3](https://maven.apache.org/)
- [MySQL 8.0.23](https://www.mysql.com/)

## Документация
Документация проекта сгенерирована при помощи [Javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html) и доступна в ```doc/index.html```.

## Начало работы

### Настройка подключения к БД
Для подключения к базе MySQL необходимо в файле ```src/main/java/env/Constants.java``` изменить имя пользователя, пароль и порт. По умолчанию запускается на 3306.

### Запуск из командной строки
1. Клонировать репозиторий ```git clone https://github.com/Aigul9/page-stats.git```
2. Перейти в директорию ```cd page-stats```
3. Скомпилировать код ```mvn compile```
4. Запустить проект ```mvn exec:java -Dexec.mainClass="GetStats"```

### Запуск из IDE
1. Перейти в директорию ```src/main/java```
2. Запустить файл ```GetStats.java```

## Логгирование
Для логгирования используется фреймворк [Logback](http://logback.qos.ch/). Конфигурационный файл находится в файле ```src/main/resources/logback.xml```. Все логи сохраняются в ```log/parseError.log```.

## Тесты
Для тестирования функциональности используется библиотека [JUnit](https://junit.org/junit5/). Файл с тестами расположен в ```src/test/java```. Команда для запуска тестов – ```mvn test```.
