package cz.kafka.test;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;

public class KafkaAdminClient {

    private final AdminClient client;

    
    public KafkaAdminClient(String bootstrap) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrap);
        props.put("request.timeout.ms", 3000);
        props.put("connections.max.idle.ms", 5000);

        this.client = AdminClient.create(props);
    }
    public boolean verifyConnection() throws ExecutionException, InterruptedException {
    Collection<Node> nodes = this.client.describeCluster()
      .nodes()
      .get();
    return nodes != null && nodes.size() > 0;
}


}
