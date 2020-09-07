---
layout: post
author: LIU,HONGYANG
tags: [Linux]
---







(1)lsof -i:端口号查看某个端口是否被占用

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrljlz9cej30na01oaa6.jpg)

 

 

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrljppwx1j30uu0h4go1.jpg)

 

 

 

(2)netstat -an|grep 80

**netstat** -- show network status

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrljvdqkgj30uy0eiwi2.jpg)

 （3）杀掉进程

kill pid

 

注意：端口Port号和进程号PID不是一样的!

但是杀掉进程可以关闭端口号