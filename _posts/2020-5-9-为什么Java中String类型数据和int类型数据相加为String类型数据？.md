---
layout: post
author: LIU,HONGYANG
tags: [Java]
---




今天看书的时候，看到`+`号可以连接字符串，我在想为什么`-`号不能连接字符串，而偏偏加号可以呢？



所以引申出一个问题：



```
为什么Java中String类型数据和int类型数据相加为String类型数据？
```



```
public class MyString2 {    public static void main(String[] args) {        String string = "案例";        int val = 1;        short val2 = 2;        char val3 = 'a';        System.out.println(string+val);        System.out.println(string+val2);        System.out.println(string+val3);    }}
```



运行结果：



![img](https://mmbiz.qlogo.cn/mmbiz_png/H7Lrpxk7QYKtDA6hCFPNwyRDzibQpWUPBAZVZ689XJlgibczNDI6fpmx6ddbtLbL5L0SZicBpvmCgXic7gArhNuNeg/0?wx_fmt=png)







![img](https://mmbiz.qlogo.cn/mmbiz_jpg/H7Lrpxk7QYKtDA6hCFPNwyRDzibQpWUPBdIzL7u4erLXvo9sz293wI4HLzdvXzvGqEUkrszhx3e0SLF9JOck39w/0?wx_fmt=jpeg)





答案就是这句话



**当一个int值需要和一个String 连接时，它的类型会被转换为 integer并触发toString方法。**