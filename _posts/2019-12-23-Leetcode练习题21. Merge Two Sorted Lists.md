### 题目描述（easy）

**Merge Two Sorted Lists**

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Example:**

```{}
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

### 基础解法

在做本题的过程中，由于本人链表这块儿不是很熟悉，所以仿照了**Discuss**里的解法。思路如下，链表1和链表2是两个



代码如下：



```{java}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
    if(l1==null)
    {
        return l2;
    }
    if(l2==null)
    {
        return l1;
    }
        
   
    
        if(l1.val<=l2.val)
        {
            ListNode newNode = new ListNode(l1.val);
            newNode.next = mergeTwoLists(l1.next,l2);
            return newNode;
            
        }
        else
        {
             ListNode newNode = new ListNode(l2.val);
             newNode.next = mergeTwoLists(l1,l2.next);
             return newNode;
           
        }
    
    
    
   
    }
}
    
```
在解决该题目的过程中，大家容易犯的错误有：一是在合并的过程可能出现中断，另一个是存在特殊值的判断问题。

我们分析合并两个链表时，都是从头节点开始。如果链表1的头节点小于链表2的头节点，则链表1的头节点将是合并后链表的头节点。接下来我们继续开始下一轮合并。在两个链表中依然是排序的，因此合并这两个链表的步骤和前面的是一样的。我们还是比较两个头节点的值。如果链表2的头节点小于链表1的头节点的值，因此链表2的头节点的值将是合并剩余节点得到的链表的头节点。

![](https://tva1.sinaimg.cn/large/006tNbRwgy1ga735oop85j30la0h8q75.jpg)


由上图我们可以看到，（a）链表1的头节点的值小于链表2的头节点的值，因此链表1的头节点是合并后链表的头节点。(b)在剩余的节点中，链表2的头节点的值小于链表1的头节点的值，因此链表2的头节点是生育节点的头节点，把这个节点和之前已经合并好的链表的尾节点链接起来。



因此这是一个递归的过程，而递归的停止条件是，当输入第一个的链表为空时，我们只需要返回另外一个链表即可，让它和第二个链表合并。而当输入的第二个链表的为空时，我们只需要返回另外第一个链表即可。


### 代码优化


我们可以看到，在循环判断的内部，

```{}
ListNode newNode = new ListNode(l1.val);
newNode.next = mergeTwoLists(l1.next,l2);
```

这步的目的是使用一个新的节点来完成链表合并。在该步中，我们让**newNode**节点等于**l1**节点的值，实际上我们并不需要再额外创建一个节点等于**l1**，只需要让原来的**l1**节点当作头节点即可。省去了每次**new**新节点的所花的空间。



### 链表介绍

链表也是线性结构，但是和数组不同，链表中的数据并不存储在连续的内存值中。元素通过指针连接在一起。

![](https://tva1.sinaimg.cn/large/006tNbRwgy1ga6x1pobidj30je07idg0.jpg)

链表的优势：

- 动态大小
- 容易删增

劣势：

- 不允许随机访问，只允许顺序访问
- 需要额外的内存来存储指针


### 表示方法：


链表由指向链表第一个节点的指针表示。链表的节点叫头节点。如果链表为空，则头也为空。
每个节点包含两部分：数据和指针。


```{}
public class LinkedList
{
    Node head;
    class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
        }

    }

}

```

第一个简单的java语言链表，
代码如下：


```{}
public class LinkedList
{
   Node head;

   static class Node
   {
       int data;
       Node next;
       Node(int d)
       {
           data = d;
           next = null;
       }
   }

   public static void main(String[] args)
   {
       LinkedList llist = new LinkedList();

       llist.head = new Node(1);
       Node second = new Node(2);
       Node third = new Node(3);


       llist.head.next = second;
       second.next = third;
       
   }


}


```

其中，**Node head**是用来声明一个链表的头节点；在class Node 中，声明链表的结构，一个数据data, 一个下一个节点。并声明一个初始化构造器。

在主函数中，我们创建一个链表。并用

- **llist.head = new Node(1);**创建第一个节点；
- **Node second = new Node(2);**创建第二个节点并将数据赋值为2;
- **Node third = new Node(3);**用来创建第三个节点，并将数据赋值为3;

![](https://tva1.sinaimg.cn/large/006tNbRwgy1ga6yakiksrj30q806qmxg.jpg)

接下来开始连接不同的节点：

- **llist.head.next = second**连接第一个和第二个节点；

- **second.next = second**连接第二个和第三个节点；

![](https://tva1.sinaimg.cn/large/006tNbRwgy1ga6ycgove9j30qi06gaad.jpg)

### 链表遍历：

```{}
public class LinkedList
{
    Node head;

    static class Node
    {
        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }

    }

    public static void main(String[] args)
    {
        LinkedList llist = new LinkedList();



        llist.head = new Node(1);

        Node second = new Node(2);

        Node third = new Node(3);


        llist.head.next = second;

        second.next = third;

        llist.printLinkedList();

    }

    public void printLinkedList()
    {
        Node n = head;

        while(n!=null)
        {
            System.out.println(n.data);
            n = n.next;
        }
    }
}

```

其中在链表中，**n = head**表示指向head的节点。再通过**while**循环来便利内容打印






