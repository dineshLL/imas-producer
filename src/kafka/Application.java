package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	public static void main(String[] args) {
		// Check arguments length value

		Logger logger = LoggerFactory.getLogger(Application.class);
		
		//Assign topicName to string variable
		String topicName = "base-q";
		
		// create instance for properties to access producer configs   
		Properties props = new Properties();

		//Assign localhost id
		props.put("bootstrap.servers", "192.168.100.199:9092");

		//Set acknowledgements for producer requests.      
		props.put("acks", "all");

		//If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		//Specify buffer size in config
		props.put("batch.size", 16384);

		//Reduce the no of requests less than 0   
		props.put("linger.ms", 1);

		//The buffer.memory controls the total amount of memory available to the producer for buffering.   
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", 
				"org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", 
				"org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String, String> producer = new KafkaProducer
				<String, String>(props);
		
		logger.info("producer created");

		for(int x = 0; x < 2; x++) {
			producer.send(new ProducerRecord<String, String>(
					topicName, 
					Integer.toString(1),
					"{\"imas\": {\"hop\": 0, \"cmd\": \"test-1\", \"role\": \"Dinesh\", \"uuid\": \"" + UuidGenerator.generateUUID()  +"\"}, \"payload\": {\"message\": \"this is for test 1\"}}"
					));
			
			/*producer.send(new ProducerRecord<String, String>(topicName, 
					Integer.toString(1), "{\"imas\": {\"cmd\": \"test-2\", \"role\": \"Dinesh\", \"hash\": \"Dinesh\"}, \"payload\": {\"message\": \"this is for test 2\"}}"));*/
		
			logger.info("data item " + x + " sent");
		}

		producer.close();
	}

}
