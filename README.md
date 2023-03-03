# Scanuvach

## шо це?
Це ПЗ призначене для
- сканування вразливостей в мережі
- збору відсканованої до БД
- презентації результатів в WEB інтерфейсі

## Як зібрати?

- ОС Linux
- Java JDK 17 і вище
- встановити MySQL Server 8ї версії і вище
- завести схему ``create schema scanuvach;``
- Встановити maven
- збілдити ``mvn clean install``
- зібраний jar буде в папці ```target/scanuvach-1.0.0-SNAPSHOT.jar```
- запустити як ```java -jar scanuvach-1.0.0-SNAPSHOT.jar```

## Стек

- java
- MySQL
- [SpringBoot](https://spring.io/) основний фреймворк
- [Hibernate](https://hibernate.org/) ORM
- [Quartz](http://www.quartz-scheduler.org/) бібліотека для плануваня джоб
- [Flyway](https://flywaydb.org/) версіонування бази
- [Thymeleaf](https://www.thymeleaf.org/) двіжок темплейтів
- 
