---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---



### 步骤：

![未命名导图](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2lij5h5gj31t00u0mzl.jpg)



本文主要用于阐述如何使用Python和jupyter notebook连接数据仓库。



首先在连接之前，首先阐明服务器架构：



主机使用Mac OS，client机使用virtual box。配置Python连接Hive的数仓，有两步。



**第一步是配置client连接程序，client程序可以是Python，也可以是 R，还可以是Java.**



第二步是必须在虚拟机端配置**hiveserver2服务器**，只有配置成功hiveserver2服务器，让**client端** 成功访问hive数据仓库。



hive数据仓库的访问模式有两种，一种是属于CLI模式，就是我们平时在hadoop上直接输入hive，提示的命令行就是CLI模式。

第二种就是**hiveserver2服务器**模式，这种模式可以提供远程访问。



### 预配置：



> 修改Bridged Adapter



为了让主客机可以连接，请将virual box的网络模式修改为**Bridged Adapter**



![Screenshot 2020-05-16 at 20.40.08](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2i36jdkmj31040ig0vm.jpg)



其次，在虚拟机上检查本机IP





> IP



记录本机ipv4地址



![Screenshot 2020-05-15 at 23.18.02](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2i4rb2dgj309u05qq3d.jpg)





### 配置Python：



![Screenshot 2020-05-16 at 10.44.25](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2ky7qj0qj30vc03smxw.jpg)



客户端的代码如下：



![Screenshot 2020-05-16 at 15.47.30](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2iefdz7cj31jq0fi0xd.jpg)



```python
from pyhive import hive
conn = hive.connect(host='10.242.135.104', port=10000, username='=root', auth='NONE')
cursor = conn.cursor()
cursor.execute("select * from oilCrawlData limit 10")
for result in cursor.fetchall():
    print(result)
```







使用的包是pyhive



首先要下载pyhive以及依赖包。



```python
sudo apt-get install gcc
pip install thrift+
pip install sasl
pip install thrift_sasl
pip install pyhive
```

![Screenshot 2020-05-16 at 10.44.05](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2kxhvbsyj30vi0a4gnt.jpg)



![Screenshot 2020-05-16 at 10.44.14](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2kxovksvj30v605m0tm.jpg)



> Install gcc

```python
sudo apt-get install gcc
```

> Install Thrift

```python
pip install thrift+
```

>  install sasl

```python
pip install sasl
```

> Install thrift sasl

```python
pip install thrift_sasl
```

> Install PyHive

```
pip install pyhive
```



##### 下载过程中遇到的问题：



![Screenshot 2020-05-15 at 23.45.19](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2ifo893lj30ls09e76l.jpg)







> 下载thrift-sasl时，遇到错误：



```python
pip3 install thrift-sasl
```



![Screenshot 2020-05-16 at 10.28.14](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2ig382k4j30vm0ea41p.jpg)





> 错误解决方法：



```python
pip install git+https://github.com/JoshRosen/python-sasl.git@fix-build-with-newer-xcode 
pip3 install thrift-sasl
```



此时如果不再有包错误，则Client端配置完毕



### 使用Beeline：



使用Beeline之前，请参考文章：[6.Beeline versus Hive CLI](https://docs.cloudera.com/HDPDocuments/HDP2/HDP-2.6.5/bk_data-access/content/beeline-vs-hive-cli.html)

或者[7.Setting Up HiveServer2](https://cwiki.apache.org/confluence/display/Hive/Setting+Up+HiveServer2)



Hive的用户名和密码：



![Screenshot 2020-05-16 at 11.26.11](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2kzil96lj30je08igmq.jpg)



配置core-site.xml



```xml
<property>     
    <name>hadoop.proxyuser.root.hosts</name>     
    <value>*</value>
 </property> 
<property>     
    <name>hadoop.proxyuser.root.groups</name>    
    <value>*</value> 
</property>
```



配置hive-site.xml



```xml
<property>
  <name>beeline.hs2.connection.user</name>
  <value>hive</value>
</property>
<property>
  <name>beeline.hs2.connection.password</name>
  <value>hive</value>
</property>
```



> 防火墙关闭

```shell
sudo netstat -anp|grep 100000
```

确保防火墙关闭，端口开启：

![Screenshot 2020-05-16 at 15.29.33](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2l832gnnj30ra038q42.jpg)





>  查看连接HiveServer2



![Screenshot 2020-05-16 at 14.08.11](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2ljaoocbj31540pw0wo.jpg)





#####  问题：



用户权限问题：

![Screenshot 2020-05-16 at 15.30.42](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2l9hz2dkj30uc086acq.jpg)



检查此处配置要正确：

```xml
<property>     
    <name>hadoop.proxyuser.root.hosts</name>     
    <value>*</value>
 </property> 
<property>     
    <name>hadoop.proxyuser.root.groups</name>    
    <value>*</value> 
</property>
```





若遇到一下问题，请确保10000端口打开，



![Screenshot 2020-05-16 at 14.52.13](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2lkjadnbj30v403gq48.jpg)





![Screenshot 2020-05-16 at 15.19.14](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2l7x33zkj30ue05y40o.jpg)







>  成功连接:



![Screenshot 2020-05-16 at 15.44.20](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2lawubpxj30v00dijwk.jpg)









### 参考文章：



1.大数据分析系列（一）：使用R连接Hive:

https://www.jianshu.com/p/651fb104088c



2.Mac 安装 thrift-sasl:

https://www.jianshu.com/p/7fa6aad1d2b2



3.Ubuntu防火墙的开启，关闭，端口的打开，查看:

https://blog.csdn.net/jiaochiwuzui/article/details/80907521



4.Hive 教程(八)-hiveserver2:

https://www.cnblogs.com/yanshw/p/11792799.html



5.Step by Step Guide Connecting HiveServer2 using Python Pyhive：

https://dwgeek.com/guide-connecting-hiveserver2-using-python-pyhive.html/



___



6.Beeline versus Hive CLI：

https://docs.cloudera.com/HDPDocuments/HDP2/HDP-2.6.5/bk_data-access/content/beeline-vs-hive-cli.html



7.Setting Up HiveServer2：

https://cwiki.apache.org/confluence/display/Hive/Setting+Up+HiveServer2#SettingUpHiveServer2-Configuration



8.Hive 教程(八)-hiveserver2:

 https://www.cnblogs.com/yanshw/p/11792799.html



9.HIVE2 ：beeline连接设置用户名和密码注意问题：

https://blog.csdn.net/swing2008/article/details/53743960



10.Beeline连接报错：Could not open client transport with JDBC Uri: jdbc:hive2://localhost:10000/default：

https://blog.csdn.net/u011495642/article/details/84305944



11.hive is not allowed to impersonate hive:

https://stackoverflow.com/questions/40603714/hive-is-not-allowed-to-impersonate-hive





12.How to access hive from python: 

https://community.cloudera.com/t5/Support-Questions/How-to-access-hive-from-python/td-p/184885



13.HiveServer2、Beeline、JDBC使用:

https://www.jianshu.com/p/4e5ed1d6f284



14.How to connect R to Hiveserver2 using hive jdbc:

https://mapr.com/support/s/article/How-to-connect-R-to-Hiveserver2-using-hive-jdbc?language=en_US

