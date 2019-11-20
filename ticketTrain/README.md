[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/andrew-murin.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/andrew-murin)
[![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/andrew-murin/badge.svg?branch=master)](https://coveralls.io/github/brest-java-course-summer-2019/andrew-murin?branch=master)

jdk1.8
maven 3+

## Installing
>mvn clean install

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

Get all tickets:

    curl -v localhost:8088/tickets
    
Create new ticket via REST:

    curl -H "Content-Type: application/json" -X POST -d '{"ticketId":null,"ticketDirectionFrom":"MINSK","ticketDirectionTo":"BREST"}' -v localhost:8088/ticket
    
