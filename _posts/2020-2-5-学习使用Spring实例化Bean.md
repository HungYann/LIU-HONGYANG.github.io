##学习使用Spring实例化Bean

### 1.通过默认构造方法实例化Bean

创建代码

```{}
package com.imooc.spring.ioc.class05;

public class Bean1 {
    public Bean1() {
        System.out.println("Bean1.Bean1");
    }
}
```


```{}
 <bean class="com.imooc.spring.ioc.class05.Bean1" name="bean1_1,bean1_2" id ="bean1"></bean>
```


2.通过静态方法实例化Bean

创建代码

Bean2.java

```{}
package com.imooc.spring.ioc.class05;

public class Bean2 {
    public Bean2() {
        System.out.println("Bean2.Bean2");
    }
}

```

Bean2Factory.java
 
```{}
public class Bean2Factory {
        public static Bean2 getBean2()
        {
            return new Bean2();
        }
}

``` 
为了调用此方法：

相当于写出如下公式：

```{java}
Bean2 bean2 = Bean2Factory.getBean2();
```

在spring.xml中配置如下：


```
<bean class="com.imooc.spring.ioc.class05.Bean2Factory" factory-method="getBean2" id="bean2"></bean>

<alias name="bean1" alias="bean1_3"/>
```

3.通过实例工厂方法实例化Bean

创建代码：

Bean3.java

```{java}

package com.imooc.spring.ioc.class05;

public class Bean3 {
    public Bean3() {
        System.out.println("Bean3.Bean3");
    }
}

```

Bean3Factory.java

```{}

package com.imooc.spring.ioc.class05;

public class Bean3Factory {
        public Bean3 getBean3()
        {
            return new Bean3();
        }
}

```

为了调用此方法：

相当于写出如下公式：

```{java}
Bean3Factory bean3Factory = new Bean3Factory();

Bean3 bean3 = bean3Factory.getBean3();

```

在spring.xml中如下配置：

```{java}
 <bean class="com.imooc.spring.ioc.class05.Bean3Factory" id="bean3Factory"/>
 
 <bean class="com.imooc.spring.ioc.class05.Bean3" factory-bean="bean3Factory"
            factory-method="getBean3" id="bean3"/>

```


最后测试文件如下：

```{java}
package com.imooc.spring.ioc.class05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Class005Test {
        @Test
        public void test()
        {
                ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
                Bean1 bean1 = context.getBean("bean1",Bean1.class);

                System.out.println(bean1);

//                Bean2 bean2 = Bean2Factory.getBean2();

                Bean2 bean2 = context.getBean("bean2",Bean2.class);

                System.out.println(bean2);

//                Bean3Factory bean3Factory = new Bean3Factory();
//                Bean3 bean3 = bean3Factory.getBean3();


                Bean3 bean3 = context.getBean("bean3",Bean3.class);

                System.out.println(bean3);


                Bean1 bean1_1 = context.getBean("bean1_1",Bean1.class);
                Bean1 bean1_2 = context.getBean("bean1_2",Bean1.class);
                Bean1 bean1_3  = context.getBean("bean1_3",Bean1.class);
                System.out.println(bean1_1);
                System.out.println(bean1_2);
                System.out.println(bean1_3);


        }

}


```


spring.xml文件


```{}
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
   http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
   http://www.springframework.org/schema/aop
   http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
   http://www.springframework.org/schema/tx
   http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
   http://www.springframework.org/schema/context
   http://www.springframework.org/schema/context/spring-context-3.0.xsd">

        <bean class="com.imooc.spring.ioc.class05.Bean1" name="bean1_1,bean1_2" id ="bean1"></bean>
        <alias name="bean1" alias="bean1_3"/>
    
        <bean class="com.imooc.spring.ioc.class05.Bean2Factory" factory-method="getBean2" id="bean2"></bean>

        <bean class="com.imooc.spring.ioc.class05.Bean3Factory" id="bean3Factory"/>
        <bean class="com.imooc.spring.ioc.class05.Bean3" factory-bean="bean3Factory"
            factory-method="getBean3" id="bean3"/>


</beans>
```

