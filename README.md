# Kafka Producer (Avro'
This is simple Java App which is can produce and also consume avro messages from kafka cluster.

## Prerequisities
- running kafka cluster with
- create topic in kafka cluster
- dependencies like kafka-clients, avro-tools etc.. (for details see `pom.xml`)
## Usage
1. clone the repo
2. use avro-tools to compile avro schema to the java class using:
   ```bash
   java -jar /path/to/avro-tools-1.11.1.jar compile schema schema.avsc .
   ```
3. modify properties for producer/consumer according to your requirements
4. run the app from your IDE or compile it and run it from command line.


 ## Further developmnet
 - integrate with schema registry
 - dockerize it
 - try it with spring
 
