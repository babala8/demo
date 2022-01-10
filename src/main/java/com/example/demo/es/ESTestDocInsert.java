package com.example.demo.es;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTestDocInsert {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST,PORT,TYPE))
        );

        //插入数据
        IndexRequest request = new IndexRequest();
        request.index(INDEX).id("1002");
        User user = new User();
        user.setName("Marry");
        user.setAge(24);
        user.setSex("女");

        //插入数据，必须转换为 JSON 格式
        String userJson = JSONObject.toJSONString(user);
        request.source(userJson, XContentType.JSON);

        //响应结果
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭ES客户端
        esClient.close();
    }
}