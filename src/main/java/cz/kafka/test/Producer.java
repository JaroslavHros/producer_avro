package cz.kafka.test;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

public class Producer {
    private static List<PartitionInfo> partitionsFor;
    public static void main(String[] args) throws Exception {

        //* Properties */  
        String servers = "192.168.56.101:29092,192.168.56.101:29093,192.168.56.101:29094";
        Properties prop = new Properties();
        String topic = "firsTopic";
        prop.setProperty("bootstrap.servers", servers);
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        prop.setProperty("value.serializer", "cz.kafka.test.AvroSerializer");
        prop.setProperty("acks", "1");  
        prop.setProperty("request.timeout", "100000");
        prop.setProperty("batchSize", "1");
        //Logger logger= LoggerFactory.getLogger(App.class);  
        KafkaProducer<Integer, AvroMessage> producer = new KafkaProducer<Integer, AvroMessage>(prop);
        KafkaAdminClient client = new KafkaAdminClient(servers);
        partitionsFor = producer.partitionsFor(topic);
       
        // verify client connection before producing
        try {
            boolean check = client.verifyConnection();
            System.err.println("client can communicate to the broker:" + check);
        } catch (Exception e) {
            System.err.println("client is unable communicate to the broker");
            e.printStackTrace();
        }
        
        // check available partitions 
        try {
             for (int i = 0 ; i < partitionsFor.size() ; i ++) {
                System.out.println(partitionsFor.get(i));
                //logger.debug(partitionsFor.get(i).toString());   
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        //System.out.println("preparing messagess to push to the topic");

        // Creating and sending the messages:
       try{
        for (int i = 1; i <= 100; i++){
            producer.send(new ProducerRecord<>("firsTopic", 0, i, AvroMessage.newBuilder().setId(i).setName(i + "avro value").build()));
            System.err.println("Avro message sent!!");
        }

        }catch(Exception e){
        e.printStackTrace();;
        }
finally{
producer.close();
System.out.println("Successfully closed the client!");
}
}
}