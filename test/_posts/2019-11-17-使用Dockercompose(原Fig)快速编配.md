---
layout: post
author: LIU,HONGYANG
tags: [Docker]

---







### Docker使用compose(原Fig)快速编配

目录

- [安装](#1)
- [应用](#2)
- [构建以及运行](#3)


<h2 id='1'>安装</h2>

在Linux上安装Fig:
在OS上安装：



在Linux上安装Fig:

```{bash}
sudo bash-c "curl -L https://github.com/docker/fig/releases/download/0.5.2/darwin "

chmod +x /usr/local/bin/fig
```

在OS上安装：

```{bash}
curl -L https://github.com/docker/fig/releases/download/0.5.2/darwin > /usr/local/bin/fig

chmod +x /usr/local/bin/fig
```

查看结果:

```{bash}
fig --version
```



Docker公司还提供了编配工具compose(原Fig)用于加速Docker环境的构建。


```{bash}
pip install docker-compose
```




<h2 id='2'>应用</h2>


采用两个容器作为示例：

- 应用容器，运行Python示例程序
- Redis容器，运行Redis数据库

创建一个目录并创建Dockerfile,

```{bash}
mkdir figapp
cd figapp
touch Dockerfile
```

创建一个名叫app.py文件，并写入代码清单，代码如下所示：


app.py:

```{python}
from flask import Flask
from redis import Redis
import os
app = Flask(__name__)
redis = Redis(host='redis', port=6379)

@app.route('/')
def hello():
    redis.incr('hits')
    return 'Hello World! I have been seen %s times.' % redis.get('hits')

if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)

```

还需要创建requirements.txt文件来保存应用程序的依赖关系。

```{python}
flask
redis
```

最后添加Dockerfile文件内容：

```{bash}
FROM python:2.7
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
```


文件基于python:2.7镜像构建。
首先添加文件app.py和requirements.txt到镜像中的/figapp目录。
之后Dockerfile将工作目录设置为/figapp,并执行pip命令来安装应用的依赖 ：
flask和redis

构建镜像

```{bash}
cd figapp
docker build -t="taiyangyixi2/figapp"
```

查看镜像


```{bash}
docker images
```


定义服务

创建docker-compose.xml文件

```
web:
  build: .
  command: python app.py
  ports:
   - "5000:5000"
  volumes:
   - .:/code
  links:
   - redis
redis:
  image: redis
```


<h2 id='3'>构建以及运行</h2>


构建:

```{bash}
docker-compose up
```

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g91eqo5lbwj30le06wmyd.jpg)

运行:

```{bash}
docker-compose run web env
```

停止:

```{bash}
docker-compose stop
```


**References**：

[快速编配](https://blog.windrunner.me/sa/docker.html)


