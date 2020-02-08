#### Spring 框架两大核心机制

- Ioc(控制反转) / DI（依赖注入）
- Aop（面向切面编程）

Spring 是一个企业级开发框架，是软件设计层面的框架， 优势在于应用程序分层。



**控制反转：**

在传统的开发程序中，需要调用对象时，通常由调用者来创建被调用者的实例，即对象是由调用者主动new出来的。

但在Spring框架中，创建对象的工作不再由调用者来完成，而是交给IOC容器来创建，再推送给调用者，整个流程发生了反转，所以是控制反转。



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>aispring</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.11.RELEASE</version>
        </dependency>

        <!--简化实体类开发-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.6</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>

</project>

```



- 创建实体类

```java
package com.southwind.entity;

import lombok.Data;

@Data
public class Student {
    private long id;
    private String name;
    private int age;

}

```



- 传统的方式开发，new Student



```java
Student student = new Student();
student.setId(1L);
student.setName("张三");
student.setAge(22);
```



- 通过IOC创建对象，在配置文件中配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="student" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="张三"></property>
        <property name="age" value="22"></property>

    </bean>

</beans>
```



- 从IOC中获取对象, 通过Id获取

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
Student student = (Student)applicationContext.getBean("student");
System.out.println(student);
```

- 通过`Bean`标签来完成对象的管理
  - `id`:对象名
  - `class`:对象的全类名。（所有交给IOC容器来管理的类必须有参构造，因为Spring底层是通过反射机制创建对象）

- 对象的成员变量通过`property`标签完成赋值
  - `name`:成员变量名
  - `value`:成员变量值（基本数据类型String可以直接赋值，如果是其它引用类型，不能通过value来赋值 ）
  - `ref`:将IOC中的另一个Bean赋给当前的成员变量（DI）



```xml
  <bean id="student" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="张三"></property>
        <property name="age" value="22"></property>
        <property name="address" ref="addresss"></property>
    </bean>

    <bean id="addresss" class="com.southwind.entity.Address">
        <property name="id" value="1"></property>
        <property name="name" value="科技路"></property>
    </bean>

```



### IOC底层原理



- 读取配置文件，解析XML
- 通过反射机制实例化配置文件中所配置的所有bean



```java
package com.southwind.ioc;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.lang.annotation.Documented;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private Map<String, Object> ioc = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext(String path) {

        Document document = null;


        try {
            SAXReader reader = new SAXReader();
            document = reader.read(path);
            System.out.println(document);

            Element root = document.getRootElement();

            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                //通过反射机制获取对象
                Class clazz = Class.forName(className);
                //获取无参构造函数，创建目标对象
                Constructor constructor = clazz.getConstructor();

                Object object = constructor.newInstance();
                //给目标对象赋值

                Iterator<Element> elementItr = element.elementIterator();
                while (elementItr.hasNext()) {

                    Element property = elementItr.next();
                    String name = property.attributeValue("name");
                    String valueStr = property.attributeValue("value");
                    String ref = property.attributeValue("ref");

                    if(ref==null){
                        Field filed = clazz.getDeclaredField(name);
                        String methodName = "set" + name.substring(0, 1).toUpperCase().toString() + name.substring(1);

                        Method method = clazz.getDeclaredMethod(methodName, filed.getType());
                        Object value = null;

                        //根据成员变量的数据类型，将value转换
                        if (filed.getType().getName() == "long") {
                            value = Long.parseLong(valueStr);
                        }

                        if (filed.getType().getName() == "java.lang.String") {
                            value = valueStr;
                        }

                        if (filed.getType().getName() == "int") {
                            value = Integer.parseInt(valueStr);
                        }

                        method.invoke(object, value);
                    }
                    ioc.put(id, object);
                }
            }
            Object obj1 = ioc.get("address");
            Object obj2 = ioc.get("student");

            System.out.println(obj1);
            System.out.println(obj2);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
        }


    }


    public Object getBean(String id) {
        return ioc.get(id);
    }
}

```



### 通过运行时类获取Bean



```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
Student student = (Student)applicationContext.getBean(Student.class);
System.out.println(student.toString());
```



这种方式存在一个问题，配置文件中一个数据类型只能有一个实例，否则抛出异常，因为没有唯一的bean



### 通过有参构造创建Bean



- 在实体类中创建对应的有参构造函数

- 配置文件

```xml
<bean id="student3" class="com.southwind.entity.Student">
  <constructor-arg name ="id" value="3"></constructor-arg>
  <constructor-arg name = "name" value="小明"></constructor-arg>
  <constructor-arg name="age" value="18"></constructor-arg>
  <constructor-arg name="address" ref="addresss"></constructor-arg>
</bean>
```



 ```xml
 <bean id="student3" class="com.southwind.entity.Student">
        <constructor-arg index = "0"  name ="id" value="3"></constructor-arg>
        <constructor-arg index="1" name = "name" value="小明"></constructor-arg>
        <constructor-arg index="2" name="age" value="18"></constructor-arg>
        <constructor-arg index="3" name="address" ref="addresss"></constructor-arg>
 </bean>
 ```



### 给Bean注入集合

```xml
<bean id="student" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="李四"></property>
        <property name="age" value="22"></property>

        <property name="address" >
            <list>
                <ref bean = "address1"></ref>
                <ref bean=  "address2"></ref>
            </list>
        </property>
</bean>

<bean id="address1" class="com.southwind.entity.Address">
  <property name="id" value="1"></property>
  <property name="name" value="科技路"></property>
</bean>

<bean id="address2" class="com.southwind.entity.Address">
  <property name="id" value="2"></property>
  <property name="name" value="高兴去"></property>
</bean>
```



### scope作用域

Spring管理的bean是根据scope来生成的，表示bean的作用域，共四种, 默认是singleton。

- singleton单例，表示通过Ioc容器获取bean是唯一的。
- prototype原型，表示通过Ioc容器获取bean是不同的
- request请求，表示在HTTP请求内有效
- session回话，表示在一个用户回话内有效。



request和session只适用于web项目，单例和原型使用较多。

prototype模式当业务代码获取Ioc容器bean时，Spring才去调用无参构造创建对应的bean, 节省空间

singleton模式无论业务代码是否获取IOC容器中的bean, Spring在加载spring.xml时就会创建bean，浪费空间



### Spring继承



与Java的继承不同，Java是类层面的继承，子类可以继承父类的内部结构信息，Spring是对象层面的继承，子对象可以继承父对象的属性值。



```xml
   
<bean id="student2" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="张三"></property>
        <property name="age" value="22"></property>
        <property name="address">
                <list>
                    <ref bean="address1"></ref>
                    <ref bean="address2"></ref>
                </list>
        </property>
    </bean>

		<bean id="address1" class="com.southwind.entity.Address">
        <property name="id" value="1"></property>
        <property name="name" value="科技路"></property>
    </bean>

    <bean id="address2" class="com.southwind.entity.Address">
        <property name="id" value="2"></property>
        <property name="name" value="高兴去"></property>

    </bean>

    <bean id="stu" class="com.southwind.entity.Student" parent="student2"> </bean>
```

Spring的继承关注点在于具体的对象属性，而不在于类，即不同的两个类的实例化对象可以完成继承，前提是子对象必须包含父对象的所有属性，同时可以在此基础上添加其它属性。

Java继承是继承整个类的内部结构，包括属性和方法。

- 子类即使不扩充父类，也能维持父类的操作。

- 子类实际上是将父类定义的更加具体化的一种手段。父类表示的范围大，而之类表示的范围小。



### Spring的依赖



与继承类似，依赖也是描述bean与bean之间的关系，配置依赖之后，被依赖的bean一定是先创建的，再创建依赖的bean. 比如， A依赖 B, 则B一定先创建。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    <bean id="student" class="com.southwind.entity.Student" depends-on="user"></bean>
    <bean id="user" class="com.southwind.entity.User"></bean>
</beans>
```



### Spring的p命令空间

p命令空间是对IOC/DI的简化操作，使用命令空间可以更加方便的完成bean的配置以及bean之间的依赖注入关系。



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="student" class="com.southwind.entity.Student" p:id="1" p:name="张三" p:age="22" p:address-ref="address">
    </bean>

    <bean id="address" class="com.southwind.entity.Address" p:id="2" p:name="科技路">
    </bean>
</beans>
```



### Spring的工厂方法

IOC通过工厂模式创建Bean的方式有两种，

- 静态工厂方法
- 实例工厂方法



> 静态工厂方法

```java
package com.southwind.entity;

public class Car {
    private long id;
    private String name;

    public Car(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="car" class="com.southwind.factory.StaticCarFactory" factory-method="getCar">
            <constructor-arg value="1"></constructor-arg>
    </bean>
</beans>
```



```java
package com.southwind.test;

import com.southwind.entity.Car;
import com.southwind.factory.StaticCarFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factory.xml");
        Car car = (Car) applicationContext.getBean("car");

        System.out.println(car.toString());
    }

}

```

> 实例工厂方法

```java
package com.southwind.factory;

import com.southwind.entity.Car;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.HashMap;
import java.util.Map;

public class InstanceCarFactory {

    private Map<Long, Car> carMap;

    public InstanceCarFactory() {
        carMap= new HashMap<Long, Car>();
        carMap.put(1L,new Car(1L,"宝马"));
        carMap.put(2L,new Car(2L,"奔驰"));

    }

    public Car getCar(long id){
        return carMap.get(id);
    }

}
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">


    <bean id="carFactory" class="com.southwind.factory.InstanceCarFactory"></bean>

    <bean id="car2" factory-bean="carFactory" factory-method="getCar">
        <constructor-arg value="1"></constructor-arg>
    </bean>

</beans>
```



```java
package com.southwind.test;

import com.southwind.entity.Car;
import com.southwind.factory.StaticCarFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factory.xml");
        Car car = (Car) applicationContext.getBean("car2");

        System.out.println(car.toString());
    }

}
```



### IOC 自动装载

IOC负责创建对象，DI负责完成对象的依赖注入，通过配置property的ref属性

同时Spring提供了另外一种更加简便的依赖注入方式，不需要配置property, ioc会自动选择bean注入



自动装在有两种方式：

- byName: 通过属性名自动装载
- byType：通过属性的数据类型自动装载

> byName

```xml
<bean id="car" class="com.southwind.entity.Car">
  <property name="id" value="1"></property>
  <property name="name" value="宝马"></property>
</bean>


<bean id="person" class="com.southwind.entity.Person" autowire="byType">
  <property name="id" value="11"></property>
  <property name="name" value="张三"></property>
</bean>
```



问题：当有两个相同bean类型时，报错

> byType

```xml
<bean id="car22" class="com.southwind.entity.Car">
  <property name="id" value="1"></property>
  <property name="name" value="宝马"></property>
</bean>


<bean id="person" class="com.southwind.entity.Person" autowire="byType">
  <property name="id" value="11"></property>
  <property name="name" value="张三"></property>
</bean>
```





