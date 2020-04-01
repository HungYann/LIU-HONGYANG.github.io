---
layout: post
author: LIU,HONGYANG
tags: [Python]
---



simplejson文件转化成json文件



在将simplejson转化成json文件真是走了无数坑，比如如下错误

遇到的有错误

```
JSONDecodeError: Expecting property name enclosed in double quotes: line 1 column 2 (char 1)
```



```
ValueError: Expected object or value
```



```
TypeError: Input string must be text, not bytes
```



最后解决方法十分简单



### 一、普通文本

比如json_data = "{'property': 'text'}"这样的，直接转换：

```python
import ast
import json
json_data = "{'property': 'text'}"
data = json.dumps(ast.literal_eval(json_data))
```



输出结果：

![image-20200331151156671](https://tva1.sinaimg.cn/large/00831rSTgy1gdd4pajfisj30em01uq2y.jpg)





### 二、simplejson文件



针对simplejson文件

```python
import simplejson as json
import ast

with open('places.simplejson') as f:
    data = f.readlines()
    for i in range(len(data)):
        data[i]= ast.literal_eval(data[i])
with open('places.json', 'w') as f:
    json.dump(data, f)
```

转换前：

![image-20200401141818386](https://tva1.sinaimg.cn/large/00831rSTgy1gde8tgnqmpj31i604qgnw.jpg)



转换后：

![Screenshot 2020-03-31 at 18.11.20](https://tva1.sinaimg.cn/large/00831rSTgy1gde8thxdyuj317m0rkqid.jpg)





### 三、验证

最后的结果是否正确，使用如下网站验证一下即可：

[jslint](https://jslint.com/)

结果如下：

![image-20200401141214970](https://tva1.sinaimg.cn/large/00831rSTgy1gde8tjzpzoj31yq0sswgm.jpg)





总之，是不是非常方便？但是成功是痛苦的，我也走了无数坑。



### References:



http://www.programmersought.com/article/8873495031/



http://zetcode.com/python/simplejson/



https://developer.rhino3d.com/guides/rhinopython/python-xml-json/



https://jslint.com/



https://blog.csdn.net/yatere/article/details/6606316