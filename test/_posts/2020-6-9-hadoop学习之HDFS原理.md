---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





HDFS原理

 

HDFS包括三个组件：

NameNode、DataNode、SecondaryNameNode

 

- NameNode的作用是存储元数据（文件名、创建时间、大小、权限、与block块映射关系等）
- DataNode的作用是存储真实数据信息
- SecondaryNameNode作用合并edits和fsimage文件

 

 

hadoop.tmp.dir -> /tmp/hadoop-root

dfs.namenode.dfs.dir ->/dfs/name/current

 

see_txid :操作事务id, 编号，用于显示操作次数，每次操作自增1

VERSION：显示namespaceID clusterID 

 

修改namenode的存储位置：

<configuration>

　　<property>

　　　　<name>dfs.replication</name>

　　　　<value>1</value>

　　<property>

　　<property>

　　　　<name>dfs.namenode.name.dir<name>

　　　　<value>/opt/module/hadoop-2.6.0/data</value>

　　</property>

</configuration>

 

 

dfs.datanode.data.dir -> /tmp/hadoop-root/dfs/data

 

修改datanode的存储目录

<configuration>

　　<property>

　　　　<name>dfs.replication</name>

　　　　<value>1</value>

　　<property>

　　<property>

　　　　<name>dfs.datanode.data.dir<name>

　　　　<value>/opt/module/hadoop-2.6.0/data</value>

　　</property>

</configuration>

 

 

修改secondarynamenode的目录

file://${hadoop.tmp.dir}/dfs/namesecondary

 

重新格式化

hdfs namenode -format -force

 

多次格式化namenode的问题解释

hdfs格式化会改变VERSION文件中的clusterID,首次格式化时datannode和namenode会产生相同的clusterID;

如果重新执行格式化，namenode的 clusterID会改变，就会与datanode的clusterID不一致，如果重新启动或读写hdfs就会挂掉

 

需要手动修改

 

DataNode

 

　　数据存储：在hdfs-site.xml的dfs.datanode.data.dir属性配置中

　　存储内容：数据本身和数据长度，校验和以及时间戳

 

　　文件块(Block):基本的存储单元，默认大小是128M,通过dfs.blocksize属性配置

 

　　副本策略：默认是3哥，通过dfs.replication属性配置

　　第一个block副本放在与client所在的机器里，（**如果client不在集群的范围内**，则这第一个node是随机选取的，当然系统会尝试不选择哪些太忙或太满的node）

　　第二个block副本放置在与第一个节点不同机架的node中（随机选择）

　　第三个副本和第二个在同一机架，随机放在不同的node中

　　如果还有更多副本就随机放在集群的node里

 

DataNode与Namenode通信

<1>DataNode启动后向NameNode注册，注册后会周期性（1小时）向NameNode上报块信息(BlockReport)

BlockReport: Block与Datanode的映射关系(第二映射关系)

说明：blockreport作用，DataNode通过上传块报告能更新NameNode内存中的映射关系（哪个地方坏了，不能使用了）

<2>心跳机制:3s 发送心跳给Namenode,心跳带有NameNode下发给DataNode的命令

   超过10分钟，datanode未给namendoe发送心跳，这认为该节点不可用

 

SecondaryNameNode(SNN)执行流程

 

<1>周期性向NameNode发送请求edits和fsimage文件

<2>请求完成后，NameNode将edits文件转成edits.new文件

<3>NameNode将fsimage文件加载到内存，合并edits和fsimage文件，命名为fsimage.ckpt

<4>SecondaryNmaeNode将文件发送给NameNode

<5>NameNode用新的fsimage.ckpt替换旧的fsimage,重命名edits.new为edits文件