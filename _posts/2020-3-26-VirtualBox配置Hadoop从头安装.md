---
layout: post
author: leo
tags: [Hadoop]
---





本人机器是mac，因此主要研究在Mac环境下的VirtualBox安装Ubuntu，并且配置Hadoop的过程(花了两天时间从0开始配置了一个单点数据仓库，主要包括虚拟机安装，配置，以及hadoop，hdfs, yarn命令配置，mysql踩坑，Hive和sqoop配置。最后是数据导入与使用，写了30来页笔记，供初学者使用学习)。



### 首先是下载VirtualBox虚拟机

1.下载VirtualBox虚拟机

[下载地址](https://www.virtualbox.org/wiki/Downloads)

虚拟机下载完成之后，开始安装 Ubuntu的过程，由于比较简单，故不在赘述。

安装成功后，效果如下图



![Screenshot 2020-03-19 at 21.37.14](https://tva1.sinaimg.cn/large/00831rSTgy1gczop0xwuzj318s0ruwnw.jpg)



2.更改虚拟机的显示模式

下载如下两个包：

```shell
sudo apt install virtualbox-guest-utils virtualbox-guest-dkms
```



安装并运行`Insert Guest Additions CD image`

![image-20200321144439431](https://tva1.sinaimg.cn/large/00831rSTgy1gd1jpswclaj310g0hak2f.jpg)



![image-20200321144542488](https://tva1.sinaimg.cn/large/00831rSTgy1gd1jqx3u8uj30x00b8dju.jpg)



3. 调整屏幕分辨率

View->Scaled Mode(HOST+C)

在View视图这个选项卡中，选择Host+C。缩放模式，即可开始自由浏览Ubuntu。





4. 更改设置在主机与虚拟机之间传递文件



打开**Devices > Shared folders settings…**

选择Machine Folders并添加Add button



设置选项勾选

- Auto-mount

- Make Permanent



![image-20200320001053066](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdwndzd4r1j30gq0960tj.jpg)



最后，打开terminal终端输入一下命令

```shell
sudo adduser username vboxsf
```



username根据当前在ubuntu上的account。



重启即可生效



查看sf_ username文件夹即可看到主机上的文件



这样即可在Ubuntu客户端机和Mac主机之间传递文件。



我的机器是如下文件,ubuntuFile是主机上的文件夹名字



![image-20200320001735812](https://tva1.sinaimg.cn/large/00831rSTgy1gczp1bu586j304e03iglp.jpg)





### 配置SSH免密登录



下载

```
sudo apt-get install openssh-server
```







### 配置JAVA_HOME篇



下载java

```shell
sudo apt update
sudo apt install openjdk-8-jdk
```





删除jdk

![image-20200320165616512](https://tva1.sinaimg.cn/large/00831rSTgy1gd0hwgty4jj31680g00vj.jpg)









### 安装Hadoop篇





- 打开Terminal

```
Ctrl+ Alt +T
```



- 检查Java版本

```
sudo apt-get update 
sudo apt-get install default-jdk
java -version
```

最好使用java1.8

![image-20200321171624533](https://tva1.sinaimg.cn/large/00831rSTgy1gd1o3qtodcj30kc022t98.jpg)

- 下载Hadoop

```
wget https://www-eu.apache.org/dist/hadoop/common/hadoop-2.7.7/hadoop-2.7.7.tar.gz
```



- 解压Hadoop文件

```
tar -xzvf hadoop-2.7.7.tar.gz
```



- 新建一个自己名字下的路径

注意`cd ~`是指当前用户下的home路径

```
sudo mkdir /home/liuhongyang
```



- 将Hadoop移到自己名下目录中并将hadoop-2.7.7重新命名为hadoop

```
sudo mv hadoop-2.7.7 /home/{yourname}/hadoop/
cd /home/{yourname}/hadoop/
mv hadoop-2.7.7 hadoop
```

![Screenshot 2020-03-20 at 00.54.03](https://tva1.sinaimg.cn/large/00831rSTgy1gd0ag1j2xxj30d8014mxa.jpg)

- 获得 Java的bin目录

  ​	

```
readlink -f /usr/bin/java | sed "s:bin/java::"
```



(`centos`配置添加补充：)

`vim ~/.bashrc`

```
export JAVA_HOME=/etc/alternatives/java_sdk_1.8.0
export PATH=$JAVA_HOME/bin:$PATH
```







注意，到bin即可，配置JAVA_HOME, 不要将其它的也包括进去！

```
/usr/lib/jvm/java-11-openjdk-amd64/bin
```



- 配置hadoop-env.sh文件，更新java_home路径



需要注意的是/home/liuhongyang是本人自己新建的一个用户，用户明叫"liuhongyang", /home下还有其它用户



```shell
sudo gedit /home/liuhongyang/hadoop/etc/hadoop/hadoop-env.sh
```



在hadoop-en.sh文件中添加如下命令:

```
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export JAVA_HOME=${JAVA_HOME}
```

或者

````shell
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
````

总之，不要添加bin目录下的java生效，但是shell脚本的目录只需指定到bin即可



![Screenshot 2020-03-20 at 13.43.37](https://tva1.sinaimg.cn/large/00831rSTgy1gd0cc93hnsj30i803awex.jpg)





- 编辑.bashrc文件

在终端输入如下命令：

```shell
sudo gedit .bashrc
```



打开.bashrc文件后，输入如下命令：

```shell
export PATH=$PATH:/home/liuhongyang/hadoop/bin
export PATH=$PATH:/home/liuhongyang/hadoop/sbin
# source .bashrc 不要添加否则会导致循环
```



![Screenshot 2020-03-20 at 12.29.24](https://tva1.sinaimg.cn/large/00831rSTgy1gd0ageu21tj30re0kg0uy.jpg)

保存重启terminal即可



再直接输入hadoop

```shell
hadoop
```

![image-20200320124135848](https://tva1.sinaimg.cn/large/00831rSTgy1gd0ajg75bzj30ls0eoq9a.jpg)



则hadoop配置成功

接下来我们使用它



就利用hadoop下的mapreduce包来进行一个简单的sample

```
mkdir ~/input
cp /home/liuhongyang/hadoop/etc/hadoop/*.xml ~/input
```



```shell
hadoop jar /home/liuhongyang/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.7.jar wordcount /home/liuhongyang/input/ /home/liuhongyang/output/
```

or

```shell
hadoop jar /home/liuhongyang/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.7.jar grep /home/liuhongyang/input /liuhongyang/grep_example 'principal[.]*'
```



此时运行成功

![Screenshot 2020-03-20 at 13.43.27](https://tva1.sinaimg.cn/large/00831rSTgy1gd0cchlnitj30li0ckdjc.jpg)



对~/input里的文件进行分词，成功

![Screenshot 2020-03-21 at 17.40.54](https://tva1.sinaimg.cn/large/00831rSTgy1gd1otstkitj30ki02mwf6.jpg)



### 安装Hadoop的HDFS和YARN篇



HDFS配置文件

```xml
<configuration>

	<property>	
		<name>dfs.namenode.name.dir</name>
		<value>/home/liuhongyang/data/nameNode</value>
	</property>

	<property>
		<name>dfs.datanode.data.dir</name>
		<value>/home/liuhongyang/data/dataNode</value>
	</property>

	<property>
		<name>dfs.replication</name>
		<value>1</value>
	</property>
  
  <property>
  	<name>dfs.permissions</name>
    <value>false</value>
  </property>

</configuration>

```



core.xml配置文件

```xml
<configuration>

	<property>
		<name>fs.default.name</name>
		<value>hdfs://localhost:9000</value>
	</property>
	
</configuration>
```





Yarn配置



map-site.xml配置文件

```xml
<configuration>
	<property>
		<name>mapreduce.framework.name</name>
		<value>yarn</value>
	</property>
	
	<property>
		<name>yarn.app.mapreduce.am.resource.mb</name>
		<value>512</value>
	</property>

	<property>
		<name>mapreduce.map.memory.mb</name>
		<value>256</value>
	</property>
	
	<property>
		<name>mapreduce.reduce.memory.mb</name>
		<value>256</value>
	</property>
	
</configuration>
```





yarn-site.xml配置



```xml
<configuration>

<!-- Site specific YARN configuration properties -->
	<property>
		<name>yarn.acl.enable</name>
		<value>0</value>
	</property>
	
	<property>
		<name>yarn.resourcemanager.hostname</name>
		<value>localhost</value>
	</property>

	<property>
		<name>yarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
	</property>

	<property>
		<name>yarn.nodemanager.resource.memory-mb</name>
		<value>1536</value>
	</property>

	<property>
		<name>yarn.scheduler.maximum-allocation-mb</name>
		<value>1536</value>
	</property>

	<property>
		<name>yarn.scheduler.minimum-allocation-mb</name>
		<value>128</value>
	</property>

	<property>
		<name>yarn.nodemanager.vmem-check-enabled</name>
		<value>false</value>
	</property>


</configuration>
```





配置完成后

```
hadoop hdfs -format
start-all.sh启动进程
```



```
使用jps查看进程
```

![Screenshot 2020-03-20 at 22.02.26](https://tva1.sinaimg.cn/large/00831rSTgy1gd1luc8vvsj30h603cq3a.jpg)



使用jsps查看进程

![Screenshot 2020-03-20 at 22.00.43](https://tva1.sinaimg.cn/large/00831rSTgy1gd1l01xic1j31300dsq56.jpg)















### 配置在Ubuntu上配置Mysql

1. 打开终端

查看 /var/run/mysqld目录，确保该目录存在，注意下文件被删会导致如下错误

```
Can't connect to local MySQL server through socket '/var/lib/mysql/mysql.sock'
```



2.升级apt-get工具



```shell
sudo apt-get update
sudo apt update
```



3.下载mysql-server

```
sudo apt-get install mysql-server
```



 4.下载mysql-client

```
sudo apt-get install mysql-client
```



5.下载 `libmysqlclient-dev`

```
sudo apt-get install libmysqlclient-dev
```



6.修改MySQL配置

```
sudo gedit /etc/mysql/mysql.conf.d/mysqld.cnf
```

进入配置文件

注销：`bind-address:127.0.0.1`



![Screenshot 2020-03-20 at 23.27.11](https://tva1.sinaimg.cn/large/00831rSTgy1gd1l18al5qj309m00ujrb.jpg)





添加：`skip-grant-tables`:目的是跳过密码直接登录

![image-20200321122643708](https://tva1.sinaimg.cn/large/00831rSTgy1gd1fqc3hzij30kc05o0td.jpg)

保存退出



7.重启mysql

```shell
sudo service mysql stop
sudo service mysql start
```



8.进入mysql



出现`mysql>`提示符表示，已经成功过使用mysql客户端



```shell
mysql
```

![image-20200321123107462](https://tva1.sinaimg.cn/large/00831rSTgy1gd1fuv00nej30kc07275d.jpg)



9.接下来修改mysql的默认密码

```mysql
use mysql;
show tables;
select user,plugin from user;
update user set authentication_string=password('xxxx'),plugin='mysql_native_password' where user='root';
flush privileges;
exit;
```



其中xxx部分用自己的密码替代

![image-20200321124457243](https://tva1.sinaimg.cn/large/00831rSTgy1gd1g98s1arj30ci070mxp.jpg)



10.重新修改mysql.cnf文件

```shell
sudo gedit /etc/mysql/mysql.conf.d/mysqld.cnf
```

注销`skip-grant-tables;`文件

![image-20200321123723043](https://tva1.sinaimg.cn/large/00831rSTgy1gd1g1dgtuej30hi02a74g.jpg)



11. 正常进入msyql

```shell
mysql -u root -p
```



![Screenshot 2020-03-21 at 12.38.16](https://tva1.sinaimg.cn/large/00831rSTgy1gd1l29z6icj30kk07kdh5.jpg)



12.使用host主机连接虚拟机上的mysql



```
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '你的密码' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```



13.进行授权和刷新

```shell
sudo service mysql restart;
```



14.Mysql删除



```shell
sudo systemctl stop mysql.service && sudo systemctl disable mysql.service
sudo apt purge mysql*
sudo apt autoremove
```





### 安装HIVE篇



- 安装Hive前确保Hadoop的进程全部启动



![image-20200321191652640](https://tva1.sinaimg.cn/large/00831rSTgy1gd1rl1f6nij30dk03u3yy.jpg)



- 下载Hive包：

```
wget http://archive.apache.org/dist/hive/hive-2.1.0/apache-hive-2.1.0-bin.tar.gz
```



- 将`mysql-connector-java-5.0.8-bin.jar`文件移动到/hive/bin目录下



- 编辑~/.bashrc文件

```shell
#Java_home

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
export PATH=$PATH:$JAVA_HOME/bin


#Hadoop
export HADOOP_HOME=/home/liuhongyang/hadoop
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin

export HIVE_HOME=/home/liuhongyang/hive
export PATH=$PATH:$HIVE_HOME/bin
```

![Screenshot 2020-03-21 at 19.44.41](https://tva1.sinaimg.cn/large/00831rSTgy1gd1u6y16kuj30kg038dgn.jpg)



确保输入Hive之后：

出现如图所示的画面：

![Screenshot 2020-03-21 at 19.54.24](https://tva1.sinaimg.cn/large/00831rSTgy1gd1u7h9iorj312r0u0dy1.jpg)





- You also need to put the jar file(mysql-connector-java-5.1.28.jar) in the lib directory of Hive



- 配置`conf/hive-site.xml`

在conf目录下创建`hive-site.xml`文件，可以参考hive-default.xml.template文件编写

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>

  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive</value>
  </property>


  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
  </property>

  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>root</value>
  </property>

  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>yourpassword</value>
  </property>

  <property>
    <name>datanucleus.autoCreateSchema</name>
    <value>true</value>
  </property>

  <property>
    <name>datanucleus.fixedDatastore</name>
    <value>true</value>
  </property>

  <property>
   <name>datanucleus.autoCreateTables</name>
   <value>True</value>
   </property>

</configuration>
```



注意，在使用hive前应先在mysql数据库中创建hive数据库，在hive-site.xml配置中指名要连接的数据库



```
 <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive</value>
  </property>
```





星型模型

![Screenshot 2020-03-04 at 14.15.29](https://tva1.sinaimg.cn/large/00831rSTgy1gd1ubl7ycij30ti0legnj.jpg)



雪花模型在星型模型的基础上发展起来



Hive是建立在Hadoop HDFS上的数据仓库基础架构

Hive可以用来数据的提取、转化、加载(ETL)

Hive定义了简单的类似SQL查询语言，HQL它允许熟悉SQL的用户查询数据

HIVE是SQL解析引擎，他将SQL语句转化成M/R Job然后在Hadoop上执行

HIVE表实际上就是HDFS的目录文件。



![image-20200304143633889](https://tva1.sinaimg.cn/large/00831rSTgy1gchvy5ibrzj312e0lw79d.jpg)









>  HQL的执行过程



解释器、编译器、优化器完成HQL查询语句从词法分析、语法分析、优化以及查询计划生成。生成的查询计算存储在HDFS中，并在随后有MapReduce调用。



![image-20200304145029914](https://tva1.sinaimg.cn/large/00831rSTgy1gchwcn1a8pj31920e2gp8.jpg)







>  Hive的体系结构

![image-20200304145426804](https://tva1.sinaimg.cn/large/00831rSTgy1gchwgr5dvwj319i0n2gwr.jpg)







> 本地模式

- 元数据信息被存储在 MySQL数据库中
- MySQL数据库与HIVE运行在同一台物理机器上
- 多用于开发和测试





> 远程模式

- 元数据信息被存储在 MySQL数据库中
- MySQL数据库与HIVE运行在不同台物理机器上
- 多用于实际生产环境

![image-20200304162528975](https://tva1.sinaimg.cn/large/00831rSTgy1gchz3h9y15j30z00o4tfj.jpg)



**常见Hive语句**



CLI方式：

```shell
## 清除
!clear

## 查看数据仓库中的表
show tables;

## 查看数据仓库中内置函数
show functions;

## 查看表结构
desc 表名

## 查看HDFS上的文件
dfs -ls 目录

## 执行操作系统的命令
!命令
```



例如：



`desc test1`

![Screenshot 2020-03-21 at 20.39.44](https://tva1.sinaimg.cn/large/00831rSTgy1gd1uaclzhyj30bo03cq35.jpg)

`dfs -ls 目录`



![image-20200321205631562](https://tva1.sinaimg.cn/large/00831rSTgy1gd1ugpyxxoj30ka06qab2.jpg)









### 安装Sqoop篇



下载sqoop文件包



```
wget https://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
```



![image-20200322133138368](https://tva1.sinaimg.cn/large/00831rSTgy1gd2n85r5iwj314s02241m.jpg)



重新解压并重命名：



```
tar -zxf sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz

mv sqoop-1.4.7.bin__hadoop-2.6.0 sqoop
```



修改~/.bashrc文件，添加环境变量

```
export PATH=$PATH:/home/liuhongyang/sqoop/bin
```



进入/sqoop/conf目录, 修改`sqoop-env-template.sh `成`/sqoop/conf/sqoop-env.sh`目录

```
mv /sqoop/conf/sqoop-env-template.sh /sqoop/conf/sqoop-env.sh
```



进入sqoop-en.sh修改环境变量：

```
#Set path to where bin/hadoop is available
export HADOOP_COMMON_HOME=/home/liuhongyang/hadoop

#Set path to where hadoop-*-core.jar is available
export HADOOP_MAPRED_HOME=/home/liuhongyang/hadoop
```



此时sqoop的运行环境如下：

![image-20200322135018597](https://tva1.sinaimg.cn/large/00831rSTgy1gd2nrk14vij30kg074403.jpg)





将mysql的驱动程序导入到sqoop的lib目录下：

```shell
cp ~/Desktop/mysql-connector-java-5.0.8-bin.jar ~/sqoop/lib/
```



>  In mysql

create table

```mysql
USE hive;
create table oilCrawl(Year varchar(4),AverageClosingPrice varchar(10),
YearOpen varchar(10),YearHigh varchar(10),YearLow varchar(10),YearClose varchar(10), AnnualChange(10)
)
exit;
```

![image-20200322152428621](https://tva1.sinaimg.cn/large/00831rSTgy1gd2qhla8roj30wy088ta5.jpg)



import oilPrice.csv into mysql

```mysql
mysql -uroot -proot --local_infile=1 mysql -e "LOAD DATA LOCAL INFILE
'~/Desktop/oilCrawl.csv' INTO TABLE oilCrawl FIELDS TERMINATED NY ','"
```



```mysql
use hive
select * from oilCrawl;
```



use sqoop to import data from mysql to hdfs



```mysql
sqoop impor --connect jdbc:mysql://localhost/hive --username root --password password --table oilCrawl -m 1
```









### 使用篇



创建文件夹

```
hdfs dfs -mkdir /usr
hdfs dfs -mkdir /usr/hadoop
```



上传文件夹

创建一个本地的目录~/input, 文件夹里放`b.txt`文件

```
hdfs dfs -put ~/input /usr/hadoop
```



上传之后可以在

localhost:50070下,Utilities中Browse the system中看到如下路径：

![image-20200321212340615](https://tva1.sinaimg.cn/large/00831rSTgy1gd1v8z0an3j30y409ijs2.jpg)



将文件拷贝到本地home目录下面：

```
hdfs dfs -copyToLocal /usr/hadoop/input/b.txt ~/
```



查看里面的内容:

```
hdfs dfs -cat /usr/hadoop/input/b.txt
```



删除内容:

```shell
 hdfs dfs -rm /usr/hadoop/input/b.txt
```



> Hive数据导入：

![image-20200322121952288](https://tva1.sinaimg.cn/large/00831rSTgy1gd2l5h3tekj318a0dyn2o.jpg)





例如：

注意的是，在创建表之前，应该指名默认表的分隔符，否则有可能会出现几十数据中表的内容导入成功后，还是查不到数据的情况

```
load data local inpath '/root/data/student01.txt' into table tablename;
select * from t3;
```





将/root/data下的所有数据文件导入t3中，并覆盖原来的数据

```
load data local inpath '/root/data/' overwrite into table t3;
```





将hdfs中，/input/student01.txt导入t3中，不需要指定local:

```
load data inpath '/input/student01.txt' overwrite into table t3;
```





分区表：

需要将data1.txt导入性别为男的分区表中，data2.txt导入到性别为女的分区表中



![image-20200322131603902](https://tva1.sinaimg.cn/large/00831rSTgy1gd2mry8crfj30lu06w76i.jpg)





![image-20200322131528721](https://tva1.sinaimg.cn/large/00831rSTgy1gd2mrgkdpqj30si08w79w.jpg)





```
load data local inpath '/root/data/data1.txt' into table partition_table partition (   gender='M')

load data local inpath '/root/data/data1.txt' into table partition_table partition (   gender='F')
```





使用Sqoop导入Oracle数据到HDFS中



```
sqoop import --connect jdbc:mysql://localhost/hive --username root --password root --table oilCrawl -m 1 --target-dir '/usr/hadoop/hive'
```



![image-20200322154053719](https://tva1.sinaimg.cn/large/00831rSTgy1gd2qynhdfyj319w04wn15.jpg)



使用Sqoop导入Oracle数据到Hive中



```
sqoop import --hive-import --connect jdbc:mysql://localhost/hive --username root --password root --table oilCrawl -m 1
```



![image-20200322154612867](https://tva1.sinaimg.cn/large/00831rSTgy1gd2r468dnpj31a204078d.jpg)



使用Sqoop导入Oracle数据到Hive中, 并指定表名

```
sqoop import --hive-import --connect jdbc:mysql://localhost/hive --username root --password root --table oilCrawl -m 1 --hive-table oilCrawl
```



> 上传数据到hdfs, 再从hdfs上传到hive











### References:



1.配置Virtual篇

[Guest Additions CD image:  This system is currently not set up to build kernel modules. Please install the gcc make perl packages from your distribution](https://askubuntu.com/questions/1140770/this-system-is-currently-not-set-up-to-build-kernel-modules-please-install-the)

[VirtualBox安装Ubuntu教程](https://brb.nci.nih.gov/seqtools/installUbuntu.html)



2.配置Java篇

[Oracle Java下载官网](https://www.oracle.com/cn/java/technologies/javase/javase-jdk8-downloads.html)

[How to Install Java on Ubuntu 18.04](https://linuxize.com/post/install-java-on-ubuntu-18-04/)

[删除open-jdk](https://askubuntu.com/questions/965407/openjdk-8-is-not-installed-correctly)



3.配置Hadoop篇



[connect to host localhost port 22: Connection refused](https://sites.google.com/site/randomt3ch/home/connect-to-host-localhost-port-22-connection-refused)



4.配置Mysql篇

[Mysql安装教程](https://www.bilibili.com/video/av79634250?from=search&seid=13093939999035770830)

[Mysql删除教程](https://linuxscriptshub.com/uninstall-completely-remove-mysql-ubuntu-16-04/)

[Install MySQL Server on the Ubuntu operating system](https://support.rackspace.com/how-to/install-mysql-server-on-the-ubuntu-operating-system/)

[mysql 远程连接数据库的二种方法](https://cloud.tencent.com/developer/article/1153843)

[连接远程mysql数据库失败常见原因及解决办法](https://blog.csdn.net/qq_16885135/article/details/53096451)

[MySQL创建用户与授权](https://www.jianshu.com/p/d7b9c468f20d)

[How to stop mysqld](https://stackoverflow.com/questions/11091414/how-to-stop-mysqld)



5.文件下载

[下载](http://archive.apache.org/dist/hive/hive-2.1.0/apache-hive-2.1.0-bin.tar.gz)



6.Hive

[Hive](https://stackoverflow.com/questions/35449274/java-lang-runtimeexception-unable-to-instantiate-org-apache-hadoop-hive-ql-meta)



7.阿里云配置Hadoop



[阿里云1](https://blog.csdn.net/bqw18744018044/article/details/79103931)

[阿里云2](https://blog.csdn.net/dongdong9223/article/details/81275360)