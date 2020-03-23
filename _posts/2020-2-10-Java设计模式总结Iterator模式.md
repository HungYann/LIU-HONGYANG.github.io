Java设计模式总结



`Iterator模式`

![](https://tva1.sinaimg.cn/large/0082zybpgy1gbr91h9fo9j30t60ucjux.jpg)





Iterator.java

```java
public interface Iterator {
    public abstract boolean hasNext();
    public abstract Object next();
}
```

hasNext():返回值为 boolean类型。“返回当前元素，并指向下一个元素”。

next():"确认是否还有下一个元素"，如果没有则返回false,否则返回true



Aggregate.java

```java
public interface Aggregate {
    public abstract Iterator iterator();
}
```

在Aggregate接口中声明的方法只有一个iterator方法，该方法会生成一个用于遍历集合的迭代器





BookShelf.java

```java
public class BookShelf implements Aggregate {

    private Book[] books;

    private int last = 0;

    public BookShelf(int maxsize){
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index){
        return books[index];
    }

    public void appendBook(Book book){
        this.books[last] = book;
        last++;
    }

    public int getLength(){
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}

```



BookShelfIterator.java

```java
public class BookShelfIterator implements Iterator{

    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf){
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index<bookShelf.getLength()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}

```



Main.java

```java
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the world in 80 days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));

        Iterator it = bookShelf.iterator();
        while (it.hasNext()){
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
    }

}
```



| 名字              | 说明           |
| :---------------- | -------------- |
| Aggregate         | 表示集合的接口 |
| Iterator          | 遍历集合的接口 |
| Book              | 表示书的种类   |
| BookShelf         | 书架的类       |
| BookShelfIterator | 遍历书架的类   |
| Main              | 测试程序行为类 |

