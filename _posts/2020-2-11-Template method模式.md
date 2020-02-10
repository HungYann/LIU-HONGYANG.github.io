### Template Method模式

在父类中定义处理流程的框架，在子类中实现具体处理

在Template Method模式中有以下登场角色

- AbstractClass（抽象类）
- ConcreteClass (具体类)

AbstractClass角色不仅负责实现模版方法，

![image-20200210213319080](https://tva1.sinaimg.cn/large/0082zybpgy1gbrmqozm9vj30bs0e4abl.jpg)z

当使用AbstractClass abC = new ConcreteClass(“ABC”)时，再调用abC.templateMethod方法，会执行templateMetod方法中的内容，如果template方法中指定了method1, method2 和method3的实现，则会执行ConcreteClass实现的方法。







| 名字            | 说明                               |
| --------------- | ---------------------------------- |
| AbstractDisplay | 只实现了display方法的抽象类        |
| CharDisplay     | 实现了open、print、 close方法的类  |
| StringDisplay   | 实现了open、 print、 close方法的类 |
| Main            | 测试程序行为的类                   |



`模版方法`：

调用抽象方法的display方法就是模版方法。它有两个核心要素一是在抽象类中，二是调用抽象方法

![image-20200210234208253](https://tva1.sinaimg.cn/large/0082zybpgy1gbrqgrub8aj30k80h4my3.jpg)



AbstractDisplay.java

```java
public abstract class AbstractDisplay {
    public abstract void open();
    public abstract void print();
    public abstract void close();
    public final void display(){
        open();
        for (int i=0;i<5;i++){
            print();
        }
        close();
    }

}
```





CharDisplay.java

```java
public class CharDisplay extends AbstractDisplay {
    private char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.println("<<");
    }

    @Override
    public void print() {
        System.out.println(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}

```



StringDisplay.java

```java
public class StringDisplay extends AbstractDisplay{

    private String string;
    private int width;
    public StringDisplay(String string){
        this.string = string;
        this.width = string.length();
    }

    public void open(){
        printLine();
    }

    public void print(){
        System.out.println("|"+string+"|");
    }

    public void close(){
        printLine();
    }

    private void printLine(){
        System.out.print("+");
        for (int i = 0; i < width ; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}

```





Main.java

```java
public class Main {
    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('H');
        d1.display();

        AbstractDisplay d2 = new StringDisplay("Hello,World");
        d2.display();

        AbstractDisplay d3= new StringDisplay("Hello,World");
        d3.display();

    }
}

```





#### 运行结果



![image-20200211002658433](https://tva1.sinaimg.cn/large/0082zybpgy1gbrrrdt950j306y0lqaao.jpg)



可以看到，调用父类的display模版方法后，会执行模版方法，尽管父类没有实现模版方法，但是会执行子类中已经实现的模版方法。所以可以根据子类定制具体的实现，但是父类的算法都是一致的。





#### 总结

使用template  Method的好处，

1.可以使逻辑处理通用化，由于在父类中编写了算法，因此无需在子类中编写算法。

2.父类和子类之间的协作，在 template method模式中，子类和父类紧密联系、共同工作，因此，在子类中实现父类中声明的抽象方法时，必须要理解抽象方法被调用的时机。