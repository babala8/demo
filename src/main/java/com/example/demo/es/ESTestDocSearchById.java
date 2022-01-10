package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTestDocSearchById {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //查询数据
        GetRequest request = new GetRequest();
        request.index("user1").id("1001");

        //响应结果
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());
        System.out.println(response.getSource());

        //关闭ES客户端
        esClient.close();
    }
}
