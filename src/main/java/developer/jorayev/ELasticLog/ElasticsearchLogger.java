package developer.jorayev.ELasticLog;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class ElasticsearchLogger {
    private final RestHighLevelClient client;

    public ElasticsearchLogger(RestHighLevelClient client) {
        this.client = client;
    }

    public void log(String service, String level, String message) throws IOException {
        XContentBuilder logEntry = XContentFactory.jsonBuilder()
                .startObject()
                .field("timestamp", System.currentTimeMillis())
                .field("service", service)
                .field("level", level)
                .field("message", message)
                .endObject();

        IndexRequest indexRequest = new IndexRequest("coreteam_logger")
                .source(logEntry);

        client.index(indexRequest, RequestOptions.DEFAULT);
    }
}
