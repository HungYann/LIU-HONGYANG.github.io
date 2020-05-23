---
layout: post
author: LIU,HONGYANG
tags: [前端]
---





### 1.下载文件，确保本地运行



```
npm install
npm run build
```





### 2.修改packages.json



```javascript
 "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject",
    "predeploy":"npm run build",
    "deploy": "gh-pages -d build"
  }
```



```javascript
 "name": "rmind",
 "homepage": "https://liu-hongyang.github.io/mindmap",
 "version": "0.1.0"
```





### 3.安装gh-pages



```
npm install gh-pages --save-dev
```





### 4.部署到github page上



```
npm run deploy
```



![Screenshot 2020-05-23 at 12.36.19](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf2a1zc5ufj31jl0u0dje.jpg)





https://liu-hongyang.github.io/mindmap/



### 5.参考链接：

https://blog.csdn.net/xieluoxixi/article/details/86495198