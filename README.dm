Проект IoT (M2M) приложение

Введение

Основная задача проекта реализовать приложение для интернета вещей (IoT), которое будет собирать информацию с датчиков/сенсоров устройств и позволять анализировать полученную информацию на портале управления Dashboard.

Основные компоненты решения

1. Data Receiver Service, высоко-доступный микро-сервис для получения информации от различных сенсорных устройств по протоколу REST/HTTP в формате JSON и сохранения этой информации в хранилище данных, реализуется и запускается с помощью Spring Boot приложения в отдельной JVM, для целей промышленной эксплуатации запускается в кластере состоящем из нескольких JVM. Поставляется в виде JAR артефакта.
2. Хранилище данных, реализуется на базе данных MySQL и выполняет функцию хранение и предоставления информации полученной с сенсорных устройств. Для управления структурой базы данных MySQL используется Liquibase.
3. User Dashboard, реализуется в виде Sping Web MVC приложения и используется конечными пользователями для просмотра и анализа информации полученной от сенсорных устройств. Поставляется в виде WAR и разворачивается в отдельном контейнере Apache Tomcat + JVM.
4. Для доступа к данным используется общий Data-Access сервис, реализованные с помощью JPA + Hibernate, поставляется в виде JAR.

Основные инструменты

1. IntelliJ IDEA Community edition
2. Java SDK: Open JDK 11.*
3. Apache Maven 3.6.*
4. Apache Tomcat 9.*
5. Spring Framework 5.1.*
6. Spring Boot 2.1.*
7. Hibernate 5.4.*
8. JMeter используется для генерации данных и нагрузочного тестирования

Модель данных

Основные сущности модели данных:

1. Device – устройство
a. Device ID
b. Device details
c. Device values
d. Device serial_number

2. Device Detaild
a. Details ID
b. Details description

3. User – зарегистрированный пользователь ИМ (логин, пароль и т.д.)
a. User ID
b. User name
c. User password

Навигационная карта Dashboard

1. Home page – главная страница
2. Sign up page – страница регистрации нового пользователя
3. Login page – страница авторизации пользователя
4. Device list page – список зарегистрированных устройств, поиск и фильтрация
5. Device details page – список сенсоров одного устройства
<<<<<<< HEAD
6. Sensor details page – детальная информация показаний одного сенсора, может содержать информацию в виде таблици, а так же графиков и диаграмм, имеет возможности для фильтрации по дате
=======
6. Sensor details page – детальная информация показаний одного сенсора, может содержать информацию в виде таблици, а так же графиков и диаграмм, имеет возможности для фильтрации по дате
>>>>>>> origin/master
