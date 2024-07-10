
## Setup Instructions

To run this application locally, follow these steps:

### Step 1: Add H2 Database Dependency

Ensure you have added the H2 database dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>1.4.200</version>
    <scope>runtime</scope>
</dependency>
### Step 2: Configure H2 Database Connection
Update your application.properties file with the following configuration for H2 database:

spring.application.name=accountsfiles
spring.datasource.url=jdbc:h2:mem:accounts_files_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.driver-class-name=org.h2.Driver

# Hibernate JPA Configuration
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

spring.servlet.multipart.max-file-size=1024MB
spring.servlet.multipart.max-request-size=1024MB

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
## Run your application (ctrl+F5) and write http://localhost:8080/ in your brawser