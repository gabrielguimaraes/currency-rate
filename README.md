# Exchange Currency Rate App (currency-rate)
A simple application responsible for get currency rate from EUR to USD frequently.

Spring Boot (with some modules such as Spring Data/JPA, H2, Junit) were used For building this application. It is possible to use the application by accessing two endpoints which permit you to access the latest currency rate on the database and get all currencies rates from a interval of time.

Every period the exchange rate is fetched from an external resource, and it is possible to configure the frequency by editing (in miliseconds) the property `currency.rate.frequency` inside the file [application.properties](src/main/resources/application.properties).

You can check all the tests running `mvnw test` with the wrapped maven inside the project.

Here you can find how the architecture was build for this application.

![Diagram](img/diagram.png)
[Diagram](img/diagram.png)