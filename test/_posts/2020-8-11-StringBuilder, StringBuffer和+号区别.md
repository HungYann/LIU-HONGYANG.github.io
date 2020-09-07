---
layout: post
author: layout
tags: [Java]
---



### Int类型和String类型相加这个过程



int 和 string, int类型会被转化成integer方法，并自动触发toStirng方法。

string 加 string方法，这个过程，会自动new StringBuilder, StringBuilder.append().toString()方法，

形成下一个方法





### StringBuilder和StringBuffer的区别



StringBuilder是线程不安全的，16+ argument.length



StringBuffer是线程安全的，它进程被使用在多线程的环境下，synchronised, 但是速度较慢。





