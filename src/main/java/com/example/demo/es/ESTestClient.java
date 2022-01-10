package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;
import java.util.Arrays;

public class ESTestClient {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST,PORT,TYPE))
        );

        System.out.println(Arrays.asList(new String[]{INDEX}));
        //删除索引
        DeleteIndexRequest requestDelete = new DeleteIndexRequest(INDEX);
        AcknowledgedResponse responseDelete = esClient.indices().delete(requestDelete, RequestOptions.DEFAULT);
        //打印删除结果
        System.out.println("删除结果：" + responseDelete.isAcknowledged());

        //创建索引，索引名称为 user
        CreateIndexRequest requestCreate = new CreateIndexRequest(INDEX);
        CreateIndexResponse responseCreate = esClient.indices().create(requestCreate, RequestOptions.DEFAULT);//RequestOptions.DEFAULT 默认的请求配置
        //响应状态
        boolean result = responseCreate.isAcknowledged();
        System.out.println("索引操作结果：" + result);

        //查询索引
        GetIndexRequest requestRead = new GetIndexRequest(INDEX);
        GetIndexResponse responseRead = esClient.indices().get(requestRead, RequestOptions.DEFAULT);

        //打印响应数据
        System.out.println(responseRead.getAliases());          //{user=[]}
        System.out.println(responseRead.getDataStreams());      //{}
        System.out.println(responseRead.getIndices());          //[Ljava.lang.String;@47e2e487
        System.out.println(responseRead.getDefaultSettings());  //{}
        System.out.println(responseRead.getMappings());         //{user=org.elasticsearch.cluster.metadata.MappingMetadata@991ce8dd}
        System.out.println(responseRead.getSettings());         //{user={"index.creation_date":"1629039746931","index.number_of_replicas":"1","index.number_of_shards":"1","index.provided_name":"user","index.routing.allocation.include._tier_preference":"data_content","index.uuid":"3-HT2CkQRyC57DqI3kmcbQ","index.version.created":"7140099"}}

        //关闭ES客户端
        esClient.close();
    }
}
