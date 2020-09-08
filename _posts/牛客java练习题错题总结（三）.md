---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



> 1

![image-20200907113254161](https://tva1.sinaimg.cn/large/007S8ZIlgy1gihxiouhqwj31jo0pu0vq.jpg)



**对于管道，有下面这几种类型：**

**①普通管道（PIPE）：通常有两种限制，一是单工，即只能单向传输；二是血缘，即常用于父子进程间（或有血缘关****系的进程****间）。**

**②流管道（s_pipe）：去除了上述的第一种限制，实现了双向传输。**

**③命名管道（name_pipe）：去除了上述的第二种限制，实现了无血缘关系的不同进程间通信。**

**显然，要求是对于不同的服务器之间的通信，是要要求全双工形式的，而管道只能是半双工\**，虽然可以双向，\**但是同一时间只能有一个方向**



**管道( pipe )：**管道是一种半双工的通信方式，数据只能单向流动，**而且只能在具有亲缘关系的进程间使用**。进程的亲缘关系通常是指父子进程关系。

**既然是不同的服务器之间进行通信，怎么可能会是具有亲缘关系的进程呢？**



![image-20200907113349884](https://tva1.sinaimg.cn/large/007S8ZIlgy1gihxjmtu4kj309s07g0tv.jpg)



> 2



![image-20200907113455000](https://tva1.sinaimg.cn/large/007S8ZIlgy1gihxkrtluoj31e20u042a.jpg)





B、C f1、f2、f3是数组，是引用类型，f、x是基本类型。基本类型之间的比较，应该会将低精度类型自动转为高精度类型再比较,B选项是long和float比较，相当于long和long比较，所以B对。



> 3

![image-20200907113918070](https://tva1.sinaimg.cn/large/007S8ZIlgy1gihxpc3hyyj318r0u0dkg.jpg)



func0: 无返回值

func1:无返回值

func2: short类型会被强转成float类型

func3: long类型会被强转成float类型

func4:错误，float比double低



这道题考的是数据类型转换问题。由大到小需要强制转换，由小到大不需要。



![Screenshot 2020-09-07 at 11.43.55](https://tva1.sinaimg.cn/large/007S8ZIlgy1gihxu9i1jij31dy0qs44u.jpg)