---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





### Linux下修改 JAVA环境变量



在配置hadoop的过程中，我们可能会遇见java环境变量配置的问题，

首先是如何找到Java环境变量的位置

其次是配置Java环境变量



> 找到Java环境变量的位置

```shell
readlink -f /usr/bin/java | sed "s:bin/java::"
```





>  配置Java环境变量



首先查看Java环境变量

```shell
echo $PATH
```

![image-20200304113052622](https://tva1.sinaimg.cn/large/00831rSTgy1gchqkzqzg2j30vc0420tj.jpg)



- 临时修改

```shell
export PATH=$PATH:/usr/bin/java
```



- 永久修改



期限：永久有效

用户局限：仅针对当前用户



```shell
vim ~/.bashrc
//添加此句
export PATH=/usr/local/bin:$PATH
```

关闭保存，执行一下命令：



```shell
source ~/.bashrc
```



- 全局修改

```shell
vim /etc/profile
export PATH=/usr/local/bin:$PATH
```



关闭保存，执行以下命令:

```shell
source /etc/profile
```

