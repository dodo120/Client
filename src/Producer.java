import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			// temperature humidité et luminosite
			
		/*	for(int i=0;i<6;i++) {
				int nombreAleatoire = 10 + (int)(Math.random() * ((30 - 10) + 1));
				Publisher publisher = new Publisher(String.valueOf(i),String.valueOf(nombreAleatoire));
				byte[] byteArray = getByteArray(publisher);
				
				channel.basicPublish("", QUEUE_NAME, null, byteArray);
				System.out.println(" [x] Sent '" + byteArray + "'");
			} */
			
			String data = "{\"id\":0, \"date\":\"12/10/2011\", \"temp\":25, \"humidite\":12, \"lum\":12\n}";
			channel.basicPublish("", QUEUE_NAME, null, data.getBytes(StandardCharsets.UTF_8));
			System.out.println(" [x] Sent '" + data + "'");
			
		}
	}
	
	public static byte[] getByteArray(Publisher publisher) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(publisher);
		return out.toByteArray();
		}
	
}
