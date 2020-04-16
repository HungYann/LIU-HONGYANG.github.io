---
layout: post
author: LIUHONGYANG
tags: [Linux]
---

##### 1.临时修改:

```shell
hostname newServername
```



##### 2.永久修改

进入/etc/目录下

```
vim hostname
vim hosts
```

 改成newServername



##### 3.重启

检查一下是否正确

```
hostname
```



