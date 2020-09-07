---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





## Mapreduce简介





### Concepts


**Mapredcue process**

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmg5zzo8jj315y0iagng.jpg)

**Anatomy of a mapreduce program**

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmg5y7m0sj318m0mmdib.jpg)

**The mapreduce wordcount process:**
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmg5wija7j31yo0tuq8q.jpg)



![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfmg5qf76rj31lo0kw41w.jpg)



### Application


查看**mapreduce**包

```{}
cd /usr/lib/hadoop-2.2.0
ls
cd /share/hadoop/mapreduce/
hadoop jar hadoop-mapreduce-examples-2.2.0.jar
```

将本地文件移到hdfs上

```{bash}
hdfs dfs -put /home/edureka/Desktop/input /
```

查看目录

```{bash}
hdfs dfs -cat /input
```

运行wordcount程序，并将输出存在firstExampleOut目录下

```{bash}
hadoop jar hadoop-mapreduce-examples-2.2.0.jar wordcount /input /firstExampleOut

```


查看结果


```{bash}
hadoop dfs -ls /firstExampleOut
```


```{bash}
hadoop dfs -ls /firstExampleOut/part-r-00000
```