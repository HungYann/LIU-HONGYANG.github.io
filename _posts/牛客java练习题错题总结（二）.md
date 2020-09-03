---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



> 1

![image-20200903114912424](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidbif6reij31b00liwgd.jpg)





和序列化有关，这是一个空接口，起标记作用，具体的序列化由ObjectOutputStream和ObjectInputStream完成。transient修饰的变量不能被序列化，static变量不管加没加transient都不可以被序列化



> 2

![image-20200903115447560](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidbo8cs88j31ay0midht.jpg)



> 3



![image-20200903115802222](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidbrlpss3j31be0m8wgr.jpg)



A：抽象方法只可以被public 和 protected修饰；

B：final可以修饰类、方法、变量，分别表示：该类不可继承、该方法不能重写、该变量是常量

C：static final 可以表达在一起来修饰方法，表示是该方法是静态的不可重写的方法

D：private 修饰方法（这太常见的）表示私有方法，本类可以访问，外界不能访问



（1）对于public修饰符，它具有最大的访问权限，可以访问任何一个在CLASSPATH下的类、接口、异常等。它往往用于对外的情况，也就是对象或类对外的一种接口的形式。

（2）对于protected修饰符，它主要的作用就是用来保护子类的。它的含义在于子类可以用它修饰的成员，其他的不可以，它相当于传递给子类的一种继承的东西。

（3）对于default来说，有点的时候也成为friendly（友员），它是针对本包访问而设计的，任何处于本包下的类、接口、异常等，都可以相互访问，即使是父类没有用protected修饰的成员也可以。

（4）对于private来说，它的访问权限仅限于类的内部，是一种封装的体现，例如，大多数的成员变量都是修饰符为private的，它们不希望被其他任何外部的类访问。



1.抽象方法只能定义在抽象类中，抽象方法和抽象类必须由abstract修饰，abstract关键字只能描述类和方法，不能描述变量。抽象方法只定义方法声明，不定义方法实现。抽象类不可以被实例化（创建对象），只有通过子类继承抽象类并覆盖抽象类中的所有抽象方法后，该子类才可以被实例化，否则该子类还是一个抽象类。抽象类中有构造函数用于给子类对象进行初始化，同时抽象类中可以含有非抽象方法。abstract关键字不可以与final，private,static关键字共存，因为被final修饰的方法不可以被重写，意味着子类不可以重写该方法，如果abstract和final共同修饰父类中的方法，子类要实现抽象方法（abstract的作用），而final又不让该方法重写，这相互矛盾。如果private和abstract共同修饰父类中的方法，private修饰则该方法不可以被子类访问，但是abstract修饰需要子类去实现，两者产生矛盾。如果static和abstract共同修饰父类中的方法，static表示是静态的方法，随着类的加载而加载，则该方法不需要在子类中去实现，这与abstract关键字矛盾。 



2.static用于修饰成员变量和成员函数，想要实现对象中的共性数据的对象共享，可以将这个数据进行静态修饰，被静态修饰的成员可以直接被类名调用，静态随着类的加载而加载，而且优先于对象存在。静态方法只能访问静态成员（静态方法和静态变量），不可以访问非静态成员，这是因为静态方法加载时，优先于对象存在，所以没有办法访问对象中的成员。静态方法中不能使用this和super关键字，因为this代表本类对象，super代表父类对象，而静态时，有可能没有对象存在，所以this和super无法使用。 3.final关键字可以修饰类，方法，变量（成员变量内，局部变量，静态变量），被final修饰的类是一个最终类，不可以被继承，被final修饰的方法是一个最终方法，不可以被覆盖，但是可以被继承。被final修饰的变量只能是一个常量，只能赋值一次。内部类被定义在类中的局部位置上时，只能访问局部被final修饰的局部变量。



> 4

![image-20200903120307285](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidbwvqosrj31bg0lmacu.jpg)






History 属于浏览器对象： 

- length：URL 数量 
- go：加载 history 列表中某个具体页面 
- back：加载 history 列表中前一个 URL
- forward：加载 history 列表中下一个 URL

参考链接：https://www.w3school.com.cn/jsref/dom_obj_history.asp



> 5



![image-20200903122438572](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidcj9vkdvj31bo0q6dis.jpg)



 ①无论如何，Integer与new Integer不会相等。不会经历拆箱过程， 
 ②两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false 
 java在编译Integer i2 = 128的时候,被翻译成-> Integer i2 = Integer.valueOf(128);而valueOf()函数会对-128到127之间的数进行缓存 
 ③两个都是new出来的,都为false 
 ④int和integer(无论new否)比，都为true，因为会把Integer自动拆箱为int再去比



> 6

![image-20200903122541936](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidckee4eaj31bk0m6jtc.jpg)



简单记忆线程安全的集合类： **喂！SHE！  喂是指** **vector，S是指 stack，** **H是指**  **hashtable，E是指：Eenumeration**



