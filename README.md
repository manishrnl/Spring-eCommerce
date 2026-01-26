## Order of MicroService to start is -

- Make sure to connect with Internet as it is required throughout the project

1. Discovery / Eureka Server
2. Config Server
3. Inventory , Order Service
4. Api Gateway

## To start Zipkin run following command in cmd

```
docker run -d -p 9411:9411 openzipkin/zipkin:latest
```

- hit any request to any microservice via Postman and then trace the response time at http://localhost:9411/zipkin/