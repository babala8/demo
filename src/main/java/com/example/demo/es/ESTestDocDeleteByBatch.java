package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTestDocDeleteByBatch {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //批量删除数据
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index(INDEX).id("1002"));
        request.add(new DeleteRequest().index(INDEX).id("1003"));
        request.add(new DeleteRequest().index(INDEX).id("1004"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());//运行时长
        System.out.println(response.getItems());

        //关闭ES客户端
        esClient.close();
    }
}