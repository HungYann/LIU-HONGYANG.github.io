---
layout: posts
author: LIU,HONGYANG
tags: [Machine Learning]
---





### pd.get_dumies(df):



```
#Formatting Categorical Variable
    
df = pd.get_dummies(df)
```





### 标签编码(Label Encoder)

 

![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190911112858347-261175133.png)

 

 

在本例中第一列是Country, 如果我们要运行任何模型， 数据中不能包含文本

所以要对文本进行处理

接下来，我们从sklearn库中导入LabelEncoder类

 

```
from sklearn.preprocessing import LabelEncoder

labelencoder = LableEncoder()

X[:, 0] = labelencoder.fit_transform(X[:, 0])
```





 

 

假设数据在X变量中，运行代码后，检查X的值，发现国家已经被1, 2, 3取代

 

![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190911113612024-248604902.png)

 

 

 

Label Encoder的问题：

我们将一组国家名称编码成数据，实际上是分类数据，数据与数据之间无任何关系。

而标签编码数据有顺序关系，0<1<2

 

因此，我们可以采用One Hot Encoder

 

**独热编码(One Hot Encoder)**

 

采用One Hot Encoder编码后，

数字将被0和1替换，

运行如下代码:

 

```
*from sklearn.preprocessing import OneHotEncoder*

*onehotencoder = OneHotEncoder(categorical_feature=0)*

*x = onehotencoder.fit_transform(x).toarray*
```



 

 

![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190911114402532-921928784.png)

 

 

 

如图所见，我们新增三个新列，每个国家那一列代表1，其它两项代表0. 例如第一列值为France, 具有France的列将变为1，其它两列为0；类似的，对于第一个列值为

Germany列， 'Germany'列将有'1', 其它两列将为0

 

### References:

机器学习中的Label Encoder和One Hot Encoder  [Accessed at 11 Septermber 2019] 

https://kknews.cc/code/kba3lvv.html