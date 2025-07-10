# Entity Service

Spring Boot 3.1 microservice for Data Hub 3.0. It is running on Java 17.

Entity Service primarily handles the direct retrieval of database entities that do not require a complex workflow process. Examples include:
* Study properties
* Datasets
* Codelists and values
* Resources
  * News
  * Events
  * Funding opportunities
  * Homepage content

# Install and Run

## Maven

### Cloud

If running a cloud configuration locally, AWS CLI needs to be installed and configured.

There are a few environment variables that need to be set in AWS Secrets Manager. 
These variables can also be set to run without AWS locally:
* dbuser
    * Open Search hostname / url
* password
    * database password for dbuser
* host
    * hostname of database
* port
    * database port
* dbname
    * database name
* dbDriverClassName
    * postgres driver

In a specific instance, the only environment variable that needs to be set is:
* spring_profiles_active
    * This should be set to '{environment}'
        * The current environments are dev, test, prod

Once the environment variables are set:
```
mvn clean install 
```
Once all classes are generated, you can run the application with maven or via the application context.
```
mvn spring-boot:run
```

### Endpoint

The base endpoint for this service is:
```
{{hostname}}/api/entity/v1/
```