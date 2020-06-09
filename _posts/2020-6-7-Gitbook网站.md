---
layout: post
author: LIU,HONGYANG
tags: [Git]
---



### 脚本命令：



```
$ gitbook --version
CLI version: 2.3.2
GitBook version: 3.2.3
```



```
$ gitbook build
info: 7 plugins are installed
info: 6 explicitly listed
info: loading plugin "highlight"... OK
info: loading plugin "search"... OK
info: loading plugin "lunr"... OK
info: loading plugin "sharing"... OK
info: loading plugin "fontsettings"... OK
info: loading plugin "theme-default"... OK
info: found 32 pages
info: found 5 asset files
info: >> generation finished with success in 3.7s !
```



**bash.sh**脚本命令

```
#!/usr/bin/env sh

echo '开始执行命令'
# 生成静态文件
echo '执行命令：gitbook build .'
gitbook build .

# 进入生成的文件夹
echo "执行命令：cd ./_book\n"
cd ./_book

# 初始化一个仓库，仅仅是做了一个初始化的操作，项目里的文件还没有被跟踪
echo "执行命令：git init\n"
git init

# 保存所有的修改
echo "执行命令：git add -A"
git add -A

# 把修改的文件提交
echo "执行命令：commit -m 'deploy'"
git commit -m 'deploy'

# 如果发布到 https://<USERNAME>.github.io/<REPO>
echo "执行命令：git push -f https://github.com/yulilong/book.git master:gh-pages"
git push -f https://github.com/yulilong/book.git master:gh-pages

# 返回到上一次的工作目录
echo "回到刚才工作目录"
cd -
```





其中:

```
git push -f https://github.com/yulilong/book.git master:gh-pages
```

替换为：

```
git push -f origin master:gh-pages
```





上传完毕即配置成功：

![image-20200607191318547](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfjxtdv9ldj31jl0u045k.jpg)



### References:

https://segmentfault.com/a/1190000017960359