---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---



 



首先查询进程，发现hadoop并没有启动

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmfst4a88j30lo02u3yu.jpg)

如何配置hadoop，参考我的另外一篇博文《[Hadoop环境准备](https://www.cnblogs.com/zhichun/p/11363114.html)》

 

接下来，启动hadoop

start-all.sh

或者

start-dfs.sh

start-yarn.sh

 

启动成功后，在浏览器中输入：

<主机名>:8088

master:8088/cluster

 

 

a. Cluster Metrics

　　集群指标：app（提交、排队、运行、完成)、 Container个数 、 资源（内存、cpu）

b. Cluster Nodes Metrics

　　机器状态：active、dicommisioning、decommisioned、lost、unhealty、reboot 

 

c. dr.who用户指标

　　通过浏览器访问默认使用dr.who用户

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmfswj31lj310g0dowg3.jpg)

 

 

 

 

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190820131955499-1716662413.png)

 

 

 

以上是基本情况说明，借来调用hadoop2.7/share/hadoop/mapreduce下的一个jar包:hadoop-mapreduce-exmaples-2.7.7.jar 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmft1o6p2j31500kyaf2.jpg)

 

执行jar包

hadoop jar hadoop-mapreduce-examples-2.7.7.jar

可以看到终端信息中出现wordcount信息

 

执行wordcount程序：

hadoop jar hadoop-mapreduce-examples-2.7.7.jar wordcount <input> <output>

 

调用wordcount分词程序，

hadoop jar hadoop-mapreduce-examples-2.7.7.jar wordcount file:/home/liuhongyang/Downloads/hadoop2.7/LICENCE.txt file:/home/liuhongyang/Downloads/hadoop2.7/tmp

说明：对LICENCE.txt文件进行分词，tmp文件输入output目录文件，新建前不应该存在

 

在tmp目录下查看结果：

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmft7vtpmj31220putb9.jpg)

 