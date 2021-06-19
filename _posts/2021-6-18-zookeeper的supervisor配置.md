---
layout: post
author: LIU,HONGYANG
tags: [zookeeper]
---



supervisor设置



**运行supervisorctl错误提示**【FATAL或BACKOFF 】Exited too quickly (process log may have details)问题总结



https://blog.csdn.net/CN_TangZheng/article/details/109223586



```
command=/data/ota_soft/zookeeper/bin/zkServer.sh start-foreground
```



改成：

```
command=/data/ota_soft/zookeeper/bin/zkServer.sh start
```



```shell
[program:zookeeper]
command=/data/ota_soft/zookeeper/bin/zkServer.sh start-foreground
;priority=999
autostart=true
autorestart=true
;startsecs=10
;startretries=3
;exitcodes=0,2
;stopsignal=QUIT
;stopwaitsecs=10
user=root
log_stdout=true
log_stderr=true
stdout_logfile=/data/logs/supervisord_zookeeper.log
redirect_stderr=true
;logfile_maxbytes=1MB
environment=JAVA_HOME=/opt/jdk1.8.0_191
```

