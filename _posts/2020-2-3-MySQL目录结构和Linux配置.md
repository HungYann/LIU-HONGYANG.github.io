MySQL目录结构

- bin目录，存储可执行文件
- data目录，存储数据文件
- docs文档
- include目录，存储包含的头文件
- lib目录，存储库文件
- share目录，错误消息和字符集文件

mysql配置文件：

**my.ini**

```

client

port=3306

mysql

default-character-set=utf-8

```


mysqld:


mysql服务器端的配置

```
character-set-server=utf8
```




#### 在Linux平台下安装MySQL

### 安装


三种方式：RPM包、二进制包、源码包

RPM是Redhat Package Manage的缩写

安装Server包

```{} 
su

rpm -ivh MySQL-server-community-5.0.45-0.rhel3.i386.rpm
```

安装MySQL Client包


```{}
rpm -ivh MySQL-client-community-5.0.45-0.rhel3.i386.rpm

```

运行MySQL:

```{}
mysql -uroot -p
```


### 配置

查看MySQL服务状态：

```{}
netstat 
```
端口号: **3306**

启动和关闭MySQL:

如何MySQL是用RPM包安装的，则启动和关闭MySQL:

```{}
service mysql start 
```


```{}
service mysql stop
```
