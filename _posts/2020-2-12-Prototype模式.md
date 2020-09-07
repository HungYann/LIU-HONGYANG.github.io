---
layout: post
author: LIU,HONGYANG
tags: [设计模式]
---





### Prototype模式

- Prototype(原型)

  负责定义用于复制现有实例来生成新实例的方法。

- ConcretePrototype(具体的原型)

  ConcreteProtype角色负责实现复制现有实例并生成新实例的方法。在实例程序中，由MessageBox类和UnderlinePen类扮演此角色。

- Client(使用者)

  

  类和接口的一览表

  

  | 包        | 名字         | 说明                                                         |
  | --------- | ------------ | ------------------------------------------------------------ |
  | framework | Product      | 声明了抽象方法use和createClone的接口                         |
  | framework | Manager      | 调用createClone方法复制实例的类                              |
  | 无名      | MessageBox   | 将字符串放入方框中并使其显示出来的类。实现了user方法和createClone方法 |
  | 无名      | UnderlinePen | 给字符串加上下划线并使其显示出来的类。实现了user方法和createClone方法 |
  | 无名      | Main         | 运行程序                                                     |

  

  

  ![image-20200211234029865](https://tva1.sinaimg.cn/large/0082zybpgy1gbsw1cvl93j30qu0ho0ty.jpg)



Main.java

```java
import framework.Manager;
import framework.Product;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen("~");
        UnderlinePen upen2 = new UnderlinePen("*");
        manager.register("strong message",upen);
        manager.register("waring box",upen2);
        Product p1 = manager.create("strong message");
        Product p2 = manager.create("waring box");
        p1.use("Hello,world");
        p2.use("waring box");
    }
}

```



>  Framework

Manager.java

```java
package framework;

import java.util.HashMap;

public class Manager {
    private HashMap showcase = new HashMap<>();
    public void register(String name,Product proto){
        showcase.put(name,proto);
    }

    public Product create(String protoname){
        Product p = (Product)showcase.get(protoname);
        return p.createClone();

    }
}

```



Product.java

```java
package framework;

public interface Product extends Cloneable{
    public abstract void use(String s);
    public abstract Product createClone();
}

```





MessageBox.java



```java
import framework.Product;

public class MessageBox implements Product {

    private char decochar;

    public MessageBox(char decochar) {
        this.decochar = decochar;
    }


    @Override
    public void use(String s) {
        int length = s.length();
        for (int i = 0; i < length ; i++) {
            System.out.println(decochar);
        }

        System.out.println("");
        System.out.println(decochar+" "+s+" "+decochar );
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
    }


    @Override
    public Product createClone() {
        Product p = null;

        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;


    }
}

```



````java
import framework.Product;

public class UnderlinePen implements Product {
    String ulchar;

    public UnderlinePen(String ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        int length = s.length();
        System.out.println("\""+s+"\"");
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println("");

    }

    @Override
    public Product createClone() {
        Product p = null;

        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;

    }
}

````

