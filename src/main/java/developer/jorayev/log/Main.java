package developer.jorayev.log;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.elasticsearch.xcontent.XContentType;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Main {
//    public static void main(String[] args) throws UnknownHostException {
////        ElasticsearchLog log = new ElasticsearchLog();
////        String string = log.addLogELC("golibjon", "Xatolik", "UserController");
////        System.out.println(string  );
//
//        Settings setting = Settings.builder().put("cluster.name", "golibjonjorayev").build();
//
//        TransportClient client = new PreBuiltTransportClient(setting).addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9200));
//        Map<String, Object> json = new HashMap<>();
//        json.put("name", "golibjon");
//        json.put("age", "21");
//        json.put("birthday", "1999");
//
//        IndexResponse indexResponse = client.prepareIndex("testIndex", "testType", "1").setSource(json, XContentType.JSON).get();
//        System.out.println(indexResponse.getIndex());
//        client.close();
//    }
//
//

    public static void main(String[] args) throws IOException {
        //AddItem
//        try (RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new org.apache.http.HttpHost("localhost", 9200, "http")
//                )
//        )) {
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("name", "golibjon");
//            jsonMap.put("age", "21");
//            jsonMap.put("birthday", "1999");
//
//            IndexRequest indexRequest = new IndexRequest("testindex", "testtype", "1")
//                    .source(jsonMap);
//
//            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//
//            System.out.println("Index: " + indexResponse.getIndex());
//            System.out.println("Type: " + indexResponse.getType());
//            System.out.println("Id: " + indexResponse.getId());
//            System.out.println("Version: " + indexResponse.getVersion());
//            System.out.println("Result: " + indexResponse.getResult());
//        }

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new org.apache.http.HttpHost("localhost", 9200, "http")
                )
        )) {
            SearchResponse searchResponse = client.search(new SearchRequest("testindex").source(new SearchSourceBuilder()), RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits
            ) {
                Map<String, Object> source = hit.getSourceAsMap();
                String name = (String) source.get("name");
                String age = (String) source.get("age");
                String birthday = (String) source.get("birthday");

                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Birthday: " + birthday);
            }
        }
    }
 }
