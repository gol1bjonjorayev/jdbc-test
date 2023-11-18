package developer.jorayev.ELasticLog;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class LoggerMain {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client;
        client = new RestHighLevelClient(RestClient.builder(new org.apache.http.HttpHost("localhost", 9200, "http")));

        ElasticsearchLogger logger = new ElasticsearchLogger(client);
        logger.log("jdbc-test", "INFO", "Log message from microservice");

        ElasticsearchReader reader = new ElasticsearchReader(client);
        reader.readLogs();

        client.close();
    }
}
