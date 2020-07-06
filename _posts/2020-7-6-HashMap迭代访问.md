---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



![image-20200706140835133](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggh7zbsx8bj31xk0u0k9v.jpg)





HashMap的迭代访问取出其中的元素，



```java
Iterator iterator = HashMap.entrySet().iterator();

while(iterator.hasNext()){
  HashMap.Entry entry = (Map.Entry)iterator.next();

  int key = (int)entry.getKey();
  int value = (int)entry.getValue();

  if(sum<=bound){
    ArrayList.add(sum);
  }
}
```



对HashSet进行排序，使用

```java
TreeSet, new TreeSet<Integer>(set);
```





```java
import java.util.*;
public class SetDemo {

  public static void main(String args[]) { 
      int count[] = {34, 22,10,60,30,22};
      Set<Integer> set = new HashSet<Integer>();
      try {
         for(int i = 0; i < 5; i++) {
            set.add(count[i]);
         }
         System.out.println(set);

         TreeSet sortedSet = new TreeSet<Integer>(set);
         System.out.println("The sorted list is:");
         System.out.println(sortedSet);

         System.out.println("The First element of the set is: "+ (Integer)sortedSet.first());
         System.out.println("The last element of the set is: "+ (Integer)sortedSet.last());
      }
      catch(Exception e) {}
   }
}
```



使用Set, 转化成List

```java
new ArrayList(set);
```



