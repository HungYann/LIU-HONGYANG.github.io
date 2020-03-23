---
layout: post
title: Python安装virtualenv
date: 2020-03-23
author: LIU,HONGYANG
tags: [Java]
comments: true
toc: false
pinned: true
---







> 下载virtualenv文件



```shell
pip install virtualenv
```



>  创建普通项目，以当前版本为系统

```
virtualenv Flask04
```



or



>  创建python3.5项目



```
virtualenv -p /usr/bin/python3.5 env3.5
```



> 激活环境

注，有可能是自动激活

```
source venv/bin/activate
```



> 退出环境

```
deactivate
```