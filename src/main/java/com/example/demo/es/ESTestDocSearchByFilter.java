package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTestDocSearchByFilter {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //全量查询
        SearchRequest request = new SearchRequest();
        request.indices(INDEX);

        //构建查询条件-过滤字段
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] excludes = {"age"};//不需要查询的字段
        String[] includes = {};//要查询的字段
        query.fetchSource(includes, excludes);
        request.source(query);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        //查询结果
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        //关闭ES客户端
        esClient.close();
    }
}