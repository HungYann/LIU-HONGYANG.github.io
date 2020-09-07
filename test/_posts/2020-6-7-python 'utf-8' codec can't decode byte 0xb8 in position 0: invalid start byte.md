---
layout: post
author: LIU,HONGYANG
tags: [Python]
---





![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrm9eks9nj30xu0n4n18.jpg)

 

 

在导入csv文件中，出现如上所示的错误，经过查阅资料，解决方法如下：

方法一：

pd.read_csv(file_path, encoding='unicode_escape')

方法二：

pd.read_csc(file_path.encoding='gbk')