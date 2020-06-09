---
layout: posts
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

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190817081951800-199373160.png)

 

 

**2.临时修改主机名**

hostname <主机名>

 

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190817082354192-556734807.png)

 

更改成功后

 

输入uname -a或者hostname

查看新目录

打开新的terminal查看主机名变成tem:

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190817082445086-400632505.png)

 

 

**3.永久修改主机名**

 

第一步:

修改配置文件/etc/hostname

 

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190817083109621-169656609.png)

 

 

第二步:

修改配置文件/etc/hosts（可选）

 

配置ip与主机名之间的映射关系

 

![img](https://img2018.cnblogs.com/blog/1067977/201908/1067977-20190817083423691-348231557.png)

 

 

第三步：

重启计算机生效

sudo reboot