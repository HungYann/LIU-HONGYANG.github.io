---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



Trails Covering the Basics

 

1 Getting Started

1.1 The Java Technology Phenomenon

1.1.1 About the Java Technology 

 

The Java Programming Language

 

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmklbmkhj30xe07oacs.jpg)

 

Figure 1 an overview of the software development process

 

 

java文件以.java作为后缀

源文件被javac compiler编译为.class 文件

.class文件中是字接码

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmkozi7uj30w60kwdmb.jpg)

 

Figure 2 java跨平台的原因

 

在不同系统中安装java vm, .class 文件可以在不同平台上运作。

 

The Java Platform

 

平台是软硬件运行的地方

但是java平台不同于其他的平台

 

Java平台仅仅只是软件，他运行在基于其他硬件的平台之上（Figure 3）

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmksm6a8j30t009ggnk.jpg)

 

Figure 3 java平台的两个组件

 

 

 

 

1.1.2 What Can Java Technology do?

开发工具：

Javac 编译器

java 启动器

javadoc 文档工具

 

API:

提供给JAVA核心的编程功能

1.1.3 How Will Java Technology change my life?

Write once, run anywhere 

 

 

Write better code:

垃圾自动回收避免内存泄漏

 

 

1.2 The "Hello World" Application

这一部分使用netbean和不同的操作系统环境编写“Hello World”

略

 

 

1.3 A Closer Look at "Hello World"

注释：

/* text */

/** documentation */

//text 

 

The main method 

public static void main(Stirng[] args)

args argv 

 

初始化运行时的参数

 

 

```
java MyApp arg1 arg2
```

 

 

1.4 Common Problems(and Their Solutions)

一些初学者常见的错误

https://docs.oracle.com/javase/tutorial/getStarted/problems/index.html

Updating the PATH Environment Variable:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#BABGDJFH

 

 

 

\2. Learning the Java Language 

2.1 Object-Oriented Programming Concepts 

2.1.1 What is an Object?

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmkx7cvgj30mi0gktdb.jpg)

 

 

 

万物皆对象

软件队形和现实对象相似

 

他们都由状态和行为组成

state & behaviour

 

state->variables

behaviour->methods

 

2.1.2 What is a Class?

 

bicycle is an instance of the class of the objects

 

A class is the blueprint from which individual objects are created

 

类是对象的实例

类是对象的蓝图

 

 

```
class Bicycle{
	int cadence=0;
    int speed=0;
    int gear=1;
    
    void changeCadence(int newValue)
    {
    	cadence=newValue;
    }
    
    void changeGear(int newValue)
    {
    	gear=newValue;
    }
    
    void speedUp(int increment)
    {
    	speed=speed+increment;
    }
    
    void applyBrakes(int increment)
    {
    
    	speed=speed-decrement;
    }
    void printStates()
    {
    	 System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
 	
}
```

 

 

 

 

 

 

```
class BicycleDemo{
	public static void main(Stirng[] args)
    {
    	Bicycle bicycle1= new Bicycle();
        Bicycle bicycle2= new Bicycle();
        
        bicycle1.changeCadence(50);
        bicycle1.speedUp(10);
        bicycle1.printStates();
        
        bicycle2.changeCadence(60);
        bicycle2.changeGear(20);
        bicycle2.speedUp(10);
        bicycle2.printStates();
    }
}
```

 

 

 

2.1.3 What is Inheritance?

![img]()

 

 

 

```
class MountainBike extends Bicycle
{
	//new fieds and methods defining
    //a mountaion bike would go there
}
```

 

 

2.1.4 What is an Interface?

 

接口是一组具有空体的相关方法。以bicycle为例：

 

 

```
interface Bicycle
{
	void changeCadence(int newValue);
    void changeGear(int newValue);
    void speedUp(int increment);
    void applyBrakes(int decrement);
    
}
```

 

 

 

 

```
class ACMBicycle implements Bicycle
{
	int cadence=0;
    int speed=0;
    int gear=1;
    
    void changeCadence(int newValue)
    {
    	cadence=newValue;
    }
    
    void changeGear(int newValue)
    {
    	gear=newValue;
    }
    
    void speedUp(int increment)
    {
    	speed=speed+increment;
    }
    
    void applyBrakes(int decrement)
    {
    	speed=speed-decrement;
    }
    
    void printSatas()
    {
    	System.out.println("cadence"+cadence+"speed"+speed+"gear"+gear)
    }
}
```

 

 

接口相当于一个契约，在类与外部世界之间。

如果类需要实现一个接口，接口说定义的方法必须出现在源文件中，才能编译成功。

 

 

 

 

2.1.5 What is a Package?

 

 

包是组织一系列相关类和接口的命名空间

 

 

 

Application Programming Interface

 

https://docs.oracle.com/javase/8/docs/api/index.html

 

2.1.6 Questions and Exercises: Object-Oriented Programming Concepts

Exercise 2

 

Real-world objects contain state and behaviour

A software object's state is stored in fields(variables)

A softeare object's behaviour is exposed through methods

Hiding internal data from outside world, and accessing it only through publicly exposed methods is known as data encapsulation.

A blueprint for a software object is called a class

Common behaviour can be defined in a superclass and inherited into a subclass using the extends keyword

A collection of methods wiht no implementment is called an interface.

A namespace that orgnaises classes and interfaces by functionality is called package

The term API stands for Application Programming Interface

 

 

2.2 Language Basics

2.2.1 Variables

2.2.1.1 Primitive Data Types

 

| byte | 8-bit |
| ---- | ----- |
|      |       |

 

 

 

 

Default value

 

| Data type             | Default value(for fields) |
| --------------------- | ------------------------- |
| byte                  | 0                         |
| short                 | 0                         |
| int                   | 0                         |
| long                  | 0L                        |
| float                 | 0.0f                      |
| double                | 0.0d                      |
| char                  | '\u0000'                  |
| String(or any object) | null                      |
| boolean               | false                     |

 

 

Floating-Point Literals

| float  | 以F或者f结尾 |
| ------ | ------------ |
| double | 以D或者d结尾 |

 

 

Character and String Literals

| char   | single quote |
| ------ | ------------ |
| String | double quote |

 

 

 

 

 

 

 

 

2.2.1.2 Arrays

 

Declaring a Variable to Refer to an Array

 

```
byte[] anArrayOfBytes;
short[] anArrayOfShorts;
int[] anArrayOfInts;
long[] anArratOfLongs;
```

 

 

也可以将括号放在最后

 

```
float anArrayOfFloats[]
```

 

 

 

 

Creating,Initializing,and Accessing an Array

 

为整数创造数组

 

initizlizing

```
anArray=new int[10]
```

 

另外一种创造和初始化数组的方式

 

 

```
int[] anArray=
{
100,20,40,50,
500,12,12,14
}
```

 

 

Coping Arrays

 

这个功能是从src开始复制，复制的初始位置是srcPro

```
public static void arrraycopy(Object src, int srcPro
							  Object dest, int destPos, int length
)
```

 

 

 

```
public class Array {
    public static void main(String[] argv)
    {
        char[] copyFrom={'d','e','f','a','b','c','d'};
        char[] copyTo=new char[4];
		
        System.arraycopy(copyFrom,3,copyTo,0,4);
        System.out.println(new String(copyTo));

    }
}
```

 

The conditional operators

&& Conditional - And

|| Conditional - OR

 

 

 

Array Manipulations

 

 

```
class ArrayCopyDemo
{
  	public static void main(String[] args)
    {
    	char[] copyForm={'a','b','c','d','e'}
      	char[] copyTo= java.util.Arrays.copyOfArrays(copyForm,1,4);
        System.out.println(new String(copyTo));
    }
}
```

- Searching an array for a specific value to get the index at which it is placed (the 

```
binarySearch
```

- method).
- Comparing two arrays to determine if they are equal or not (the 

```
equals
```

- method).
- Filling an array to place a specific value at each index (the 

```
fill
```

- method).
- Sorting an array into ascending order. This can be done either sequentially, using the 

```
sort
```

- method, or concurrently, using the 

```
parallelSort
```

- method introduced in Java SE 8. Parallel sorting of large arrays on multiprocessor systems is faster than sequential array sorting.

 

 

2.2.1.3 Summary of Variables

instance variables(non-static fields)

class variables(static fields)

 

2.2.1.4 Questiosn and Exercises:Variables

The term "instance variable" is another name for non-static field

The term "class variable" is another name for static field

A local variable stores temporary state; it is declared inside a method

A variable declared within the opening and closing parenthesis of a method signature is called a parameter

What are the eight primitive data types supported by the Java programming language?

byte short int long float double boolean char

Character strings are represented by the class 

java.lang.String

An array is a container object that holds a fixed number of values of a single type.

 

2.2.2 Operators

2.2.2.1 Assignment,Arithmetic,and Unary Operators

The only difference is that the prefix version (++result) evaluates to the incremented value, whereas the postfix version (result++) evaluates to the original value

++i相对效率更好

i++是先把i的值拿来用,然后在自增1

++i是想把i自增1然后拿来用

 

The arthimetric operators

| Operator | Description             |
| -------- | ----------------------- |
| +        | Additive                |
| -        | Subtraction operator    |
| *        | Multiplication operator |
| /        | Division operator       |
| %        | Remainder operator      |

 

 

 

The unary operators

 

 

| Operator | Description                 |
| -------- | --------------------------- |
| +        | Unary plus operator         |
| -        | Unary minus operator        |
| ++       | Increment operator          |
| --       | Decrement operator          |
| !        | Logical complement operator |

 

2.2.2.2 Equality,Relational, and Conditional Operators

 

The Equality and Relational Operators

| ==   | equal to                 |
| ---- | ------------------------ |
| !=   | not equal to             |
| >    | gearter than             |
| >=   | greater than or equal to |
| <    | less than                |
| <=   | less than or equal to    |

 

 

 

```
public class Array {
    public static void main(String[] argv)
    {
        int value1=1;
        int value2=2;
        if(value1==value2)
        {
            System.out.println("value1==value2");
        }
        if(value1!=value2)
        {
            System.out.println("value1!=value2")
        }
        if(value1>value2)
        {
            System.out.println("value1>vluae2");
        }
        if(value1<value2)
        {
            System.out.println("value1<value2");
        }
    }
}
```

 

The Conditional Operators

 

 

 

 

 

```
public class ConditionalDemo1 {
    public static void main(String[] argv)
    {
        int value1 = 1;
        int value2 = 2;
        if((value1==1)&&(value2==2))
        {
            System.out.println("value1 is 1 AND value2 is 2");
        }
        if((value1==1)||(value2==2))
        {
            System.out.println("value1 is 1 OR value2 is 1");
        }
    }
}
```

 

 

Ternary operator

if someCondition is true , assign the value of value1 to result. Otherwise assign the value of value2 to result

 

 

 

```
class ConditionalDemo2 {

    public static void main(String[] args){
        int value1 = 1;
        int value2 = 2;
        int result;
        boolean someCondition = true;
        result = someCondition ? value1 : value2;

        System.out.println(result);
    }
}
```

 

The Type Comparison Operator instanceof

 

 

instanceof 使用来使用判断一个实例的class, subclass 和一个class 用来实现一个特定的接口

 

 

 

```
public class InstanceofDemo {
    public static void main(String[] args)
    {
        Parent parent1 = new Parent();
        Child child = new Child();
        System.out.println("parent1 instanceof Parent "+(parent1 instanceof Parent));
        System.out.println("parent1 instanceof Child "+(child instanceof Child));
        System.out.println("parent1 instanceof MyInterface "+(parent1 instanceof MyInterface));
        System.out.println("child instanceof Parent "+(child instanceof Parent));
        System.out.println("child instanceof Child "+(child instanceof Child));
        System.out.println("child instanceof MyInterface "+(child instanceof MyInterface));

    }
}

class Parent{}

class Child extends Parent implements MyInterface{}

interface MyInterface{}
```

 

2.2.2.3 Bitwise and Bit Shift Operators

| bitwise &  | bitwise AND operation                 |
| ---------- | ------------------------------------- |
| bitwise ^  | exclusive OR operation 位互斥或操作符 |
| bitwise \| | inclusive OR operation 位包换或操作符 |

 

```
class BitDemo {
    public static void main(String[] args) {
        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
    }
}
```

 

 

2.2.2.4 Summary of Operators

Simple Assignment Operator

 

 

| =    | Simple assignment operator |
| ---- | -------------------------- |
|      |                            |

 

 

 

 

Arthimetirc Operators

 

| +    | Additive operator(also used for String concatenation) |
| ---- | ----------------------------------------------------- |
| -    | Subtraction operator                                  |
| *    | Multiplication operator                               |
| /    | Division operator                                     |
| %    | Remainder operator                                    |

 

 

 

Unary Operators

 

 

| +    | Unary plus operator;indicates positive value |
| ---- | -------------------------------------------- |
| -    | Unary minus operator;negates an exprssion    |
| ++   | Increment operator                           |

 

 

Equality and Relational Operators

 

| ==   | Equal to                 |
| ---- | ------------------------ |
| !=   | Not equal to             |
| >    | Greater than             |
| >=   | Greater than or equal to |
| <    | Less than                |
| <=   | Less than or equal to    |

 

 

Conditional Operators

 

| &&   | Conditional-And |
| ---- | --------------- |
| \|\| | Conditional-OR  |
| ?:   | Ternary         |

 

 

Type Comparison Operator

 

| instanceof | Compares an object to a specified type |
| ---------- | -------------------------------------- |
|            |                                        |

 

 

 

Bitwise and Bit Shift Operators

 

| ~    | Unary bitwise complement |
| ---- | ------------------------ |
| <<   | Signed left shift        |
| >>   | Signed right shift       |
| >>>  | Unsigned right shift     |
| &    | Bitwise AND              |
| ^    | Bitwise exclusive OR     |
| \|   | Bitwise incluseive OR    |

 

 

 

 

2.2.3 Expressions, Statements, and Blocks

 

 

Expression

An expression is a construct made up of variables, operators, and method invocations, which are constructed according to the syntax of the language, that evaluates to a single value

 

Statements

Statements are roughly equivalent to sentences in natural languages

 

Block

block is a group of zero or more statements between balanced braces and can be used anywhere a single statement is allowed. 

 

 

 

 

2.2.4 Control Flow Statements

2.2.4.1 The if-then and if-then-else Statements

The if-then Statement

 

The if-then-else Statement

 

```
class IfElseDemo {
    public static void main(String[] args) {

        int testscore = 76;
        char grade;

        if (testscore >= 90) {
            grade = 'A';
        } else if (testscore >= 80) {
            grade = 'B';
        } else if (testscore >= 70) {
            grade = 'C';
        } else if (testscore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade = " + grade);
    }
}
```

一旦满足一个条件，剩余条件就不会被执行

 

 

2.2.4.2 The switch Statement

 

 

```
public class SwithDemoFallThrough
{
    public static void main(String[] args)
    {
        java.util.ArrayList<String> futureMonths = new java.util.ArrayList<String>();
        int month = 8;
        switch(month)
        {
            case 1: futureMonths.add("January");
            case 2: futureMonths.add("February");
            case 3: futureMonths.add("March");
            case 4: futureMonths.add("April");
            case 5: futureMonths.add("May");
            case 6: futureMonths.add("June");
            case 7: futureMonths.add("July");
            case 8: futureMonths.add("August");
            case 9: futureMonths.add("September");
            case 10: futureMonths.add("October");
            case 11: futureMonths.add("November");
            case 12: futureMonths.add("December");
            break;
            default: break;


        }

        if(futureMonths.isEmpty())
        {
            System.out.println("Invalid month number");
        }
        else
        {
            for(String monthName:futureMonths)
            {
                System.out.println(monthName);
            }
        }
        
    }
}
```

 

2.2.4.3 The while and do-while Statements

 

```
while(expression)
{
	statements
}
```

 

先执行一次，再判断

 

 

```
do
{
	statement(s)
}while(expression)
```

 

 

2.2.4.4 The for Statement

 

```
for(initilization;termination;increment)
{
	statement(s)
}
```

 

 

initilization:初始化表达式初始化循环，它在循环开始执行一次

termination:如果等于false,循环结束

increment: 每一次迭代后，increment 调用一次；可增，可减

 

 

more compact and easy to read

 

 

```
public class EnhancedForDemo {
    public static void main(String[] args) {
        int[] numbers={1,2,3,4,5,6,7,8};
        for(int item: numbers)
        {
            System.out.println(item);
        }
    }
}
```

 

 

 

2.2.4.5 Branching Statements

break

带标签的break

 

 

continue

 

 

```
public class BreakWithLabelDemo {
    public static void main(String[] args) {
        int[][] arrayOfInts=
                {
                        {32, 87, 3, 589 },
                        {1, 1076, 2000, 8},
                        {622, 127, 77, 955}
                };
        int searchfor =12;
        int i;
        int j=0;
        boolean foundIt= false;

        search:for(i=0;i<arrayOfInts.length;i++)
        {
            for(j=0;j<arrayOfInts[i].length;j++)
            {
                if(arrayOfInts[i][j]==searchfor)
                {
                    foundIt=true;
                    break search;
                }
            }
        }

        if(foundIt==true)
        {
            System.out.println("found it!");
        }
        else
        {
            System.out.println("not found it!");
        }
    }
}
```

 

 

 

 

 

 

 

 

 

```
public class ContinueWithLabelDemo
{
    public static void main(String[] args)
    {
        String searchMe = "Look for a substring in me";
        String substring = "sub";
        boolean foundIt = false;
        int max=searchMe.length()-substring.length();

        test:for(int i=0;i<=max;i++)
        {
            int n=substring.length();


            int j=i;
            int k=0;

            while(n-- !=0)
            {
                if(searchMe.charAt(j++)!=substring.charAt(k++))
                {
                    continue test;
                }
            }
            if(foundIt=true)
            {
                break test;
            }
        }
        if(foundIt)
        {
            System.out.println("found it");
        }
        else
        {
            System.out.println("Not found it");
        }

    }

}
```

 

return 

 

Questions and Exercises

 

The most basic control flow statement supported by java programming language is the if-then statement

 

The switch statements allows for any number of possible execution paths

 

The statements is similiar to the while statement, but evaluates its expression at the botto, of the loop

 

 

How do you write an infinite loop using the for statement?

for( ; ; )

{

}

How do you write an infinite loop using the while statement?

while(true)

{

}

 

 

 

2.2.4.6 Summary of Control Flow Statements

2.2.4.7 Questions and Exercises

 

 

2.3 Classes and Objects

2.3.1 Classes

2.3.1.1 Declaring Classes

class declaration

class body

 

```
class MyClass
{
//fields,constructor, and 
//method declarations
}
```

 

 

MyClass继承于MySuperClass 实现接口YourInterface

```
class MyClass extends MySuperClass implements YourInterface
{
	//field, constructor,  and 
    //method declarations
    
}
```

 

2.3.1.2 Declaring Member Variables

三种不同的变量：

- 类中的成员变量-叫做字段
- 方法或者代码中的变量叫本地变量
- 字段声明叫做参数

 

 

 

fields的例子：

 

 

```
public int cadence
public int gear
public int speed
```

 

 

Field declaration 由三个部分组成

修饰符:

Zero or more modifiers

字段类型

The field's type

字段名字

The field's name

 

Access Modifiers

 

public:

字段可以访问所有类

private:

字段仅能够再类中访问

 

 

```
public class PrivateBicycle
{
    private int cadence;
    private int gear;
    private int speed;

    public PrivateBicycle(int startCadence, int startSpeed, int startGear)
    {
        gear = startGear;
        speed = startSpeed;
        cadence = startCadence;

    }
    public int getCadence()
    {
        return cadence;
    }

    public int getGear()
    {
        return gear;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void setCadence(int newValue)
    {
        cadence = newValue;
    }
    public void setGear(int newValue)
    {
        gear = newValue;
    }
    public void applyBrake(int decrement)
    {
        speed-=decrement;
    }
    public void speedUp(int increment)
    {
        speed+=increment;
    }
}
```

 

2.3.1.3 Defining Methods

 

1. 修饰符 public , private
2. 返回type
3. 方法名
4. 参数
5. an exception list
6. 方法体

 

code conventation

方法名应该是一个或者多个名字开始于小写字母

紧接着名词，形容词

第二或者更往后的名次首字母应该大写

 

 

overloading

 

相同的方法名，不同的argument

 

 

```
public class DataArtist
{
	public void draw(String s){}
    public void draw(int i){}
    public void draw(double f){}
    public void draw(float f){}
}
```

 

2.3.1.4 Providing Constructors for Your Classes

no-argument constructor

构造器声明就像方法声明

 

```
Bicycle yourBike = new Bicycle()
```

 

调用无参构造方法

If another class cannot call a MyClass constructor, it cannot directly create MyClass objects

 

2.3.1.5 Passing Informaiton to a Method or a Constructor

 

```
public double computePayment(double loanAmt, double rate, double futureValue, int numPeriods)
{
	double interest= rate/100.0;
    double partial1= Math.pow((1+interest),-numPeriods);
    double denominator = (1-partial1)/interest;
    double answer = (-loanAmt/denominator)-((futureValue*partial1)/denominator);
    return  answer;

}
```

 

Parameter Types

 

参数可以是原始数据类型，也可以是引用数据类型。

 

 

```
public Polygon polygonFrom(Point[] corners)
{
	//method body goes there
}
```

 

 

Arbitrary Number of Arguments

```
 public Ploygon ploygonFrom(Point... corners)
    {
        int numberOfSides = corners.length;
        double squareOfSide1,lengthOfSides
        squareOfSide1 = (corners[1].x-corners[0].x)*(corners[1].x-corners[0])+(corners[1].y-corners[0].x)*(corners[1].y-corners[0].y)
        lengthOfSides=Math.sqrt(squareOfSide1);
        
    }
```

 

 

```
public PrintStream printf(Stirng format,Object... args)
```

 

Passing Reference Data Type Arguments

 

 

 

2.3.2 Objects

2.3.2.1 Creating Object

 

类为对象提供蓝图

 

从类中创造对象

 

 

```
Point originOne = new Point(20,30);
```

 

Declaration: type and name

Instantiation: new 是创造对象的Java operator

Initialization: constructor用于初始化new object

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmlwdlirj305601y748.jpg)

 

 

 

 

 

引用不一定分配一个variable ,也可以是一个表达式

 

```
int height = new Rectangle().height;
```

 

 

Initializing an Object

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmltszrnj30eu08yq3n.jpg)

 

 

 

```
Rectangle rectOne = new Rectangle(originOne,100,200)
```

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmlrqop6j30ic0f4q4r.jpg)

2.3.2.2 Using Objects

 

Referencing an Object's Fields

 

object reference 

rectone.width

 

 

expression

int height = new Rectangle().length;

 

 

Calling an Object's methods

 

 

```
new Rectangle(100,50).getArea()
```

 

 

 

 

The Garbage Collector

 

garbage collection JAVA运行环境会自动删除不需要使用的对象。

java进行周期性的回收

 

当变量超出范围时，在变量引用会被删除。或者设置变量为special value null

 

 

2.3.3 More on Classes

- Returning values from methods
- The this key word
- Class vs. instance members
- Access control

2.3.3.1 Returning a Value from a Method

java在三种情况下停止：

1. 运行完所有方法
2. 抵达return 语句
3. 抛出异常

 

 

什么都不返回：

return;

 

Returning a Class or Interface

 

 

 

 

当一个方法用类作为返回值时，返回对象的类型要么是子类，或者是确切类。

e.g:

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmlo1o90j30se0fcgn2.jpg)

现在假设有一个方法返回Number

 

```
public Number returnANumber{
	...
}
```

 

这个returnANumber可以返回ImaginaryNumber，但是不可以返回Object

 

covariant return type

You can override a method and define it to return a subclass of the original method, like this:

 

```
public ImaginaryNumber returnANumber() {
    ...
}
```

 

2.3.3.2 Using the this Keyworkd

Using this with a Field

 

Each argument to the constructor shadows one of the object's fields 

 

```
public class Point {
    public int x = 0;
    public int y = 0;
        
    //constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

 

 

Using this with a Constructor

 

显式构造函数调用

 

```
public class Rectangle {
    private int x, y;
    private int width, height;
        
    public Rectangle() {
        this(0, 0, 1, 1);
    }
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    ...
}
```

 

 

 

2.3.3.3 Controlling Access to Members of a Class

 

 

Package Two是Package One的子类

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmlkoi7gj30vq0a0dhv.jpg)

 

Visibility

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmliirl5j30lu0biq41.jpg)

 

 

 

Access Levels

 

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrmlfmsztj30lm0bkgmq.jpg)

 

 

public 是所有包都可见；

protected 是不同包非子类不可见;

no modifie同包可见；

private仅仅同类可见。

 

 

 

2.3.3.4 Understanding Class Members

Class Variables

 

静态变量中，类变量可以通过类名直接访问，也可以通过实例对象访问，但是一般很少使用实例对象访问。因为这无法表示他们是类变量。

 

Class Methods

 

```
public static int getNumberOfBicycles
{
	return numberOfBicycles;
}
```

tips:

 

- 实例方法可以直接访问实例方法和实例变量

 

 

- 实例方法可以直接访问类变量和类方法；

 

 

- 类方法可以直接访问类方法和类变量；

 

- 类方法不能直接访问实例变量和实例方法，他们必须使用一个对象引用。也不能使用this关键字。因为没有this所指引的实例。

 

 

Constants

 

 

 

```
static final double PI = 3.141592653589793;
```

If the name is composed of more than one word, the words are separated by an underscore (_).

 

 

The Bicyle Class

 

```
public class Bicycle
{
    public static void main(String[] args) {
        System.out.println(Bicycle.getNumberOfBicycle());
    }



    private int cadence;
    private int gear;
    private static int numberOfBicycle=2;

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public static int getNumberOfBicycle() {
        return numberOfBicycle;
    }

    public static void setNumberOfBicycle(int numberOfBicycle) {

        Bicycle.numberOfBicycle = numberOfBicycle;
    }
}
```

 

 

 

2.3.3.5 Initializing Fields

 

 

```
public class BedAndBreakfast {
    public static int capacity=10;
    private boolean full=false;
}
```

 

 

Static Initialization Blocks

 

A static initialization block is a normal block of code enclosed in braces{}, and preceded by the static keyword

 

 

```
static
{
        // whatever code is needed for initialization goes here      
}
```

 

 

```
class Whatever
{
    public static varType myVar=initializeClassVariable();
    
    private static varType initializeClassVariable()
    {
        //initialization code goes here
    }
}
```

 

The advantage of private static methods is that they can be reused later if you need to reinitialize the class variable.

 

 

Initializing Instance Members

 

 

Initializer blocks for instance variables look just like static initializer blocks, but without the static keyword

 

 

 

```
{
    // whatever code is needed for initialization goes here
}
```

 

 

 

```
class Whatever
{
    private varType myVar=initializeInstanceVariable();
    protected final varType initializeInstanceVariable()
    {
        // initialization code goes here
    }
}
```

 

 

 

 

 

2.3.3.6 Summary of Creating and Using Classes and Objects

通过在类声明中使用一个static keyword声明类变量或者类方法。如果一个成员变量没有声明static,则隐示的作为成员变量。类变量可以被类实例和类名字访问。

 

 

 

 

You can explicitly drop a reference by setting the variable holding the reference to null.

 

 

2.3.4 Nested Classes

 

 

嵌套类

static nested classes: 静态嵌套类

non-static nested classes:inner classes: 内部类 

```
public class OuterClass {
    ...
    class NestedClass
    {
        ...
    }
}
```

 

 

 

```
class OuterClass
{
    ...
    static class StaticNestedClass
    {
        ...
    }
    class innerClass
    {
        ...
    }
}
```

 

 

非静态类嵌套类可以访问封闭类的其他成员变量，即使他们被声明为私有

 

静态嵌套类不能访问封闭类的其他成员

 

嵌套类可以声明为private ,public , protected,package private

 

(请记住，外部类只能声明为public or package private)

 

 

 

 

```
OuterClass.StaticNestedClass
```

 

创建一个静态嵌套类对象

 

```
OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass
```

 

 

非静态类（内部类）

 

 

```
class OuterClass
{
	...
    class InnerClass
    {
  	...    
    }
    
}
```

 

 

 

为了实例化内部类，应该先实例化外部类

 

```
OuterClass.InnerClass innerObject=  outerObject.new InnerClass();
```

 

 

 

Shadowing

 

 

```
public class ShowTest
{
	public int x=0;
    class FirstLevel
    {
    	public int x=1;
        void methodInFirstLevel
        {
			System.out.println(x);
            System.out.println(this.x);
            System.out.println(ShowTest.this.x);
        }
    }
    public static void main(String args)
    {
    	ShadowTest st = new ShadowTest();
        ShadowTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
```

 

 

 

output:

 

```
x = 23
this.x = 1
ShadowTest.this.x = 0
```

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

2.3.4.1 Inner Class Example

 

 

```
public class DataStructure {
    //create an array
    private final static int SIZE =15;
    private int[] arrayOFInts = new int[SIZE];

    public void printEven()
    {
        DataStructureIterator iterator = this.new EvenIterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next()+" ");
        }
        System.out.println();
    }

    interface DataStructureIterator extends java.util.Iterator<Integer>{}

    private class EvenIterator implements DataStructureIterator
    {
        private int nextIndex =0;

        public boolean hasNext()
        {
            return (nextIndex<=SIZE-1);
        }

        public Integer next()
        {
            Integer retValue=Integer.valueOf(arrayOFInts[nextIndex]);

            nextIndex+=2;
            return retValue;

        }
    }

    public static void main(String[] args) {
        DataStructure ds = new DataStructure();
        ds.printEven();
    }

}
```

The EvenIterator实现DataStructureIterator 接口

 

 

 

 

 

 

2.3.4.2 Local Classes

 

 

 

 

 

 

 

2.3.4.3 Anonymous Classes

 

2.3.4.4 Lambda Express

 

 

 

2.3.5 Enum Types

 

2.4 Annotation

2.5Interfaces and Inheritanc

2.6Numbers and String

2.7Generic

2.8Packages

 

 

Essential Java Classes