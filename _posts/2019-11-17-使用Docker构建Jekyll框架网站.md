## 使用Docker构建Jekyll框架网站


使用**dockerfile**构建**apache + jekyll**

目录


- [Jekyll基础镜像](#1)

- [构建Jekyll基础镜像](#2)

- [Apache镜像](#3)

- [构建Jekyll Apache镜像](#4)

- [创建Apache容器](#5)

- [查看Apache容器端口](#6)

- [更新Jekyll网站](#7)



<h4 id='1'>Jekyll基础镜像</h4>

```
mkdir jekyll
cd jekyll
vi Dockerfile
```

其中Dockerfile内容修改文件内容为:

```
FROM ubuntu
MAINTAINER hongyang liu 1664698982@qq.com
ENV REFRESHED_AT 2019-11-17

RUN apt-get -yqq update
RUN apt-get -yqq install ruby ruby-dev make nodejs
RUN gem install --no-rdoc --no-ri jekyll

VOLUME /data
VOLUME /var/www/html

WORKDIR /data

ENTRYPOINT ["jekyll","build","--destination=/var/www/html"]

```

构建成功后结果如下：

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g914oprt85j30ka044dgg.jpg)

```/data```,用来存放网站源代码

```/var/www/html/```,用来存放编译后的Jekyll网站码


镜像基于Ubuntu 18.04,并且安装了Ruby和用于支持Jekyll的包。
然后使用VOLUME指令创建了两个卷

最后一句命令的意思就是每次启动的时候就将/data下的源文件编译成可发布的网站内容，并放在/var/www/html中供下面的apache使用


<h4 id='2'>构建Jekyll基础镜像</h4>



```{}
docker build -t taiyangyixi2/jekyll .
```



<h4 id='3'>Apache镜像</h4>


创建Apache Dockerfile

```{}
mkdir apache
cd apache 
vi Dockerfile
```


Dockerfile内容



```{}
FROM ubuntu:14.04
MAINTAINER hongyang liu 1664698982@qq.com
ENV REFRESHED_AT 2019-11-17

RUN apt-get -yqq update
RUN apt-get -yqq install apache2

VOLUME ["/var/www/html"]
WORKDIR /var/www/html

ENV APACHE_RUN_USER www-data
ENV APACHE_RUN_GROUP www-data
ENV APACHE_LOG_DIR /var/log/apache2
ENV APACHE_PID_FILE /var/run/apache2.pid
ENV APACHE_RUN_DIR /var/run/apache2
ENV APACHE_LOCK_DIR /var/lock/apache2

RUN mkdir -p $APACHE_RUN_DIR $APACHE_LOCK_DIR $APACHE_LOG_DIR
EXPOSE 80
ENTRYPOINT ["/usr/sbin/apache2"]
CMD ["-D","FOREGROUND"]
```



Dockerfile构建了一个镜像，
然后使用VOLUME指令创建一个卷，
即/var/www/html/,用来存放编译后的Jekyll网站，然后将```/var/www/html```设置为工作目录


使用ENV设置必要的环境变量，创建了必要的目录
并设置了EXPOSE公开了80端口。
最后指定了ENTRYPOINT和CMD指令组合来在容器启动时默认运行Apache.

<h4 id='4'>构建Jekyll Apache镜像</h4>


```{}
docker build -t taiyangyixi2/apache .
```

启动Jekyll网站

现在有了如下两个镜像

- Jekyll:安装了Ruby及其他必备软件包的Jekyll镜像
- Apache:通过Apache Web服务器来让Jekyll网站工作起来的镜像

下载博客源代码

```{}
cd ~
git clone https://github.com/jamtur01/james_blog.git
```

在这个目录下可以看到一个启用了Twitter Bootstrap的最基础的Jekyll博客。如果你也想使用这个博客，可以修改```_config.yml```文件和主题，以符合你的要求
```{}
docker run -v /home/james_blog:/data/ --name james_blog taiyangyixi2/jekyll
```
运行结果

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g9160tsat9j317605sjsc.jpg)

启动了名为```james_blog```的新容器,
把本地的james_blog目录作为```/data/```卷挂在容器汇中。
容器已经拿到网站的源代码，并将其构建到已经编译的网站，存放到/var/www/html目录

<h4 id='5'>创建Apache容器</h4>


```{}
docker run -d -P --volumes-from james_blog taiyangyixi/apache
```
```{}--volumes-from```把指定容器里的所有卷都加入新创建的容器里

Apache容器可以访问之前创建的james_blog容器里的/var/www/html卷中存放的编译后的Jekyll网站。即便james_blog容器没有运行，Apache容器也可以访问这个卷。不过，容器必须存在，如果用**docker rm**命令删除了james_blog容器，那么这个卷和卷里的内容也就不存在了。

<h4 id='6'>查看Apache容器端口</h4>


```{}
docker port  30d486b77899 80
```

在Docker宿主机上浏览该网站：


![](https://tva1.sinaimg.cn/large/006y8mN6gy1g91bctms6gj31br0u0jzy.jpg)
 

<h4 id='7'>更新Jekyll网站</h4>


```{}
cd ~
vi james_blog/_config.yml
```

将title域修改为James' Dynamic Docker-driven Blog

更新博客网站

```{}
docker start james_blog
```

查看容器日志

```{}
docker logs james_blog
```
