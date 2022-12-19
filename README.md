# Backend Best Practices Project

Hello! I'm happy to see you here!
The goal of this project is to share a few of best practices of Software Engineering which I could learn during the last years.

Here, you'll see:

* **Automation of Logs**: a solution to automate logs for your application using Aspect Oriented Programming; I'm using the class **LoggingInterceptor** to intercept every method call to print some logs, so we don't need to worry about cross cutting concerns. You can learn more in my blog post here: [my blog post](https://dev.to/victormsti/padronizando-logs-de-uma-aplicacao-java-usando-spring-aop-12ic)

* **REST API operations**: here, I'll show some CRUD operations related to Users and other APIs for authentication and an external API call, which I'll explain further.

* **Authentication**: here, you'll see a simple authentication solution for your application. I'm using Spring Security to secure every routes related to Users CRUD operations. Any other route is public

* **External API calls**: you'll see a pattern for external service calls. I'm using **Feign Client** for REST API calls and using this cool API for this purpose [cat facts api](https://alexwohlbruck.github.io/cat-facts/docs/).

* **Embedded Database**: for this demonstration, I'm using an in memory database (**H2**) with a sql file to create the tables and insert data into them during the application start up.

* **Messaging Service**: I managed to implement a simple messaging system using **RabbitMQ**

* **Transactional Outbox Pattern**: I'll show here a cool architectural pattern for scenario where we need to send a message and commit data into the database. So, this approach is pretty efficient for fault tolerance guarantees in a data intensive scenario. To learn more, check this link [transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html) 

* **Unit Tests**: you'll see patterns for unit tests here: builders, mocks, assertions, so on.

* **Integrations tests** : for integrations tests, I'm using **MockMvc** for testing the controller and the entire workflow. Also I'm using **DataJpaTest** for database integration tests.


## Running the application locally

It's really easy to run this application locally. You just need to do two things:

1. Set up the **local** profile as the environment variable 

```bash
spring.profiles.active = local
```

2. run the **RabbitMQ container** by calling the docker-compose file from the application root folder

```bash
docker-compose up
```

## That's it!

That's it for now! If you have any questions of sugestions. Feel free to contact me any time!
