---
layout: post
title: Python连接MySQL数据库
date: 2020-03-23
author: LIU,HONGYANG
tags: [Java]
comments: true
toc: true
pinned: false
---





### Python连接MySQL数据库

首先使用Python3.7直接下载MySQL-python,会发现报错



![image-20200310113956978](https://tva1.sinaimg.cn/large/00831rSTgy1gcook9ntlij30vs0e6wi4.jpg)



接着升级pip组件重试

```shell
pip install -U setuptools
```



![image-20200310114115773](https://tva1.sinaimg.cn/large/00831rSTgy1gcoollbaaxj30vk08o407.jpg)



安装mysql-connector-python包

```shell
pip install mysql-connector-python
```

![image-20200310114322472](https://tva1.sinaimg.cn/large/00831rSTgy1gcoontg1pvj31020lwgqt.jpg)



使用python连接mysql



```python
import mysql.connector
mydb = mysql.connector.connect(host='localhost',user='root',passwd='lhy942821',database='demo')
mycursor = mydb.cursor()
mycursor.execute("SHOW TABLES")
for x in mycursor:  print(x)
```







### References:



[Python MySQL - mysql-connector 驱动](https://www.runoob.com/python3/python-mysql-connector.html)

[Python连接Hive的两种方法](https://blog.csdn.net/Clany888/article/details/82989068?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task)