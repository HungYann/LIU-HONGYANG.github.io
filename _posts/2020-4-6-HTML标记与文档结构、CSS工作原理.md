

### 1HTML标记与文档结构



#### 1.1HTML标记基础



##### 文本用闭合标签



![image-20200404193713171](https://tva1.sinaimg.cn/large/00831rSTgy1gdhyuj1p8vj31000e6q5f.jpg)





##### 引用非文本内容用自闭合标签



![image-20200404200832641](https://tva1.sinaimg.cn/large/00831rSTgy1gdhzr4cefgj312k0ck0w9.jpg)





注：HTML5中会忽略标签后面的斜杠

```
<img src="images/cisco.jpg" alt="My dog Cisco" >
```



##### 属性



每个HTML标签都可以添加属性，class和id属性，几乎可以用于任何标签。



![image-20200404202417115](https://tva1.sinaimg.cn/large/00831rSTgy1gdi08aitw3j30we0860tm.jpg)

![image-20200404202432273](https://tva1.sinaimg.cn/large/00831rSTgy1gdi08bj7ofj30wm084t92.jpg)

![image-20200404202445422](https://tva1.sinaimg.cn/large/00831rSTgy1gdi08ds0dyj30wa03g0sp.jpg)􏴯􏵊􏲗􏴱􏰽􏲒􏳓􏰚􏰔􏰕􏰍􏰖􏰯􏵋􏵌􏴷􏳘􏰿􏱋􏲲􏵍􏳲􏰭􏵇􏵈􏲤􏳭􏳮􏲉􏳯􏳻 􏲙􏵎􏴋􏳼􏲲􏵇􏵈􏳹􏳑 􏴯􏲜􏵏􏵐􏵃􏵄􏰴􏱖􏱑􏳹􏱍􏳋􏵑􏱋􏵒􏲖􏴸􏴴􏴵􏳼􏲲􏰍􏰖􏵓􏵔􏳸􏰔􏲩􏳹􏳴􏳢􏵑􏲣􏳍􏳎􏵆􏲰􏰯􏰍􏰖􏲤



##### 复合元素



`<ol>`:有序列表



`<ul>`:无序列表



```html
<ol>
    <li>Save HTML file</li>
    <li>Move file to Web server via FTP</li>
    <li>Preview in browser</li>
</ol>
```



![image-20200404213205487](https://tva1.sinaimg.cn/large/00831rSTgy1gdi2629t3rj30ea046mxf.jpg)





```html
<ul>
    <li>Save HTML file</li>
    <li>Move file to Web server via FTP</li>
    <li>Preview in browser</li>
</ul>
```





![image-20200404213512715](https://tva1.sinaimg.cn/large/00831rSTgy1gdi29afh3lj30fu046dg3.jpg)



##### 嵌套标签



在一个标签里嵌套另一个标签（也就是前者的开标签写在后者的开标签之前），必须要先关闭后一个标签，再关闭前面那个标签。



```html
<p>That car is <em>fast</em>.</p>
```



#### 1.2HTML文档剖析



##### HTML模版

```html
<!DOCTYPE HTML>
<html>
	<head>
        <meta charset="utf-8">
    
		<title>An HTML</title>
	</head>
    <body>
        <!-- body内容 -->
    </body>
</html>
```



##### 块级元素和行内元素



![image-20200404221432128](https://tva1.sinaimg.cn/large/00831rSTgy1gdi3e7d1uyj30zk04i0u2.jpg)



##### 嵌套元素



```html
<p>It is <strong>absolutely critical</strong> that <em>everyone</em> does this <abbr title="as soon as possible">ASAP</abbr></p>
```

![image-20200404221903347](https://tva1.sinaimg.cn/large/00831rSTgy1gdi3iws2elj30vg05ajsn.jpg)





#### 1.3文档对象模型



文档对象模型(DOM)



```html
<body>
    <section>
        <h1>The Document Object Model</h1>
        <p>The page's HTML markup structure defines the DOM</p>
    </section>
</body>
```



![image-20200404225543113](https://tva1.sinaimg.cn/large/00831rSTgy1gdi4l2em2uj305w060dfv.jpg)







#### 1.4小结



![image-20200404222056840](https://tva1.sinaimg.cn/large/00831rSTgy1gdi3kvmh4qj30w609w77q.jpg)





### 2CSS工作原理





```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <style>
        /**CSS规则放在<style>标签中 **/
    </style>
    
    <body>
        <!-- HTML元素放在<body>标签中 -->
    </body>
</html>

```





#### 2.1剖析CSS规则

为文档添加样式的三种方法：



> 行内样式



```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    
    <body>
        <p> This paragraph simply takes on the brower's</p>
        
        <p style="font-size: 12px; font-weight: bold">
            By adding inline CSS styling to this paragraph
        </p>
        
    </body>
</html>

```



> 嵌入样式



嵌入的CSS样式是放在HTML文档的head元素中：

```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <style>
        h1{font-size: 16px}
        p{color:rebeccapurple}
    </style>
    
    <body>
        <h1>This is paragraph</h1>
        <p> This paragraph simply takes on the brower's</p>
    </body>
</html>

```



> 链接样式



`styles.css`文件：

```css
p{color:red}
```



`css.html`文件：

```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <link href="styles.css" rel="stylesheet" text="text/css">
    <body>
        <h1>This is paragraph</h1>
        <p> This paragraph simply takes on the brower's</p>
    </body>
</html>

```





##### CSS规则命名惯例

![image-20200405011909794](https://tva1.sinaimg.cn/large/00831rSTgy1gdi8qbaltej30e007874v.jpg)



CSS规则分为两部分，即选择符和声明：（属性和值）



`第一种方法` ：多个声明包含在一条规则里

使用分号分隔

```css
p {color:red; font-size:12px; font-weight:bold;}
```



`第二种方法`：多个选择符组合在一起



```css
h1{color:blue;font-weight:bold;}
h2{color:blue;font-weight:bold;}
h3{color:blue;font-weight:bold;}
```



```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <link href="styles.css" rel="stylesheet" text="text/css">
    <body>

        <h1>Hello World</h1>
    </body>
</html>

```



`第三种方法`：多条规则应用给一个选择符



```css
h1,h2,h3{color:red;font-weight:bold;}
h1{font-style: italic}
```



```html
<!DOCTYPE html>

<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <link href="styles.css" rel="stylesheet" text="text/css">
    <body>

        <h1>Hello World</h1>
    </body>
</html>

```



#### 2.2上下文选择符



后代组合式选择符，就是一组以空格分隔的标签名。用于选择作为指定祖先元素后代的标签。



![image-20200405142035784](https://tva1.sinaimg.cn/large/00831rSTgy1gdivbe2egij30c404a74i.jpg)



```html
article p{font-weight: bold;}
```



![image-20200405142614280](https://tva1.sinaimg.cn/large/00831rSTgy1gdivha79xoj30n6082aat.jpg)

![image-20200405144114115](https://tva1.sinaimg.cn/large/00831rSTgy1gdivwv859hj30hy0d0wfz.jpg)



接下来修改上下文选择符，让它选择：



```css
p em{color:green;}
```



注：上下文选择符以空格作为分隔符，而分组选择符则以逗号作为分隔符。



```css
article p em{color:green;}
```



![image-20200405144857469](https://tva1.sinaimg.cn/large/00831rSTgy1gdiw4wntwwj30mo07i752.jpg)





#### 2.3特殊的上下文选择符



上面的上下文选择符以某个祖先作为上下文，只要标签在它的层次上游存在这么一个祖先，那么就会选中该标签。

无论该标签到座位祖先的上下文之间隔着多少层次也都没关系。



> 子选择符

标签1 >  标签2



```css
section > h2{color:aqua}
```

> 紧邻同胞选择符+

标签1  + 标签2



```css
h2 + p{color:aqua}
```



> 一般同胞选择符~

标签1～标签2



标签2必须跟在其同胞标签1后面

```css
h2 ~ a{color:red;}
```



> 通用选择符



```css
* {color:gray}
```



```css
p * {color:red;}
```



![image-20200405160101950](https://tva1.sinaimg.cn/large/00831rSTgy1gdiy7wga2bj30um0u0q8o.jpg)



#### 2.4ID和类选择符

ID和类为我们选择元素提供了另一套手段，利用它们可以不用考虑文档的层次结构。只要你在HTML标记中为元素添加了id和class属性，就可以在CSS选择符中使用ID和类名.



##### 类属性

```html
<html>
    <meta charset="utf-8">
    <title>HTML5 Template</title>
    <link href="styles.css" rel="stylesheet" text="text/css">
    <body>
        <p>I am testing</p>
        <p class="specialtext"> This FIRST <span>LEO</span> as the FIRST paragraph</p>
        <p class="specialtext"> This SECOND <span>ZACK</span> as the SECOND paragraph</p>
    </body>
</html>
```





> 类选择符

```css
p {font-size: 12px;font-style: italic}
.specialtext{color:red}
```



![image-20200405205446701](https://tva1.sinaimg.cn/large/00831rSTgy1gdj6pllglej30f405074n.jpg)



>  标签带类选择符



```css
p {font-size: 12px;font-style: italic}
p.specialtext{font-size: 20px;color:red}
```



![image-20200405205839558](https://tva1.sinaimg.cn/large/00831rSTgy1gdj6tkqcn8j30ys09kabn.jpg)



> 多类选择符



```html
 <p class="specialtext featured">Here the span tag<span>may or may not</span> be styled.</p>
```



多个类名，这里的specialtext和featured,放在听一对引号里，用空格分隔。



```css
p {font-size: 12px;font-style: italic}
p.specialtext{font-size: 30px;color:red}
.specialtext.featured{font-size: 120%}
```





##### ID属性

ID与类的写法类似，表示ID选择符的#的用法，

`#` (井号)

```html
<p id="specialtext">This is the special text.</p>
```



访问css

```css
p#specialtext
或者
#specialtext
```





##### ID和属性选择符号总结



> ID选择

ID的用途是在页面中唯一地标示一个元素。

```html
<nav id="mainmenu">
```



![image-20200405212744220](https://tva1.sinaimg.cn/large/00831rSTgy1gdj7nu5v4xj30ie0q2wj4.jpg)





```html
 <nav id="firstId"> 
   <ul>
     <li>1</li>
     <li>2</li>
     <li>3</li>
   </ul>  
</nav>
```



```css
#firstId li{color: aqua}
```



![Screenshot 2020-04-05 at 21.30.41](https://tva1.sinaimg.cn/large/00831rSTgy1gdj7s73thtj304m04awed.jpg)







> 类选择



![Screenshot 2020-04-05 at 21.34.35](https://tva1.sinaimg.cn/large/00831rSTgy1gdj7vrixlbj30ne0rijvk.jpg)





>  总结

![image-20200405211634684](https://tva1.sinaimg.cn/large/00831rSTgy1gdj7c7jhoqj30w0088dic.jpg)



#### 2.5属性选择符

##### 属性名选择符



![image-20200405221406626](https://tva1.sinaimg.cn/large/00831rSTgy1gdj902lrlej30sk0gsgpm.jpg)





##### 属性值选择符



![image-20200405221430643](https://tva1.sinaimg.cn/large/00831rSTgy1gdj90hlns4j30ra0f277e.jpg)



#### 2.6伪类



针对链接的伪类：

- Link      此时，链接就在那儿等着用户点击
- Visited  用户此前点击过这个链接
- Hover   鼠标指针正悬停在链接上
- Active   链接正在被点击



```css
a:link {color:black;}
a:vistied {color:gray;}
a:hover {text-decoration:none;}
a:active {color:red;}
```



> 结构化伪类



![image-20200406012218193](https://tva1.sinaimg.cn/large/00831rSTgy1gdjefw2sz4j30yu0r6jvu.jpg)







#### 2.7继承



![image-20200406012740438](https://tva1.sinaimg.cn/large/00831rSTgy1gdjelgzv0dj30xq0lojzg.jpg)



#### 2.8层叠



![image-20200406012914586](https://tva1.sinaimg.cn/large/00831rSTgy1gdjen3xqp1j30x80a241t.jpg)

> 层叠规则 一 找到应用给每个元素和属性的所有高声明



> 层叠规则 二 按照顺序和权重排序



```css
p {color:green !important;font-size:12pt;}
```



![image-20200406025040969](https://tva1.sinaimg.cn/large/00831rSTgy1gdjgzuqm9mj30w80ca793.jpg)



> 层叠规则三：按特指度排序



![image-20200406025207260](https://tva1.sinaimg.cn/large/00831rSTgy1gdjh1cjzirj30tu0ggtcx.jpg)

> 计算特指度

![image-20200406025344296](https://tva1.sinaimg.cn/large/00831rSTgy1gdjh30xabsj30ru0nujwn.jpg)



> 层叠规则四：顺序决定权重



![Screenshot 2020-04-06 at 02.56.12](https://tva1.sinaimg.cn/large/00831rSTgy1gdjh63h6l3j30ls0p4439.jpg)







#### 2.9 小结



![image-20200405011552103](https://tva1.sinaimg.cn/large/00831rSTgy1gdi8mvzsngj31120dcdk8.jpg)