---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



### Array转化成ArrayList



三种方法：

第一种：使用Arrays.asList(array); 方法，将数组转化成List链表

第二种：使用集合中的方法Collections.addAll(); 将数组加入到集合中

第三种：迭代遍历



```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class demo25 {
    public static void main(String[] args) {
        String[] array = {"a","b","c","d","e"};
        //method 1
        List<String> list = Arrays.asList(array);
        System.out.println(list);

        //method 2
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1,array);
        System.out.println(list1);

        //method 3
        List<String> list2 = new ArrayList<>();
        for(String text: array){
                list2.add(text);
        }

        System.out.println(list2);



    }
}

```





## ArrayList转化为Array



使用toArray()方法，可以直接将ArrayList转化成array



```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class demo25 {
    public static void main(String[] args) {

        //arraylist -> array

        List<String> aList = new ArrayList<String>();
        aList.add("Nathan");
        aList.add("John");
        aList.add("Susan");
        aList.add("Betty");
        aList.add("Peter");
        Object[] objArr = aList.toArray();
        System.out.println("The array elment are:");
        System.out.println(Arrays.toString(objArr));
    }
}

```







https://www.tutorialspoint.com/Conversion-of-Array-To-ArrayList-in-Java

https://www.tutorialspoint.com/copy-all-elements-of-arraylist-to-an-object-array-in-java#:~:text=All%20the%20elements%20of%20an,toArray().