---
layout: post
author: LIU,HONGYANG
tags: [Linux]
---





（1）首先我们使用ifconfig命令查询一下网卡名称

 ![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlfs65tzj318a0kkgtq.jpg)



提示：如果提示没有ifconfig命令，首先应该下载一个net-tools

仅需执行命令：

```
apt install net-tools
```





（2）编辑/etc/network/interfaces文件

执行命令:



sudo gedit /etc/network/interfaces

  

 ![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfm1h0cm02j31800s6764.jpg)

 



修改文件，网卡名字为lo



则将文件修改为：

auto lo

iface lo inet static       //指定为静态

address  10.211.55.1     //IP地址

gateway 10.211.55.2     //网关

netmask 255.255.255.0  //子网掩码

broadcast 192.168.1.255  //广播

 

然后重启网络设备

 

sudo /etc/init.d/networking restart

重启

 

接来下修改DNS访问外网(可选)

 

##### References：



ubuntu server 网络配置，主机名配置:

https://www.cnblogs.com/pipci/p/9230195.html



Ubuntu 14.04下静态DNS配置详解:

https://www.linuxidc.com/Linux/2017-11/148587.htm

https://zhidao.baidu.com/question/1669630541812482467.html

https://blog.csdn.net/luteresa/article/details/68061156

 