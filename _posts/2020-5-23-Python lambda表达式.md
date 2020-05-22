---
layout: post
author: LIU,HONGYANG
tags: [Python]
---



要说在python当中，最强大的数据运输库是什么，很多人会认为是pandas，确实是！

pandas底层使用c语言编写，处理大行数据是一定快过excel的



那么python当中，最好用的函数式方程是什么？



个人认为是lambda



lambda与pandas 结合，足以撑起数据科学一片天！



##### 更改数据值:

```python
f=[1,2,4,16,256]
list(map(lambda x: x + 10 if x> 10 else x, f))
```



![image-20200523001612971](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf1onmccuqj309401umx2.jpg)





##### 去除异常值



> 去除前

![image-20200523001743643](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf1op7auurj316c0bgmyw.jpg)





>  去除后

```python
df['Annual% Change'] = list(map(lambda x: x[:-1],df['Annual% Change']))
df.head()
```

![image-20200523001849585](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf1oqbrpctj30ty088q3w.jpg)





##### 或者使用apply函数

```python
df['Year'].apply(lambda x: x+10 if x>10 else x+2)
```

![image-20200523002247791](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf1ougnsicj30ri07gt9g.jpg)