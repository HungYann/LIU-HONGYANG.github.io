---
layout: post
author: LIU,HONGYANG
tags: [Idea]
---







### InteliJ idea安装lombok

由于在项目开发过程中，写getter和setter一定觉得很烦，尤其是在修改属性之后，那么如何解决这个问题呢？



那就是lombok，lombok是intellij上的一个插件，它能够自动通过注解生成相应的方法



首先使用command+shift+A找到搜索栏，搜索plugins插件，下载lombok.安装完成后，重启idea



![](https://tva1.sinaimg.cn/large/0082zybpgy1gbq3zr6w1oj31070u0wme.jpg)



接下来，找到preferences中的Build, Execution, Deployment设置，勾选Enable annotation processin。

重启，即可生效。



![](https://tva1.sinaimg.cn/large/0082zybpgy1gbq429itknj31660u0wms.jpg)



最后，加上@Data 和 @AllArgsConstructor注解，即可使用，自动替代getter和setter方法，是不是非常简单，哈哈哈

```java
package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private long id;
    private String name;
    private int age;

}
```

