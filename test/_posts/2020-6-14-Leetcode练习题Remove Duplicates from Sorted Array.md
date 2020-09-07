---
layout: post
author: LIU,HONGYANG
tags: [数据结构与算法]
---



##问题描述

**26. Remove Duplicates from Sorted Array**

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.


**Example 1:**

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.


**Example 2:**

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.



##自己解法

移除重复值这一题挺让人困惑的，因为题目要求整数，但是返回值是数组

题目中描述需要返回一个没有重复序列的数组，并且不能使用额外的内存开销。我的解法思路是两个指针，和官方答案差不多。

由于数组已经是排序好的了，我们可以设置两个指针**i**和**j**,**i**比**j**慢，因为**i**用来存储新数组的长度。而**j**用来判断。

- 1.如果*nums[i] == nums[j]*,则我们增加**j**的值略过；
- 2.如果*nums[i] != nums[j]*,则我们将**nums[j]**的值给**nums[i+1]**,因为只有这样**i**中才能不是重复的值；
- 3.重复以上两步直到**j**到达数组的末尾；



```{java}
class Solution {
    public int removeDuplicates(int[] nums) {
    if(nums.length==0) return 0;
        
    int left = 0;
    
      
        
    for(int right = 1;right<nums.length;right++)
    {
        if(nums[right]!=nums[right-1])
        {
            left++;
            nums[left]= nums[right];
            
        }
      
    }
   
   
    return left + 1;
}
}
```

##他人解法

```{java}
public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[i]) {
            i++;
            nums[i] = nums[j];
        }
    }
    return i + 1;
}
```

解法的不同之处在于，我的解法主要用一个指针指向自己，用后一个和前面一个进行比较，而他们用了两个不同的指针。用来比较前面慢指针一个和后面一个快指针。

##改进建议

在如下程序中，

```{java}
if (nums[j] != nums[i])
{
       i++;
       nums[i] = nums[j];
}
```
我们可以使用**nums[++i]=nums[j]**代替，这样可以先自增后赋值。

##获得思考

双指针是一个非常有效的单一数组内进行值比较的方法，一个快指针用来比较，一个慢指针数用来存储，最后在不需要额外内存的情况下实现目的。