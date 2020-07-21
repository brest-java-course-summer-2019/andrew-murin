[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/andrew-murin.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/andrew-murin)
[![Coverage Status](https://covers.io/repos/github/brest-java-course-summer-2019/andrew-murin/badge.svg?branch=master)](https://covers.io/github/brest-java-course-summer-2019/andrew-murin?branch=master)

jdk1.8
maven 3+

## Insting
>mvn clean inst

## Running the tests
>mvn clean test

## Run server test
>mvn jetty:run

Open [http:localhost:8081](http://localhost:8081/)

## Rest server

Start REST app:

    cd rest-app
    mvn jetty:run
    
### Try CURL requests

Get  tickets:

    curl -v localhost:8088/tickets
    
Create new ticket via REST:

    curl -H "Content-Type: application/json" -X POST -d '{"ticketId":null,"ticketDirectionFrom":"MINSK","ticketDirectionTo":"BREST"}' -v localhost:8088/ticket
    
