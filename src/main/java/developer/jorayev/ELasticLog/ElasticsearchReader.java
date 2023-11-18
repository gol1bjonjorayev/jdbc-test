package developer.jorayev.ELasticLog;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;

public class ElasticsearchReader {
    private final RestHighLevelClient client;

    public ElasticsearchReader(RestHighLevelClient client) {
        this.client = client;
    }

    public void readLogs() throws IOException {
        SearchRequest searchRequest = new SearchRequest("coreteam_logger");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchHit[] searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits().getHits();

//        System.out.println(searchRequest.source());

        Arrays.stream(searchHits)
                .map(hit -> hit.getSourceAsString())
                .forEach(System.out::println);
    }
}
