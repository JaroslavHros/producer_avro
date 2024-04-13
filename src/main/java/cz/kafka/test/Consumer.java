package cz.kafka.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;

public class Consumer {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.56.101:29092,192.168.56.101:29093,192.168.56.101:29094");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");

        try (KafkaConsumer<Integer, AvroMessage> consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new AvroDeserializer())) {
            consumer.subscribe(Arrays.asList("firsTopic"));

            while (true) {
                ConsumerRecords<Integer, AvroMessage> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<Integer, AvroMessage> record : records) {
                    System.out.println("Received message: (" + record.key() + ", " + record.value().toString() + ") at offset " + record.offset());
                }
   }
        }

}
}
