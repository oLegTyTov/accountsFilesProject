
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
