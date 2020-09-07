---
layout: post
author: LIU,HONGYANG
tags: [设计模式]
---





### Singleton模式学习



- 想确保任何情况下都绝对只有1个实例
- 想在程序上表现出“只存在一个实例”



| 名字      | 说明               |
| --------- | ------------------ |
| Singletop | 只存在一个实例的类 |
| Main      | 测试程序行为       |



![image-20200211211747178](https://tva1.sinaimg.cn/large/0082zybpgy1gbsrwyae5qj30dq08qdg6.jpg)



|          | 静态对象           | 非静态对象                 |
| -------- | ------------------ | -------------------------- |
| 拥有属性 | 类共同拥有         | 类各个对象独立拥有         |
| 内存分配 | 内存空间上固定分配 | 空间在各个附属类中分配     |
| 分配属性 | 先分配静态属性     | 继而再对非静态属性分配空间 |

Singleton.java

```java
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton(){
        System.out.println("生成了一个实例");
    }

    public static Singleton getInstance(){
        return singleton;
    }
}

```



Main.java

```java
public class Main{
    public static void main(String[] args) {
        System.out.println("Start.");
        Singleton ob1 = Singleton.getInstance();
        Singleton ob2 = Singleton.getInstance();

        if (ob1 == ob2){
            System.out.println("ob1与ob2是相同实例");
        }else{
            System.out.println("ob1与ob2是不同实例");
        }

        System.out.println("End.");


    }
}
```



总结，Singleton类定义了static字段singleton, 并将其初始化为Singleton类实例。初始化行为仅在该类被加载时进行一次。

Singleton类的构造函数时private, 禁止从Singleton类外部调用构造函数。如果从Singleton类以外的代码中调用构造函数new Singleton(), 就会报错。





