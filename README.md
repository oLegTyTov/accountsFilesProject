# for run this app you have to add dependency h2 database in pom.xml and change application.properties just insert this code:
//
spring.application.name=accountsfiles
spring.datasource.url=jdbc:h2:mem:accounts_files_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

spring.servlet.multipart.max-file-size=1024MB
spring.servlet.multipart.max-request-size=1024MB

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
//
for run application you can run any java file that locates in the folder java/com/example/accountsfiles
and write the url http://localhost:8080/ in your brawser 