---
layout: post
author: LIU,HONGYANG
tags: [Scala]
---





```
1.map是一个高阶函数，因此也可以直接传入一个匿名函数，完成map

2.当遍历list时，参数类型是可以推断出来的，可以省略数据类型Int println(list.map(x)⇒x+1)

3.当传入的函数，只有单个参数时，可以省略括号 println(list.map(x⇒x+1))

4.如果变量只在⇒右边出现一次，可以用_来代替 println(list.map(_+1))
```







结果样例：

```
Map(hw.a.yximgs.com -> List(3, 5), hw6.a.yximgs.com -> List(3, 5), hw6.a.kwimgs.com -> List(3, 5), hw2.a.kwimgs.com -> List(3, 5))
```



代码：



```scala
val domains = sampler.getStringList("domains").asScala.map { e =>
  val ss = e.split(",",2).map(_.trim)
  ss.last -> ss.head.split("/", 2).map(_.trim.toInt).toList
}.toMap
SamplerConf(sampler.getInt("domain-idx"), sampler.getInt("status-idx"), domains)

```



自己演示修改代码：

```scala
package Test
object Demo04 {
  def main(args: Array[String]): Unit = {
    val my: List[String] = List("hw6.a.yximgs.com = 3/5", "hw.a.yximgs.com = 3/5")
    val res = my.map {
      e =>val ss = e.split("=", 2).map(_.trim)
        ss(0) -> ss(1).split("/", 2).map(_.trim.toInt).toList
    }.toMap
    print(res)
  }
}

```



map的作用：

```scala
package com.baishan.scala.demo37

object demo37 {
  def main(args: Array[String]): Unit = {
        val list = List(3,5,7,9,10)
        //1.将list集合中的元素，依次遍历
        //2.将各个元素传递给multiple 函数=>新Int
        //3.将得到新Int，放入到一个新的集合并返回
        //4.因此multiple 函数调用3

        //原函数
        val list2 = list.map[Int](multiple)
        println(list2)
    
        //现函数
        val myList = MyList()
        println(myList.map(multiple))

  }

  def multiple(n:Int):Int={
     2*n
  }
}

//模拟map映射函数的机制，模拟实现
class MyList{
    val list1 = List(3,5,7,9)
    //新的集合
    var list2 = List[Int]()
    //写map
   def map(f:Int=>Int) ={
      for(item<-this.list1){
          list2 = list2 :+ f(item)
      }
      list2
   }
} 

object MyList{
  def apply(): MyList = new MyList()
}
```



对map函数里的内容进行大写转换

```scala
package com.baishan.scala.demo38

object demo38 {
  def main(args: Array[String]): Unit = {
      val names = List("Alice","Bob","Nick")

      def upper(x:String)={
          x.toUpperCase
      }
      //指定返回值类型
      val NAME = names.map[String](upper)

      println(NAME)
  }
}
```

