---
layout: posts
author: LIU,HONGYANG
tags: [Python]
---





![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190914101146906-462692582.png)

 

 

在导入csv文件中，出现如上所示的错误，经过查阅资料，解决方法如下：

方法一：

pd.read_csv(file_path, encoding='unicode_escape')

方法二：

pd.read_csc(file_path.encoding='gbk')