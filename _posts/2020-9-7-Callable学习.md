---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



Callable接口API定义：

![image-20200907232005115](https://tva1.sinaimg.cn/large/007S8ZIlgy1giihyk82y6j31y409kwh4.jpg)

1.可以有返回值

2.可以抛出异常

3.方法不同，run(), call()



首先线程启动的方式有且只有一个就是.start()



![image-20200907235013996](https://tva1.sinaimg.cn/large/007S8ZIlgy1giiitv4guyj31xu0d40vf.jpg)



为什么用FutureTask可以实现**Callable** ?

1. 首先FutureTask可以传递Callable构造参数
2. 而FutureTask又是Runnable接口的实现类

因此可以直接使用new Thread方法，放入futureTask的实例对象



![image-20200907235834755](https://tva1.sinaimg.cn/large/007S8ZIlgy1giij2jv0ooj31yk0mujx7.jpg)