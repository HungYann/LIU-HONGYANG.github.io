---
layout: post
author: LIU,HONGYANG
tags: [VScode]
---



##### 1.下载文件



解压到`/usr/local`目录下，控制台输入`go version`查看是否安装成功



##### 2.设置工作区目录GOPATH

```
go env -w GOPATH=我们自己的工作路径 
```

例如：

```
go env -w GOPATH=/Users/liuhongyang/go
```



##### 3.使用代理

此步骤略GO



##### 4./echo $GOROOTi

```
$ cd $GOPATH/src/hello
$ go mod init
```



##### 5.json文件配置

```
{
  // Use IntelliSense to learn about possible attributes.
  // Hover to view descriptions of existing attributes.
  // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Launch Package",
      "type": "go",
      "request": "launch",
      "mode": "auto",
      "program": "${fileDirname}",
      "env": {
        "GOPATH": "/Users/liuhongyang/go",
        "GOROOT": "/usr/local/go"
      },
      "args": []
    }
  ]
}
```



##### 6.设置代理

```
 liuhongyang@liuhongyangdeMacBook-Pro  ~/go  go env -w GO111MODULE=on
 liuhongyang@liuhongyangdeMacBook-Pro  ~/go  go env -w GOPROXY=https://goproxy.cn,direct
```

