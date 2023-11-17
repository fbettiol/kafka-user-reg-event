# kafka-user-reg-event
Example project to learn how to use kafka. It has 3 modules:
- Kafka-commons: it contains the common classes for the producer and consumer. Here the JSON is converted into a generated class using jsonschema2pojo. As the generated classes are required in the producer and consumer (topics and events definitions), and they need the same version, I have created a new module. 
- kafka-producer: it contains a rest controller on the port 8081 which allows to post user registration events. It is responsible for getting the events and sending them to Kafka. It uses the event classes for the request body of the endpoint. This might not be optimal as they are not necessary the same and might break SOLID. But for learning purposes I considered it was ok.  
- kafka-consumer: it contains just a service which logs in the command line the received user registration events.

These are all independent modules. I understand that it could also be possible to have a multi-module project (and then you dont need the maven local repo). But I am not so familiar with Gradle, so I did it they way I "knew".

Since the idea was to learn kafka, I have not focused on proper rest controllers standards. 

# Run (*it needs docker installed in order to run the broker*).

## 1) Start the kafka broker. 
CD to the kafka-broker folder and execute "docker compose up -d". In order to see the logs, you can use the following commands:
- **docker exec -it CONTAINER-ID /bin/bash**: opens a bash in the container.
- **kafka-console-consumer --bootstrap-server localhost:9092 --topic userregistration --from-beginning**: creates a consumer on the topic 'userregistration' to see the messages going through

## 2) Build the kafka-commons.
CD to kafka-commons and execute **./gradlew build** followed by **./gradlew publishMavenPublicationToMavenLocal**. This will generate the classes, compile them, and publish the artifact to the local maven repository.  

## 3) Build and run the kafka-producer.
CD to kafka-producer and execute **./gradlew build** followed by **./gradlew run**. This will build and execute the producer

## 4) Build and run the kafka-consumer.
CD to kafka-consumer and execute **./gradlew build** followed by **./gradlew run**. This will build and execute the consumer

## 5) Post a user registration event to the producer.
- Path: http://localhost:8081/users/register
- Verb: Post
- Body (JSON):
    ````
    {
        "userId":"4",
        "username":"favio",
        "email": "fav.bet@gmail.com",
        "registrationDate" : "2023-02-10"
    }
    ````
- Curl
  ````
  curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"userId":"4","username":"favio","email": "fav.bet@gmail.com","registrationDate" : "2023-02-10"}' \
  http://localhost:8081/users/register
  ````

After the post is done, a new line should be displayed in the consumer terminal and the consumer microservice. 