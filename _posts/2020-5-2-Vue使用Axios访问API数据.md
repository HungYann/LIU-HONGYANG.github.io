---
layout: post
author: LIU,HONGYANG
tags: [Vue]
---



### 1.构建Vue的Axios项目



```html
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.15.2/axios.js"></script>
</head>
```





### 2.小案例



1.前端输入url

2.点击按钮访问url

3.局部返回数据



![image-20200502122010579](https://tva1.sinaimg.cn/large/007S8ZIlgy1gedzkjdho1j30c806g0t2.jpg)



> 双向数据绑定



```html
<div id="app">
 <input type="text" v-model="keyword" >
        输入内容:{{keyword}}
</div>
```

在vue中，使用v-model可以双向绑定数据，什么是双向数据绑定？

正常情况下，在vue js中，



```javascript
var vue = new Vue({
            el: '#app',
            data:{
                keyword:'',
                results:[]
            }
})
```



数据keyword是已经有值的：' ' ,那么在**输入内容:{{keyword}}**中显示的也应该是' ' ，可是由于使用了v-model，此时若用户自行改变keyword的值，那么data中的keyword中的值也会被改变，这就是双向数据的绑定。

换句话说，keyword数值已经不仅仅是只有一个数据源方向了，而是两个方向都可以改变，而成为了两个。



> Axios访问API数据



接下来我们使用axios取API中的数据，



```javascript
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.15.2/axios.js"></script>
```



思路是，

1.点击按钮事件

2.axios访问HTTP地址

3.拿到数据

4.再展示在前端页面上



```javascript
 <div id="app">
        <button v-on:click="searchKey">点击</button>
        访问的数据:
        <br>
        <li v-for="result in results">
            {{result.id}}---{{result.name}}
        </li>
</div>
```



```vue
<script>
        var vue = new Vue({
            el: '#app',
            data:{
                results:[]
            },
            methods: {
                searchKey: function(){
                        
                        axios.get("/list").then(response=>{
                        		this.results = response.data;
                        })
                }
            }
        })

</script>
```



`v-on:click="searchKey"`  绑定函数searchKey()方法， 在方法中，axios请求'/list'数值，其中`response.data` 就是取出的数据集，然后赋给resutls上，此时数据就到了data参数中

这时，我们只要使用`v-for`取出数据，展示在页面上即可。



```vue
 <li v-for="result in results">
            {{result.id}}---{{result.name}}
 </li>
```





![image-20200502123718153](https://tva1.sinaimg.cn/large/007S8ZIlgy1gee0289satj30b004wdg0.jpg)





### 3.代码

本例中全部代码如下：



> 前端index.html：

```html
<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.15.2/axios.js"></script>
</head>
<body>

<!--设计一个链接-->
<!--点击链接时，数据到后台访问，-->
<!--取出数据存储在data中-->



<div id="app">
        <input type="text" v-model="keyword" >
        输入内容:{{keyword}}
         <br>
        <button v-on:click="searchKey">点击</button>
        访问的数据:
        <br>
        <li v-for="result in results">
            {{result.id}}---{{result.name}}
        </li>
</div>




<script>
        var vue = new Vue({
            el: '#app',
            data:{
                keyword:'',
                results:[]
            },
            methods: {
                searchKey: function(){
                        var keyword = this.keyword;
                        // console.log(keyword)
                        axios.get("/"+keyword).then(response=>{
                            // console.log(response.data);

                            this.results = response.data;
                        })
                }
            }
        })

</script>

</body>
</html>
```



> 后端ContentController.java

```java
package com.hongyang.demo.controller;


import com.hongyang.demo.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContentController {

    @RequestMapping("/list")
    @ResponseBody
    public List<Student> show(){

        List<Student> list = new ArrayList<>();

        list.add(new Student(1,"张三"));
        list.add(new Student(2,"李四"));
        list.add(new Student(3,"王五"));

        return list;
    }

}

```



> POJO



```java
package com.hongyang.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
        int Id;
        String name;
}

```

