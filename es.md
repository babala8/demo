## es

查询问题网址 https://stackoverflow.com/questions

### 1.新增索引

**localhost:9200/shopping**

**put请求方式**

### 2.**查看所有索引**

http://localhost:9200/_cat/indices?v

![image-20211210104446444](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20211210104446444.png)





### 3.新增数据

**发送：put 或Post http://localhost:9200/索引名称/doc/id值（如果不指定id值ES会自动生成ID）**

http://localhost:9200/shopping/_doc/1001

1001表示唯一标识ID

ID为递增且唯一的，下一个1002

![image-20211213154227840](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20211213154227840.png)

```json
{
    "title": "小米手机",
    "category": "小米",
    "images": "http://www.baidu.com",
    "price": 3999.00
}
```



### 4.查询数据

#### 4.1根据id查询文档

http://localhost:9200/shopping/_doc/1001

1001表示唯一标识，即查询的ID这一条的数据

1002

![image-20211213154732870](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20211213154732870.png)



#### 4.2**查询所有记录**

发送 get  `http://localhost:9200/索引名称/_doc/_search`

示例： 发送 get  `http://localhost:9200/shopping/_search`

**查询名称中包括spring 关键字的的记录**

发送：get   http://localhost:9200/索引名称/doc/_search?q=name:bootstrap

**查询学习模式为201001的记录**

发送 get   http://localhost:9200/索引名称/doc/_search?q=studymodel:201001

![image-20211215143835824](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20211215143835824.png)

#### 查询结果字段解释

took：本次操作花费的时间，单位为毫秒。

timed_out：请求是否超时

_shards：说明本次操作共搜索了哪些分片

hits：搜索命中的记录

hits.total ： 符合条件的文档总数 hits.hits ：匹配度较高的前N个文档

hits.max_score：文档匹配得分，这里为最高分

_score：每个文档都有一个匹配度得分，按照降序排列。

_source：显示了文档的原始内容。



### 5.数据修改

#### 5.1全量修改

**全量修改时**，向ES服务器发送`PUT`请求：`http://localhost:9200/shopping/_doc/1002`



#### 5.2部分修改

**局部修改时**，向ES服务器发送`POST`请求：`http://localhost:9200/shopping/_doc/1003/_update`

```json
{
    "doc": {
        "price": 3999.00
    }
}
```



![image-20211215143607606](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20211215143607606.png)









### 倒排索引

Elasticsearch 使用一种称为”倒排索引"的结构，它适用于快速的全文搜索。一个倒排索引由文档中所有不重复词的列表构成，对于其中每个词，有一个包含它的文档列表，这样可以通过某个单词快速的找到其所在的文档：

倒排索引包含两部分：单词词典（Team Dictionary）和倒排列表（Posting List）
单词词典记录单词到倒排列表的关联关系，一般通过 B+ 树或者哈希链表实现
倒排列表记录单词对应的文档结合，由倒排索引项组成
倒排索引项由文档ID(docId)，词频（term frequencies），单词位置(term postion)，偏移量(character offsets)组成
ElasticSearch 中默认会对文档中的每个字段做倒排索引，可以强行指定不对某些字段设置倒排索引



### 分词器

Analysis 是把全文本转换为一系列单词的过程，也叫分词
Analysis 通过 Analyzer 分词器实现，可以使用 ElasticSearch 内置的分词器或者定制化分词器
在写入文档和搜索查询时都需要用到分词器
在写入文档数据时，需要对 TEXT 字段做分词然后建立倒排索引
在搜索查询时，需要对输入的查询语句进行分词，然后通过倒排索引进行搜索
Analyzer 分词器的组成:



#### IK分词器



在添加文档时会进行分词，索引中存放的就是一个一个的词（term），当你去搜索时就是拿关键字去匹配词，最终找到词关联的文档。

测试当前索引库使用的分词器：

**post 发送：localhost:9200/_analyze**

**{"text":"测试分词器，后边是测试内容：spring cloud实战"}**

结果如下：

![img](https://img2018.cnblogs.com/blog/608476/201905/608476-20190507150245973-1625863315.png)



会发现分词的效果将 “测试” 这个词拆分成两个单字“测”和“试”，这是因为当前索引库使用的分词器对中文就是单字分词。

**IK分词器分词效果**

发送：post localhost:9200/_analyze

{"text":"测试分词器，后边是测试内容：spring cloud实战","analyzer":"ik_max_word" }

结果：

![img](https://img2018.cnblogs.com/blog/608476/201905/608476-20190507150440150-737417465.png)





**两种分词模式**

ik分词器有两种分词模式：ik_max_word和ik_smart模式。

1、ik_max_word

会将文本做最细粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为“中华人民共和国、中华人民、中华、

华人、人民共和国、人民、共和国、大会堂、大会、会堂等词语。

2、ik_smart

会做最粗粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为中华人民共和国、人民大会堂。

**自定义词库**

如果要让分词器支持一些专有词语，可以自定义词库。

iK分词器自带一个main.dic的文件，此文件为词库文件。

可以进行该词库替换与配置增强功能 



### 6.删除索引

- 向ES服务器发送`DELETE`请求：`http://localhost:9200/shopping`



### 删除文档

向ES服务器发送DELETE请求：http://localhost:9200/shopping/_doc/1001



### 条件查询、分页查询、查询排序

向ES服务器发送GET请求：http://localhost:9200/shopping/_search
请求体：（条件查询）

```json
{
    "query": {
        "match": {
            "title": "为"
        }
    }
}
```

全量查询

```json
{
    "query": {
        "match_all": {
        }
    }
}
```

请求体：（分页查询）

```json
{
    "query": {
        "match_all": {}
    },
    "from": 0,
    "size": 1
}
```

或者（指定`name`字段查询）

```json
{
    "query": {
        "match_all": {}
    },
    "from": 0,
    "size": 1,
    "_source": [
        "title"
    ]
}
```

请求体：（对`price`进行排序查询）

```json
{
    "query": {
        "match_all": {}
    },
    "sort": {
        "price.keyword": {
            "order": "desc"
        }
    }
}
```

#### 多条件查询、范围查询

向ES服务器发送`GET`请求：`http://localhost:9200/shopping/_search`
请求体：（多条件查询-`必须满足title、price两个条件成立`）

```json
{
    "query": {
        "bool": {
            "must": [
                {
                    "match": {
                        "title": "诺基亚手机"
                    }
                },
                {
                    "match": {
                        "price": 999.0
                    }
                }
            ]
        }
    }
}
```

请求体：（多条件查询-`满足title或price两个条件的其中一个`）

```json
{
    "query": {
        "bool": {
            "should": [
                {
                    "match": {
                        "title": "诺基亚手机"
                    }
                },
                {
                    "match": {
                        "title": "华为手机"
                    }
                }
            ]
        }
    }
}
```

请求体：（范围查询）

```json
{
    "query": {
        "bool": {
            "should": [
                {
                    "match": {
                        "title": "诺基亚手机"
                    }
                },
                {
                    "match": {
                        "title": "小米手机"
                    }
                }
            ],
            "filter": {
                "range": {
                    "price": {
                        "gt": 5000
                    }
                }
            }
        }
    }
}
```

#### 全文检索、完全匹配、高亮查询

向ES服务器发送`GET`请求：`http://localhost:9200/shopping/_search`
请求体：（全文检索）

```json
{
    "query": {
        "match": {
            "title": "小华"
        }
    }
}
```

这个也是可以查询出数据的，ES会把 `小` `华` 进行拆解进行匹配，如果想完全匹配`小华`这个词，则需要把`match`改为`match_phrase`，也就是：

```json
{
    "query": {
        "match_phrase": {
            "title": "小华"
        }
    }
}
```

如果想对检索的字段进行高亮，则：

```json
{
    "query":{
        "match_phrase":{
            "title":"小米"
        }
    },
    "highlight":{
        "fields":{
            "title":{}
        }
    }
}

```



#### 聚合查询

关键字`aggs`

```json
{
    "aggs": { //聚合操作
        "price_group": { //统计名称，随意起名
            "terms": { //分组操作
                "field": "price.keyword" //分组字段
            }
        }
    }
}
```

#### 或者 分组操作 平均值（这里报错）

```json
{
    "aggs": { //聚合操作
        "price_avg": { //统计名称，随意起名
            "avg": { //分组操作
                "field": "price.keyword" //分组字段
            }
        }
    }
}
```



#### 映射关系

- 创建索引：发送`PUT`请求：`http://localhost:9200/user`
- 创建文档，并规定文档类型（映射关系），类似与MySQL的字段类型，发送`PUT`请求：`http://localhost:9200/user/_doc/_mapping`
  请求体内容

```json
{
    "properties":{
        "name":{
            "type":"text",
            "index":true//能够索引查询
        },
        "sex":{
            "type":"keyword",
            "index":true
        },
        "tel":{
            "type":"text",
            "index":false
        }
    }
}
```

增加数据，发送PUT请求：http://localhost:9200/user/_doc/1001

```json
{
    "name":"lemon",
    "sex":"男",
    "phone":"15550172338"
}
```


查询数据，发送GET请求：http://localhost:9200/user/_search

```json
{
    "query":{
        "match":{
           "name":"l"
        }
    }
}
```

```json
{
    "query":{
        "match":{
           "name":"lemon"
        }
    }
}
```

```json
{
    "query":{
        "match":{
           "phone":"15550172338"
        }
    }
}
```

```json
{
    "query":{
        "match":{
           "sex":"男"
        }
    }
}
```

### ES 整合 Java API

**注意：elasticsearch:7.8.0版本要和pom.xml文件中的依赖对应上**

1. 下载镜像：`docker pull elasticsearch:7.8.0`

2. 运行容器：
`docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -e "discovery.type=single-node" -d -p 9200:9200 -p 9300:9300 --name elasticsearch-7.8.0 elasticsearch:7.8.0`

第一个参数设置运存为256m，ES默认启动占2g，第二个参数设置启动方式为单个节点（不设置启动后会自动停止，日志会输出错误消息，低版本不用设置，至少docker默认拉取的5.6.12版本只需要设置第一个参数即可）

#### Maven 配置

```xml
		<!--引入 ElasticSearch 依赖-->
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>7.8.0</version>
        </dependency>
        <!--引入 ElasticSearch 客户端-->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.8.0</version>
        </dependency>
```

#### java创建索引

```java
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
```

#### java插入数据

```java
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
        request.index(INDEX).id("1001");
        User user = new User();
        user.setName("Lemon");
        user.setAge(20);
        user.setSex("男");
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
```

#### 文档修改

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import java.io.IOException;

public class ESTestDocUpdate {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //修改数据
        UpdateRequest request = new UpdateRequest();
        request.index(INDEX).id("1001");

        request.doc(XContentType.JSON, "sex", "女");
        // /user1_doc/1003/_update
        // /user1/_update/1001

        //响应结果
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭ES客户端
        esClient.close();
    }
}
```

#### 文档查询（根据 ID查询）

```java
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
```

#### 文档删除（根据 ID删除）

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTestDocDeleteById {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //删除数据
        DeleteRequest request = new DeleteRequest();
        request.index(INDEX).id("1001");

        //响应结果
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);

        //关闭ES客户端
        esClient.close();
    }
}
```

#### 文档批量新增

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTestDocInsertByBatch {
    private static final String HOST = "localhost";//请求地址
    private static final Integer PORT = 9200;//请求端口
    private static final String TYPE = "http";//请求类型
    private static final String INDEX = "user1";//请求索引名称

    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, TYPE))
        );

        //批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index(INDEX).id("1002").source(XContentType.JSON, "name", "zhangsan"));
        request.add(new IndexRequest().index(INDEX).id("1003").source(XContentType.JSON, "name", "lisi"));
        request.add(new IndexRequest().index(INDEX).id("1004").source(XContentType.JSON, "name", "wangwu"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());//运行时长
        System.out.println(response.getItems());

        //关闭ES客户端
        esClient.close();
    }
}
```

#### 文档批量删除

```java
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
```

#### 文档全量查询

```java
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

public class ESTestDocSearchAll {
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

        //构建查询条件-查询索引种全部的数据
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
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
```

#### 文档分页查询、条件查询、字段查询

##### 分页查询

```java
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

public class ESTestDocSearchByPage {
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
        //构建查询条件-分页查询
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        query.from(0);//起始页码，从0开始
        query.size(1);//每页条数
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
```

##### 条件查询－排序

```java
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
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class ESTestDocSearchBySort {
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
        //构建查询条件-查询排序
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        query.sort("age", SortOrder.ASC);//升序
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
```

##### 字段查询

```java
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

public class ESTestDocSearchByCondition {
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

        //构建查询条件-条件查询-termQuery(注意大小写)
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "lisi"));
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
```

##### 过滤字段

```java
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
```

#### 文档组合查询、范围查询

##### 组合查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTestDocSearchByZuhe {
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

        //构建查询条件-组合查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //条件组合-must:一定；should:或
        //boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("sex","女"));
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex","男"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 20));

        builder.query(boolQueryBuilder);
        request.source(builder);
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
```

##### 范围查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTestDocSearchByRange {
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

        //构建查询条件-范围查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");//age 范围查询

        //范围查询条件
        rangeQueryBuilder.gte(20);//大于等于20
        rangeQueryBuilder.lte(30);//小于等于30
        //rangeQueryBuilder.lt(30);//小于30

        builder.query(rangeQueryBuilder);
        request.source(builder);
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
```

#### 文档模糊查询、高亮查询

##### 模糊查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTestDocSearchByLike {
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
        //构建查询条件-模糊查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "lisi").fuzziness(Fuzziness.ONE);//name 模糊查询,差别数量：1个字符
        builder.query(fuzzyQueryBuilder);
        request.source(builder);
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
```

##### 高亮查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public class ESTestDocSearchByGaoliang {
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
        //构建查询条件-高亮查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "lisi");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");//对name 高亮显示
        builder.highlighter(highlightBuilder);
        builder.query(termQueryBuilder);
        request.source(builder);
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
```

#### 文档最大值查询、分组查询

##### 最大值查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public class ESTestDocSearchByMax {
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
        //构建查询条件-最大值查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        //AggregationBuilder aggregationBuilder = AggregationBuilders.min("minAge").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);
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
```

##### 分组查询

```java
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public class ESTestDocSearchByGroup {
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
        //构建查询条件-分组查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);
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
```













































