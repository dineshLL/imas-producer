package kafka;

import java.util.UUID;

public class UuidGenerator {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
	
}
