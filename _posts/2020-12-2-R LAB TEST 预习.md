---
layout: post
author: LIU,HONGYANG
tags: [R]
---



### 1.Data  Camp视频笔记



#### 1.1 data importing 

文件的基本类型5 types

flat files, excel file, statictical software, databases, data from the web



```{r}
read.table("states.csv", header =TRUE, spe="," ,stringsAsFactors=FALSE )
```



如果文件名和路径不在一起：

>  file:

```R
path <- file.path("~","datasets","states.csv")
read.table(path,header=TRUE,spe=",",stringsAsFactors=FALSE)
```



> header:

 ![image-20201202111203030](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9c7h6f5zj31000nk7e0.jpg)



> sep: field separator



import string as categorical variables?

![image-20201202111255451](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9c8fsdbmj30wy0k046v.jpg)







> read.csv()

![image-20201202111457658](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9caj9lzaj31cg0r67ik.jpg)

>  Tab-delimited files: 

![image-20201202111621228](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9cbyq3phj31ci0r616c.jpg)





### 2. Lectures 5

If statements or switch

```R
for(i in 1:10) print(i*i) 
```



```R
ages<-c(12,18,32,2,4)
names(ages)<-c('Jane','Ed','Bob','Joe','Liz')
for(person in names(ages)){message(person,"is ",ages[person]," years old")}
Janeis 12 years old
Edis 18 years old
Bobis 32 years old
Joeis 2 years old
Lizis 4 years old
```

#### 2.1 apply

> apply

- apply() function is the base function.
- row/column operation, – 1 for row wise operation, 2 for column wise operation

what is the differenct between apply, lapply, sapply, tapply?



> sapply(li, fct)



input: vector, array



> lapply 

output: lists or data frames

apply operates on list and return list object



> tapply



is a very powerful function that lets you break a vector into pieces and then apply some function to each of the pieces. 



> mapply

'multivariate ' apply 



#### 2.2 seq(), rnorm(), is.na(), na.rm(), anyNA()



rnorm() randomly number generation



### 3. Lecture 6



#### 3.1 importing data in R

> scan()

```R
inp<-scan("input.dat",list("",0,0))
```



> read.table()



![image-20201202160155023](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9kl4rukdj31620m2jwj.jpg)

```R
file<-read.table("exercise1.dat",skip=1)
```



> read.delim() and read.delim2()



intended for reading TAB sparated files



>  Reading excel files:

read.delim() or read.csv()



> Binary format

save(), save.image(), serialize()



> File connections

file()



```R
con<-file("foo.txt")
open(con,"r")
data<-read.csv(con)
close(con)
```



#### 3.2 Exporting data

R objects can be exported to a text file using the `cat()` function

```R
cat(x, file="",sep="",fill=FALSE,labels=NULL,append=FALSE)
```



> write()



![image-20201202172408270](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9myncet8j318s0o641s.jpg)

![image-20201202172528040](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9n00z934j31840oggrb.jpg)

### 4. Lecture 6 dply R



```R
install.packages("dplyr")
library(dplyr)
```



references:

参考1



### 5.Lecture 6 Cleaning 









### 6. dplyr package



![image-20201202190409148](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9pup1xonj30uo0gawox.jpg)



> tidyverse



```
install.packages("tidyverse")

```



`%>%`: piple operator



`na.omit()`: handling null





![Screenshot 2020-12-02 at 21.01.24](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9t9mv5tfj31kc0s41kx.jpg)





![image-20201202205847901](https://tva1.sinaimg.cn/large/0081Kckwgy1gl9t5zwpwgj31hi0pcarw.jpg)

