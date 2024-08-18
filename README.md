Для запуска необходимо указать параметры в файле properties:

* NUMBER_PORT - номер порта
* URL_OF_DATABASE - url базы данных, например: jdbc:postgresql://localhost:5432/test 
* DATABASE_USERNAME - имя пользователя базы данных
* DATABASE_PASSWORD - пароль пользователя к серверу базы данных<br>
Для взаимодействия с API предусмотрен swagger-UI, доступный на:
 http://localhost:NUMBER_PORT/swagger-ui/index.html#
Или <br>
java -jar demo-0.0.1-SNAPSHOT.jar --spring.datasource.url=URL_OF_DATABASE --spring.datasource.username=DATABASE_USERNAME --spring.datasource.password=DATABASE_PASSWORD --server.port=NUMBER_PORT
