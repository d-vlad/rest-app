## Simple Rest Application with Swagger

Rest application template able to handle simple operations on the database such as add,update,delete.

1. The first step is to build the application:
> maven clean install

2. Run it in development
> mvn spring-boot:run

3. Check that the first [Check if your application is working](http://localhost:8080/customers/test)


4. Now behind the scene you have a in memory database h2 that will be used for testing purposes. 
Here you will be able to interact directly with the database  [H2-Console-UI](localhost:8080/h2-console)

5. Intellij Launchers added to the project. Please import them on your in case are not automatically detected


## Post request examples
Here some examples of how to interact with the Customers table and the operations that are allowed such as: add, update, upsert, delete and retrieve all.

Check if your application is up and running
```bash
curl --location 'http://localhost:8080/customers/test'
```

Add a new customer to the database
```bash
curl --location 'http://localhost:8080/customers/add' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "name": "This is a dummy entry for the name"
}'
```

Upsert a new customer to the database. Update the customer or add it
```bash
curl --location --request PUT 'http://localhost:8080/customers/update' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2,
    "name": "This is a dummy entry for the name upsert"
}'
```

Update or throw an error if customer is missing
```bash
curl --location 'http://localhost:8080/customers/update' \
--header 'Content-Type: application/json' \
--data '{
    "id": 3,
    "name": "This is a dummy entry for the name"
}'
```

Delete Customer by ID
```bash
curl --location --request DELETE 'http://localhost:8080/customers/delete/2'
```

Retrieve all customers from the database
```bash
curl --location 'http://localhost:8080/customers/all'
```