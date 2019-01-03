# stress-tester
This is a small application to determine user's currect stress level by examining user's quotes.

## Prerequisites
* Git
* JDK 8 or above
* Apache Maven
* MongoDB should be configured with a valid connection string

## Database Structure
* Default database name is "stress_db".
* Collection name should be "dep_level".

level|keywords|severity
-----|--------|--------
normal|feel|5
 . |read|
high|kill|7
  .|hit|

## Getting Started
* Make sure you have already configured mongoDB server with above structure.
* Clone this repository into your local file system.
* Find ``application.properties`` file in cloned project and replace xxxxx with your mongoDB connection string and database name (stress_db). Then make sure to save the file.
* Navigate into the location where pom.xml exists using command prompt (terminal)
* Run command `mvn clean package`
* Run command `java -jar target/stress-tester-0.0.1-SNAPSHOT.jar`

## Usage
* Application will run on port 8080.
* If there is any port conflict, project will not start. In such case start application using following command.
`java -jar target/stress-tester-0.0.1-SNAPSHOT.jar --server.port=8081`. Here you can use your own desired port number instead of 8081.
* After succesfull start of application, open your favourite web browser and put this in address bar. `localhost:<your_port_no>/depLevel?sentences=I feel like hit someone`.
* You will get `{"dep_level":"high"}` as the result.
* That it!

