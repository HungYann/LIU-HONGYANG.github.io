---
layout: post
author: LIU,HONGYANG
tags: [Java]
---



![image-20200706140835133](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggh7zbsx8bj31xk0u0k9v.jpg)



>  Map



HashMap的迭代访问取出其中的元素，

```java

import java.util.HashMap;
import java.util.Map;

public class TEST {
        public static void main(String[] args) {

        frequencySort("maaqq");

        }

        public static void frequencySort(String s) {

        HashMap<Character,Integer> hashMap = new HashMap<>();

        char[] charArray = s.toCharArray();

        for(int i=0;i<charArray.length;i++){
            if(hashMap.containsKey(charArray[i])){
                hashMap.put(charArray[i],hashMap.get(charArray[i])+1);
            }
            else
            {
                hashMap.put(charArray[i],1);
            }
        }


        for(HashMap.Entry entry:hashMap.entrySet()){
            char i = (char) entry.getKey();
            int j = (int) entry.getValue();
            System.out.println("key "+i+"  "+"value "+j);
        }



    }

}

```







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



> HashSet

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

其中list包含重复的元素，而set包含独特的元素

```java
new ArrayList(set);
```



HashSet中常见的方法：

![image-20200706211048885](https://tva1.sinaimg.cn/large/007S8ZIlgy1gghk6ksh5vj31bc0sygqs.jpg)





