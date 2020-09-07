---
layout: post
author: LIU,HONGYANG
tags: [数据结构与算法]
---



这题是leetcode上非常简单的一道easy题。

拿这道题出来的目的是练习数组，以及寻求更好的解决方案。

题目描述如下：

 

You're given strings `J` representing the types of stones that are jewels, and `S`representing the stones you have. Each character in `S` is a type of stone you have. You want to know how many of the stones you have are also jewels.

The letters in `J` are guaranteed distinct, and all characters in `J` and `S` are letters. Letters are case sensitive, so `"a"` is considered a different type of stone from `"A"`.

Example 1:

```
Input: J = "aA", S = "aAAbbbb"
Output: 3
```

Example 2:

```
Input: J = "z", S = "ZZ"
Output: 0
　　
```

Note:

- `S` and `J` will consist of letters and have length at most 50.
- The characters in `J` are distinct.

 

首先我尝试使用Java解法：

思路很简单，就是将字符串**J** 和字符串 **S** 分别转化成数组形式，再进行迭代查找。

而时间复杂度就是O(J.length)*O(S.length)

这样做的话，我们来思考一个问题，会不会导致时间过于长？我觉得肯定会，可是当我将答案提交上去后，发现还是比93.31%的方案更快。

![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190920113541137-1909677115.png)

 

 

 

首先我们思考一下新的解决方案，到底能不能用在这个问题上，查找对于有序数的查找，可以使用二分查找，但是对于无序字符串的查找，需要的就是HashMap

hashMap是由key和value组成，

向数中添加，就用put,向数中查找,就用get(key).可是，在本问题中，如果将被查找的石头S串作为key,就会面临key值不能重复的问题。

而将S串作为value, 我们需要自己编写一套函数来实现这个功能

 

接下来尝试自己编写hashMap查找value，发现效果并不理想。

 

![img](https://img2018.cnblogs.com/blog/1067977/201909/1067977-20190920121511942-349524289.png)

 

 

 

 

 

 

方法一：

 

```java
class Solution {
public int numJewelsInStones(String J, String S) {

int countNumber = 0;
char[] charArrayJ = new char[50];
char[] charArrayS = new char[50];


charArrayJ = J.toCharArray();
charArrayS = S.toCharArray();

for(Character s:charArrayJ){
for(Character j:charArrayS){
if(s==j)
{
countNumber++;
}
}

}

return countNumber;

}
}


```



 

 

方法二：





```java


class Solution {
public int numJewelsInStones(String J, String S) {


char[] charArrayJ = new char[50];
char[] charArrayS = new char[50];


charArrayJ = J.toCharArray();
charArrayS = S.toCharArray();

HashMap<Integer,Character> hashmap = new HashMap<Integer, Character>();

int countNumber = 0;
//generateData,O(N)
for(int i=0;i<charArrayS.length;i++){

hashmap.put(i,charArrayS[i]);

}



//search,O(J.length)*O(J.lgnth)*O(S.length)
for (Character j : charArrayJ) {
for(Integer m: hashmap.keySet()) {
if(hashmap.get(m)==j){
countNumber++;
}
}
}

 


return countNumber;


}
}


```

 

 

所以可以看到，在时间复杂度层面，方法二O(S.length)*1+O(J.length)*O(J.lgnth)*O(S.length)会比方案一O(J.length)*O(S.length)长一些

 