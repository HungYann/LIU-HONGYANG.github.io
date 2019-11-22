## Mapreduce简介





### Concepts


**Mapredcue process**

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g974iwc6t2j315y0iancl.jpg)

**Anatomy of a mapreduce program**

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g974iggmy3j318m0mmtpa.jpg)


**The mapreduce wordcount process:**
![](https://tva1.sinaimg.cn/large/006y8mN6gy1g974htfr9jj31yo0tukjl.jpg)



![](https://tva1.sinaimg.cn/large/006y8mN6gy1g9746cjxu3j31lo0kwqn8.jpg)



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

