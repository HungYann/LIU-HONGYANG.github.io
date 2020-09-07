---
layout: post
author: LIU,HONGYANG
tags: [设计模式]
---





### Factory Method模式

父类定义接口，将实例创建生成过程交给交给子类，让子类决定创建产品

> Product(产品)

Product角色属于框架这一方，是一个抽象类。它在Factory Method模式中生成实例所持有的接口(API), 但具体的处理则由子类ConcreteProduct角色决定。



> Creator(创建者)

负责生成Product角色的抽象类，但具体的处理则由ConcreteCreator角色决定。在示例程序中，由Factory类扮演此角色。



> ConcreteProduct(具体的产品)

属于加工这一方，它决定了具体的产品。在示例程序中，由IDCard类扮演此角色。



> ConcreteCreator(具体的创建者）

ConcreteCreator角色属于加工者这一方，它负责生成具体的产品。在示例程序中，由IDCardFactory类扮演此角色。

![image-20200211150659567](https://tva1.sinaimg.cn/large/0082zybpgy1gbsh71864oj30om0n4406.jpg)



类的一览表



| 包        | 名字          | 说明                                         |
| --------- | ------------- | -------------------------------------------- |
| framework | Product       | 只定义抽象方法use的抽象类                    |
| framework | Factory       | 实现了create方法的抽象类                     |
| idcard    | IDCard        | 实现了use方法的类                            |
| idcard    | IDCardFactory | 实现了createProduct、registerProduct方法的类 |
| 无        | Main          | 测试程序行为                                 |









![image-20200211152536628](https://tva1.sinaimg.cn/large/0082zybpgy1gbshqeib36j30o80mmq4m.jpg)



> package framework



Factory.java

```java
package framework;

public abstract class Factory {

    protected abstract Product createProduct(String owner);

    protected abstract void registerProduct(Product product);


    public  final Product create(String owner){
        Product p = createProduct(owner);
        registerProduct(p);

        return p;
    }

}
```



Product.java

```java
package framework;

public abstract class Product {
    public abstract void use();
}

```





> package idcard 



IDCard.java

```java
package idcard;

import framework.Product;

public class IDCard extends Product {
    private String owner;
    public IDCard(String owner) {
        System.out.println("制作"+owner+"的ID卡");
        this.owner = owner;
    }

    public void use(){
        System.out.println("使用"+owner+"的ID卡");
    }

    public String getOwner(){
        return owner;
    }

}

```



IDCardFactory .java

```java
package idcard;

import framework.Factory;
import framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDCardFactory extends Factory {

    private List owners = new ArrayList();



    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }


    protected  void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners(){
        return owners;
    }

}

```





Main.java

```java
import framework.Factory;
import framework.Product;
import idcard.IDCardFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 =factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");

        card1.use();
        card2.use();
        card3.use();
    }
}

```



总结，工厂模式遵循了依赖倒置原则





![image-20200211195232584](https://tva1.sinaimg.cn/large/0082zybpgy1gbspg4wv4ej311o0fsdiq.jpg)

访问权限：

默认default访问的权限是Package，表示IDCard只能由IDCardFactory创建，不能由其它包中类创建。

![image-20200211143733657](https://tva1.sinaimg.cn/large/0082zybpgy1gbsgcf50snj30zc0aajt1.jpg)





![image-20200211195051450](https://tva1.sinaimg.cn/large/0082zybpgy1gbspehopl5j312y0dawfo.jpg)

构造方法必须实现，如果父类中定义了构造方法，则子类中必须使用super关键字， 先给父类变量赋值。

构造方法不能加abstract关键字