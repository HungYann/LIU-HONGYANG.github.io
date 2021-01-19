---
layout: post
author: LIU,HONGYANG
tags: [学习计划]
---



### 前序



本文讲述我是如何学习Berkeley CS186课程的，在学习课程之前，我一直在想，如何才能学好学习计算机技术？曾经的我尝试了很多技术栈，包含前端，安全，后端，机器学习等等，很多技术都是浅尝辄止，到了最后感觉什么都学了，而什么都学不精通。为了改变这种状况，我决定降低自己的期望值，并从基础入手，不断的夯实基础，不再去搞框架知识。**朝着一个目标不断做精深练习，不断犯错，不断挑战自己的极限，这种努力给你带来的收获绝对超出你的想象。**

引述原文的话就是：

<div align="center">
  <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmsxlxwyz1j30sc0a8aca.jpg" width="80%">
</div>





我的想法是从如下几篇文章中得到的：

 25 岁做什么，可在 5 年后受益匪浅？ - 李苦李的回答 - 知乎 https://www.zhihu.com/question/52178718/answer/894704445

[怎样才算一个计算机知识体系完整的毕业生？](https://mp.weixin.qq.com/s/MT2ithkc3y7Equ8-I2Sxkg)

[计算机系的同学应该有更高的雄心壮志](https://mp.weixin.qq.com/s/DdE-WVBxuYFuwZLDdMvx5g)

[帅北的「编程能力」从什么时候开始突飞猛进的？](https://mp.weixin.qq.com/s/uq67R4jHpYkbMUQqIBgpHA)



人的经历是有限的，在有限的经历下，选择一个目标，不断得做精深练习，不断犯错，不断努力，这也是我决定做一个数据库管理系统的初衷。



### 准备



##### 1.[安装ant](https://www.youtube.com/watch?v=XsUsLWvh0zo)



ant是Apache公司的文件构建工具，下载的[地址](https://ant.apache.org/bindownload.cgi)

下载完成后，解压即可，为了根方便的使用此工具，我将ant移动到`/usr/local/ant`目录下面，改变命令行权限，将ant及其子文件的拥有者设为root，群体使用者设置为wheel组。[链接](https://www.runoob.com/linux/linux-comm-chown.html) 

```shell
sudo chown -R root:wheel ant
```

设置完环境后，

进入到项目目录下，运行`ant` 命令：

<div align="center">
  <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmsxxfp0b7j30vi02wt91.jpg" width="80%">
</div>



运行成功，那么准备工作就完成了。



##### 2.辅助资料



该文的项目是Berkeley CS186课程的课设，为了完成此任务，我收集了一些可能会有帮助的材料：



1.Intro to Database Systems - Fall 2013

https://sites.google.com/site/cs186fall2013/homeworks/project-1



2.ant的使用：

https://www.tutorialspoint.com/ant/ant_build_files.htm



3.伯克利的参考课程：

比较推荐的一个：



<iframe width="1280" height="720" src="https://www.youtube.com/embed/j-iq40QBJy8?list=PLYp4IGUhNFmw8USiYMJvCUjZe79fvyYge" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>



备用参考的：

http://www.infocobuild.com/education/audio-video-courses/computer-science/CS186-Spring2015-Berkeley/lecture-01.html

或者：

https://www.youtube.com/watch?v=y5IShB9ihds&list=PLhMnuBfGeCDPtyC9kUf_hG_QwjYzZ0Am1



4.课程网站：

ppt: https://sites.google.com/site/cs186spring2015/home/schedule-and-notes





另外感谢icegene博主提供的资料[博客](https://iamxpy.github.io/2017/10/05/%E4%BC%AF%E5%85%8B%E5%88%A9%E5%A4%A7%E5%AD%A6%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BD%9C%E4%B8%9A%E5%AE%9E%E7%8E%B0SimpleDB/)

博客中记录了博主的心得，这也是完成该课程必不可少的资料。



##### 3.课程内容





