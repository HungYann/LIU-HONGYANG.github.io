---
layout: post
author: LIU,HONGYANG
tags: [ElasticSearch]
---



### Getting Started







> Maven Repository





```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.6.2</version>
</dependency>
```



> Dependencies



以来如下两个**artifacts**

- org.elasticsearch.client:elasticsearch-rest-client
- org.elasticsearch:elasticsearch



> Initialization



```
RestHighLevelClient client = new RestHighLevelClient()
```



### Document APIs



>  Index API



1.创建客户端

2.创建Json内容

3.创建IndexRequest

4.创建IndexResponse

5.关闭客户端



```java
 public static void main( String[] args ) throws IOException {
        //Initialization
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9201,"http")
                )
        );


        IndexRequest request = new IndexRequest("post");
        request.id("1");

        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        request.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        restHighLevelClient.close();
    }
```



```java
public static void main( String[] args ) throws IOException {
        //Initialization
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9201,"http")
                )
        );
        
        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy1");
        jsonMap.put("postDate", new Date());
        IndexRequest indexRequest = new IndexRequest("posts").id("1").source(jsonMap);


        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        restHighLevelClient.close();
    }
```



> Get Requests



```java
GetRequest getRequest = new GetRequest("posts","1");
```



> Exists API



判断documents是否存在



```java
GetRequest getRequest = new GetRequest("posts", "1");
getRequest.fetchSourceContext(new FetchSourceContext(false));
getRequest.storedFields("_none_");
boolean exists =  restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
System.out.println(exists);
```



> Delete API



```java
 DeleteRequest request = new DeleteRequest(
                "posts",
                "1");
        
 DeleteResponse delete = restHighLevelClient.delete(
      request, RequestOptions.DEFAULT);

```



> Update API



```java
RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
  RestClient.builder(
    new HttpHost("localhost",9200,"http"),
    new HttpHost("localhost",9201,"http")
  )
);

Map<String, Object> jsonMap = new HashMap<>();
jsonMap.put("updated", new Date());
jsonMap.put("reason", "daily update");
UpdateRequest updaterequest = new UpdateRequest("posts", "1")
.doc(jsonMap);

restHighLevelClient.close();

```



> Bulk API



```json
public static void main( String[] args ) throws IOException {
        //Initialization
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9201,"http")
                )
        );
        
        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest("pos").id("2")
                .source(XContentType.JSON,"field", "foo"));

        request.add(new IndexRequest("pos").id("3")
                .source(XContentType.JSON,"field", "bar2"));


        BulkResponse bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        restHighLevelClient.close();
    }
```

