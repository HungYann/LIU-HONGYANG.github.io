---
layout: posts
author: LIU,HONGYANG
tags: [ElasticSearch]
---



### ELasticSearch安装



分布式全文搜索引擎



> 下载



```
http://127.0.0.1:9200/
```



1. 修改jvm.options

![image-20200424194302018](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge53esc11jj30pm05qdga.jpg)



2. 使用npm或者cnpm(中国)下载:

```
npm install
```



3.配置elasticsearch.yml解决跨域问题：



![image-20200424202148797](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge54j3gw74j30eg03e75i.jpg)



索引可以当成数据库，文档可以当成一条数据

执行elasticsearch-head：

```
cd elasticsearch-heads
npm run start
```



```
http://localhost:9100
```





> 下载Kibana

启动之后进入

```
http://localhost:5601/app/kibana
```



> kibana汉化：

```
#i18n.locale: "en"
```

将"en"转化成"zh-CN"



### 下载IK分词器



```
https://github.com/medcl/elasticsearch-analysis-ik
```



使用命令行查看:



```
elasticsearch-plugin
```



或者：

![Screenshot 2020-04-24 at 22.25.17](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5848fx2pj30b0018mx5.jpg)



使用kibana进行测试：



```
GET _analyze
{
  "analyzer": "ik_smart",
  "text": "中华人民共和国"
}

GET _analyze
{
  "analyzer": "ik_max_word",
  "text": "中华人民共和国"
}
```



ik_smart最小切分

ik_max_word穷尽切分



![image-20200424222231845](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge580pn5txj31hx0u0jz6.jpg)





### 创建索引



1. 创建索引

```
PUT /索引名/～类型名～/文档id
{请求体}
```



```json
PUT /test1/type1/1
{
  "name":"liu",
  "age":"23"
}
```

![image-20200425005045778](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5cax8u91j313u0ceq5s.jpg)



2.创建这个规则

```json
PUT /test2
{
  "mappings": {
    "properties": {
      "name":{
        "type": "text"
      },
      "age":{
        "type": "long"
      },
      "birthday":{
        "type":"date"
      }
    }
  }
}

GET test2
```

默认类型：

```json
PUT /test3/_doc/1
{
  "name":"liu",
  "age":"13",
  "birth":"1997-7-26"
}

GET test3
```



![image-20200425010510184](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5cpxf215j31sp0u0grt.jpg)



> 查看默认配置

```
GET _cat/indices
```



### 关于索引的操作

> 修改

强力覆盖（缺点是容易遗漏一些内容）：

```
PUT /test3/_doc/1
{
  "name":"liuhongyang",
  "age":"13",
  "birth":"1997-7-26"
}

```

常规操作：

```
POST /test3/_doc/1/_update
{
  "doc":{
    "name":"法外1"
  }
  
}
```



>  删除：

```
DELETE test3
```



简单的查询：

GET 

_doc:type

_search:固定词

q:查询

```
GET /test3/_doc/_search?q=name:狂胜说
```



### 复杂搜索



```json
PUT /test2/_doc/1
{
  "name":"liuhongyang",
  "age":"12",
  "tags":["技术一","技术二"]
}


GET /test2/_search?q=name:liuhongyang
```



hit:

索引和文档的信息



```json
GET /test2/_doc/_search
{
  "query":{
    "match":{
      "name":"liuhongyang"
    }
  },
  "_source":["name","age"]
}
```



**_source** : 过滤信息



> 分页查询



```json
GET /test2/_doc/_search
{
  "query":{
    "match":{
      "name":"liuhongyang"
    }
  },
  "sort":{
     "age":{
      "order":"desc"
    }  
  },
  "from":0,
  "size":1

}
```



/search/{current}/{pagesize}

must

should

must_not

```json
GET /test2/_doc/_search
{
  "query":{
    "must":[
      {
        "match":{
          "name":"liuhongyang"
        }
      },
      {
        "match":{
          "age":"3"
        }
      } 
    ]
  }

}
```

```
GET /test2/_search
{
  "query":{
    "should":[
      {
        "match":{
          "name":"liuhongyang"
        }
      },
      {
        "match":{
          "age":"3"
        }
      } 
    ]
  }

}
```



```
"filter":{
	"range":{
		"age":{
			"gte":10,
			"lte":25
		}
	}
}
```

 

> 条件查询



```
"query":{
	"match":{
		"tags":"男"
	}
}
```



- term，直接查询精确的
- match，会使用分词器解析

> 分词



```json
GET _analyze{
	"analyzer":"standard",
	"text":"java name"
}
```

keyword不会被分词起器解析，只能被精确查询



>  精确查询多个值：



![image-20200425140322223](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5z7oevc4j31fc0omwtr.jpg)



```
GET testdb/_search
{
	"query":{
			"bool":{
				"should":[
					"term":{
						"t1":"22"
					},
					"term":{
						"t1":"33"
					}
				]
			}
	}

}
```



> 高亮

![image-20200425141434743](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge5zjaibsjj318w0gu12i.jpg)



匹配

按照条件匹配

精确匹配

区间范围匹配

匹配字段过滤

匹配字段过滤

多条件查询

高亮查询

倒排索引



### 集成SpringBoot



参考链接：

https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html