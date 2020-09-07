---
layout: post
author: LIU,HONGYANG
tags: [数据结构与算法]

---





### 题目描述(easy)

**Implement strStr()**

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

**Example 1**

```{}
Input: haystack = "hello", needle = "ll"
Output: 2
```

**Example 2**

```{}
Input: haystack = "aaaaa", needle = "bba"
Output: -1
```

**Clarification:**

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

### 基础解法

在本题中，strStr的功能是返回匹配的字符串第一次出现的位置。而在java当中，有一个函数具备此功能。那就是**IndexOf**

**indexOf**的返回值为**int**

如果找到，则返回找到字符起始字符位置的**index**索引(从0开始)。

如果未找到，则返回-1

参考如下：


```{}

public class containString
{
    public static void main(String[] args)
    {
        String str1 = "sdfsfsfa2we";
        String str2 = "we";

        String str3 = "me";

        System.out.println(str1.indexOf(str2));
        System.out.println(str1.indexOf(str3));
    }
}
```

所以，可以直接利用**indexOf**函数实现此功能。


### 代码优化

此外我们还可以优化代码，参考他人给的解法。实现此功能。

主要是利用迭代循环，然后再利用数值比较的方法进行判断，直到找到和**needle**相等的数组, 返回坐标。否则返回**index=-1**


```{}
class Solution {
    public int strStr(String haystack, String needle) {
      
    if(needle.length()==0)
    {
        return 0;
    }   
        
    int index = -1;
    
    for(int i=0;i<=haystack.length()-needle.length();i++)
    {
        if(haystack.charAt(i)==needle.charAt(0))
        {
            if(haystack.substring(i,i+needle.length()).equals(needle))
            {
                return i;
            }
        }
        
    }
        
    return index;
       
    
    }
}

```

