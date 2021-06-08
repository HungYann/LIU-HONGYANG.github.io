---
layout: post
author: LIU,HONGYANG
tags: [Git]
---





问题背景：

GitBook 运行时报错使用 nvm 降低node版本得以解决


##### 下载nvm

```
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.0/install.sh | zsh
```

##### 下载对应版本nodejs

```
nvm install 10.22.0
```


##### 使用

```
(base)  ⚙ liuhongyang@localhost  ~/Documents/GitHub/repositories/gitbook   master  nvm use 10.22.0
Now using node v10.22.0 (npm v6.14.6)
```

##### 查看本机版本nvm：

```
nvm current
```



##### 常见命令总结

```
nvm ls-remote // 列出所有可安装的node版本号
nvm install v12.18.3 // 安装指定版本号的node
nvm use v12.18.3 // 切换node版本，这个是全局的
nvm current // 当前node版本
nvm ls // 列出所有已经安装的node版本

```


##### 结果:
![](https://tva1.sinaimg.cn/large/008i3skNly1grax9k5ykhj31jv0u0q7l.jpg)


##### 参考：

https://juejin.cn/post/6866073251295002638

https://blog.csdn.net/qq_43528771/article/details/107949010

https://developpaper.com/gitbook-installation-uninstall-faq/
