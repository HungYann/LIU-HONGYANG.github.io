---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





查看进程

jps

访问hdfs:

hadoop-root:50070

 

hdfs bash命令：

　　hdfs dfs

　　<1>　　 -help: 显示命令的帮助的信息

　　<2>　　 -mkdir:创建一个新目录 -p

　　　　　　-mkdir -p /test1/test2：创建目录，p可以创建不存在的父路径

　　<3>　　-ls:显示当前目录下的所有文件(-ls -R) -R：递归地查看

  <4> 　 -put LICENSE.txt /test 将本地文件上传到HDFS上

　　　　   -copyFromLocal  将本地文件复制到HDFS上

　　　　   -moveFromLocal 

　　<7>   -du(s):显示文件大小 hdfs -du -s /

　　<8>   -count显示当前目录下文件大小 

　　<9>　　-mv/-cp 移动复制目录或文件

　　<10>   -rm -r:删除目录或文件

　　<11>   -get: 将服务器上的文件下载到本地hdfs dfs -get /test/NOTICE.txt /opt/datas

　　<12>   -cat/-text:查看服务器上文本格式的文件

　　　　　　copyToLocal[-ignoreCrc]:复制文件到本地

　　　　　　copyToLocal:移动文件到本地

 

 HDFS命令行：

　　hdfs dfsadmin 

　　　　　　-report:查看文件系统的基本信息和统计信息

　　　　　　-safemode enter | leave | get | wait:安全模式命令

　　　　　　-refreshNodes:重新读取hosts和exclude文件，在新增节点或注销节点时使用

　　　　　　-finalizeUpgrade:终结HDFS的升级操作

　　　　　　-setQuota <quota> <dirname> :为每个目录<dirname> 设定配额 <quota>

　　　　　  -clrQuota <dirname>:为每个目录<dirname>清楚配额设定

　　　　　　

　　　　　　　　　　　　