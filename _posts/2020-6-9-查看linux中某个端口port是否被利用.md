---
layout: posts
author: LIU,HONGYANG
tags: [Linux]
---







(1)lsof -i:端口号查看某个端口是否被占用

![img](https://img2018.cnblogs.com/blog/1067977/201910/1067977-20191019131607669-1008164825.png)

 

 

 

![img](https://img2018.cnblogs.com/blog/1067977/201910/1067977-20191019131946429-201691523.png)

 

 

 

(2)netstat -an|grep 80

**netstat** -- show network status

![img](https://img2018.cnblogs.com/blog/1067977/201910/1067977-20191019132237549-1129622490.png)

 （3）杀掉进程

kill pid

 

注意：端口Port号和进程号PID不是一样的!

但是杀掉进程可以关闭端口号