---
layout: post
author: LIU,HONGYANG
tags: [Linux]
---







**1.查看主机名的方法**

 

方法1:

在终端输入hostname

方法2: 

提示符

liuhongyang@ubuntu:~$

liuhongyang:用户名

ubuntu:    主机名

~:        当前路径

 

方法3:

uname -a 

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlf3dwkyj317w0reacg.jpg)

 

 

**2.临时修改主机名**

hostname <主机名>

 

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlf4unyfj318e0nc77m.jpg)

 

更改成功后

 

输入uname -a或者hostname

查看新目录

打开新的terminal查看主机名变成tem:

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlf8oih1j31860n0my4.jpg)

 

 

**3.永久修改主机名**

 

第一步:

修改配置文件/etc/hostname

 

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlfiduebj318e0oyab4.jpg)

 

 

第二步:

修改配置文件/etc/hosts（可选）

 

配置ip与主机名之间的映射关系

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrlfcbto9j31860q00vd.jpg)

 

 

第三步：

重启计算机生效

sudo reboot