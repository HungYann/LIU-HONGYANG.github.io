## Docker remote API


该教程基于Ubuntu或Debian环境，如果不是，请略过本文

**Docker API**

在Docker生态系统中一共有**三种API**

- Registry API:提供了与来存储Docker镜像的Docker Registry集成的功能
- Docker Hub API:提供了与Docker Hub集成的功能
- Docker Remote API:提供与Docker守护进程进行集成的功能

其中, Docker Remote API是最常用的


**启动Remote API**

Remote API是由Docker守护进程提供的。因此我们在启动Docker守护进程时
，需要给Docker守护进程传递一个-H标志即可做到

- Ubuntu或Debian系统: ```/etc/default/docker```
- 对于Red Hat、 Redora及相关发布版本 ```/etc/sysconfig/docker```

**在该配置文件中添加：**

```{}
OPTIONS='-H=tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock'
```

**重启Docker守护进程：**

```{}
systemctl stop docker
systemctl start docker
```

现在我们可以从一台远程主机来访问Docker守护进程：

```{}
docker -H docker.example.com:2375 info
```

**测试Docker Remote API**

```{}
curl http://docker.example.com:2375/info
```
返回JSON结果


**通过API来管理Docker镜像**


```{}
curl http://docker.example.com:2375/images/json | python -mjson.tool
```

**通过API来管理Docker容器**

```{}
curl -s http://docker.example.com:2375/containers/json | python -mjson.tool
```

References:

[Docker API详解](https://blog.csdn.net/laodengbaiwe0838/article/details/79340805)


