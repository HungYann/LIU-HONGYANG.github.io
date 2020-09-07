---
layout: post
author: LIU,HONGYANG
tags: [ElasticSearch]
---



1.

```java
java.lang.NoClassDefFoundError: org/elasticsearch/action/admin/cluster/repositories/cleanup/CleanupRepositoryRequest
```



```xml
<!--        导入elasticsearch-->
<dependency>
  <groupId>org.elasticsearch</groupId>
  <artifactId>elasticsearch</artifactId>
  <version>7.6.2</version>
</dependency>
```



2.

````
java.lang.NoSuchMethodError: org.elasticsearch.client.Request.addParameters(Ljava/util/Map;)V
````



```xml
 <properties>
        <java.version>1.8</java.version>
        <elasticsearch.version>7.6.2</elasticsearch.version>
  </properties>
```



3.



```
org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: type is missing;2: type is missing;3:
```



```xml
<dependency>
  <groupId>org.elasticsearch.client</groupId>
  <artifactId>elasticsearch-rest-high-level-client</artifactId>
  <version>7.6.2</version>
</dependency>

<dependency>
  <groupId>org.elasticsearch</groupId>
  <artifactId>elasticsearch</artifactId>
  <version>7.6.2</version>
</dependency>
```



![image-20200501010025186](https://tva1.sinaimg.cn/large/007S8ZIlgy1gecaazx5wmj30xy0mmmze.jpg)