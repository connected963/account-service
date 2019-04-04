# Account service

### Objective
Provides an API to manage bank accounts.

**Note: For a real environment Guava EventBus must be replaced with
 some message broker to provide resilience.**

### Features
- Insert account
- Find all accounts
- Delete account
- Transfer money between accounts

### Behavior
When a transfer is requested, the API validates received input and if everything is valid,
 proceed with the operation.
 
The operation executes the following steps:
- Subtract informed amount on the payment account
- Start a **Payment Movement** to keep history of this transaction, with status **PROCESSING**
- Try
    - Add informed amount on the receiver account
    - Create a **Receipt Movement** to keep history of this transaction, with status **COMPLETED**
    - Finalizes the **Payment Movement** created before, with status **COMPLETED**
 - If something goes wrong
    - Revert informed amount subtraction on the payment account
    - Finalizes the **Payment Movement** created before, with status **ABORTED**
    
### Utilization
To compile and generate an executable jar for this application, inside the project directory:

Execute: ```mvn clean package```

To run this application, after generate the executable jar 

Execute: ```java -jar target/account-service.jar```

#### Postman collection
A [Postman collection](account-server.postman_collection.json) was made available to facilitate
 the tests experience.
 
### Utilized technologies
- Java 12
- Javalin
- Jdbi
- Flyway
- Guava
- H2

### Next steps
- Add transactions to sensitive operations
- Refactor ```AccountInsertValidator``` to validate an ```Account``` instead of an ```AccountInsertInputModel```
- Create an exception handler to return human-readable messages on errors