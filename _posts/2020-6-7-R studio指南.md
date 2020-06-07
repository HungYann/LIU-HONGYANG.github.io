---
layout: posts
author: LIU,HONGYANG
tags: [R]
---



笔者使用Rstudio编写R程序，本文主要总结在编写过程中遇到的一些实际 问题

与学习配套的的code上传到我的github，网址：

https://github.com/LIU-HONGYANG/Scrapy

 

### 基础语法 

1.查看工作路径

 

getwd()

 *"/Users/liuhongyang"*

 

2.设置工作路径

 

setwd("/Users/liluhongyang/Desktop/data")

 

3.判断文件是否存在

file.exists("data")

 

4.创建文件

dir.create("data")

 

5.如果文件不存在，创建文件

if(!file.exists("data"))

{

　　dir.create("data")

}

6.读取文件数据

weather <- readRDS('data/weather.rds')

 

*7.显示前6行数据*

head(weather)

 

8.显示最后6行数据

tail(weather)

 

9.查看数据的简要摘要 

str(weather

 

10.检查数据的类型

class(bmi)

 

11.检查数据的维度

dim(bmi)

 

12.检查数据的列名

colnames(bmi)

 

13.下载文件

install.packages("dplyr")

 

14.加载数据库

library(dplyr)

 

15.数据可视化

hist(bmi$Y2008)

 

16.Scatter plot comparing

plot(bmi$Y1980,bmi$Y2008)

 

### R studio markdown syntax



 ![img](https://img2018.cnblogs.com/blog/1067977/201910/1067977-20191027193446384-165871846.png)

 

 

 

 

 

 

 

常见的结果：

 

![img](https://img2018.cnblogs.com/blog/1067977/201910/1067977-20191027192301035-163696113.png)

 