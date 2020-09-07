---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





##### 剖析MapReduce作业运行机制

MapReduce总体上分为5步，其中包括

 **input**、 **split**、 **map**、 **shuffle**、 **reduce**、 **output*8



执行的流程如下：



![image-20200412170309294](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdr3cqhjl5j30t60dgagf.jpg)



具备如下独立实体：

客户端，提交MapReduce作业

YARN **Resource Manager**，负责协调集群上计算机资源分配

YARN **Node  Manager** 节点管理器，负责启动和坚实集群中机器上的计算容器（container）

MapReduce的**application master**, 负责协调运行MapReduce作业的任务，它和MapReduce任务在容器中运行，这些容器由资源管理器分配并由节点管理器进行管理。

分布式文件系统(HDFS)



##### MapReduce的WordCount



mapreduce具有两种运行环境：

- 第一种是单机运行环境，

<1>在windows环境中，需要导入winutils.exe和hadoop.dll文件

<2>配置Hadoop_Home环境变量（重启）

或临时环境变量：

System.setProperty("hadoop.home.dir","xxx")

<3>修改`NativeIO`类，

将access0调用处直接换成true

```java
static{
	System.setProperty("hadoop.home.dir","E:\\hadoop-2.6.0-cdh5.0.0");
}
```





- 第二种是连Linux服务器运行

mac系统的代码连接linux系统上的hadoop环境进行运行，运行结果存在本地或hdfs

查看**core-site.xml**文件

```
conf.set("fs.defaultFS","hdfs://localhost:8020");
```





- 第三种打Jar包放到hadoop集群中运行

两种方式打Jar包

第一种是直接通过Idea进行打包（比较繁琐），

第二种通过maven打Jar包，在Configuration配置完成后可以看到Jar包的位置

![image-20200413000634008](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdrfl92wioj30vw09stax.jpg)





![image-20200413000852777](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdrfnnql91j31oi04g0yj.jpg)



放在linux上运行，即可



重新配置WordCount, 使用Edit Configurations



![image-20200412224939974](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdrdd88pi0j30e806gt9h.jpg)



编辑Program arguments, 添加两个输入，输出路径：

![Screenshot 2020-04-12 at 22.36.46](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdrdcpnroyj314y0fu0v7.jpg)



