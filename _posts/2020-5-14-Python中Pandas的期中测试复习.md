---

layout: post
author: LIU,HONGYANG
tags: [Python]
---



### Creating, Reading and Writing



##### 创建一个DataFrame



```python
import pandas as pd
df = pd.DataFrame({'Yes':[2,3],'No':[5,6]})
```



```python
data = {"Apples":[35,41],"Bananas":[21,34]}
fruit_sales = pd.DataFrame(data,columns={"Apples","Bananas"},index=['2017 Sales','2018 Sales'])
```



##### 为DataFrame指定Index



Series没有Column名称

```python
df = pd.DataFrame({'Bob':['I liked it.', 'It was awful.'],'Sue':['Pretty good.', 'Bland.']},index=['Product A','Product B'])
```



```python
df=pd.Series([30, 35, 40], index=['2015 Sales', '2016 Sales', '2017 Sales'], name='Product A')
```



##### 查看DataFrame的属性



```python
df.shape
df.head()
```



##### 设置显示行数



```
pd.set_option('max_rows', 5)
```





### Indexing, Selecting & Assigning



##### 选择指定列



```python
df.country
df['country']
df['country'][0]
```



##### 选择指定行或者列

**loc**和**iloc**的特别之处在于，都是行优先，列其次





### Mid-term考点



##### missing data



- 删除dropna()
- 填充fillna()
- 替换replace()
- 数值方法填充interpolate()



>  查看缺失值个数

```python
df.isnull().sum()
```



>  查看非缺失值个数

```
df.notnull().sum()
df.notnull().sum(axis=0)
```

- axis=0：计算行个数

- axis=1:   计算列个数



> 查看City列缺失的文本

```
df[df.City.isnull()]
```



>  删除缺失值

```
df.dropna(how='any').shape
```



> 删除City或Colors Reported列中有缺失值的数

```
df.dropna(subset=['City','Colors Reported']).shape
```



>  删除City且Colors Reported列中有缺失值的数

```
df.dropna(subset=['City','Colors Reported'],how='all').shape
```



> 查看属性值

```
df.State.value_counts()
df.City.value_counts(dropna=False)
```



> 填充缺失值

```
df['Colors Reported'].fillna(value='Red',inplace=True)
```

```
df["Shape Reported"].fillna( method ='ffill',inplace = True) 
```



```
value : Static, dictionary, array, series or dataframe to fill instead of NaN.

method : Method is used if user doesn’t pass any value. Pandas has different 
methods like bfill, backfill or ffill which fills the place with value in the Forward index or Previous/Back respectively.

axis: axis takes int or string value for rows/columns. Input can be 0 or 1 for Integer and ‘index’ or ‘columns’ for String

inplace: It is a boolean which makes the changes in data frame itself if True.

limit : This is an integer value which specifies maximum number of consequetive 
forward/backward NaN value fills.

downcast : It takes a dict which specifies what dtype to downcast to which one. Like Float64 to int64.

**kwargs : Any other Keyword arguments
```



参考链接: https://www.geeksforgeeks.org/working-with-missing-data-in-pandas/



> 替换

```
df['Colors Reported']=df['Colors Reported'].replace(to_replace ="Red", 
                 value ="Omega")

```



> 使用参数填充

```
df2.interpolate(method='values',limit_direction ='both')
```



参考：https://www.geeksforgeeks.org/python-pandas-dataframe-interpolate/



##### Noisy Value



>  重命名



```python
df.rename(columns={'DURATION':'TIME'})
```



> 填充0



```
df.fillna(0)
```

其中value参数省略



> 填充均值



```
df2.fillna(df2['B'].mean())
```





![image-20200515132209345](https://tva1.sinaimg.cn/large/007S8ZIlgy1get2ewhx8kj309c0ao74l.jpg)





![image-20200515132147559](https://tva1.sinaimg.cn/large/007S8ZIlgy1get2enmf1fj30la0aumxw.jpg)



> 删除所有缺失值行



```
df2.dropna()
```



> 查看帮助文档



```
?df2.dropna
```



> revome duplicate data



- 查看duplicate数量

  

```
df.duplicated().sum()
```



- 可视化duplicate值

```
df.loc[df.duplicated(),:]
df.loc[df.duplicated(keep='last'),:]
df.loc[df.duplicated(keep='first'),:]
```



- 删除重复值



```
df.drop_duplicates(keep='first').shape
df.drop_duplicates(keep='last').shape
df.drop_duplicates(keep=False).shape
```



- 仅仅考虑部分列

````
df.drop_duplicates(subset=['City','Colors Reported','Shape Reported']).shape
````





参考链接：

https://www.geeksforgeeks.org/python-pandas-dataframe-drop_duplicates/

https://www.youtube.com/watch?v=bFVMR1qfzXo



##### Inconsistency data



```
df=pd.read_csv('ufo.csv',dtype={'City':str})
df.dtypes
```





##### Dimensionality reduction



```
df.describe()
df.shape()
df.describe(exclude='number')
```





##### Normalization



 ```python
from sklearn import preprocessing
names = df2.columns
scaler = preprocessing.StandardScaler()
df3=scaler.fit_transform(df2)
scaled_df = pd.DataFrame(df3,columns=names)
 ```





```python
preprocessing.minmax_scale(df2,feature_range=(-1,1))
preprocessing.scale(df2)
```



![image-20200515152806294](https://tva1.sinaimg.cn/large/007S8ZIlgy1get61z2b6hj30q40r241n.jpg)



参考资料:https://morvanzhou.github.io/tutorials/machine-learning/sklearn/3-1-normalization/





##### Template



![image-20200515153634692](https://tva1.sinaimg.cn/large/007S8ZIlgy1get6as6y2yj316s0agaib.jpg)



![image-20200515161751187](https://tva1.sinaimg.cn/large/007S8ZIlgy1get7hps0o7j31100csjvv.jpg)