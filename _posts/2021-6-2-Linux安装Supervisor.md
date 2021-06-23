---

layout: post
author: LIU,HONGYANG
tags: [Linux]
---



# 预备知识



##### Linux scp命令：



- 本地复制到远程

```sh
scp local_file remote_username@remote_ip:remote_folder 
```



- 远程复制到本地

```shell
scp root@www.runoob.com:/home/root/others/music /home/space/music/1.mp3 
```



- 复制目录命令格式：

```
scp -r local_folder remote_username@remote_ip:remote_folder
```





# Linux运行supervisor



安装`supervisor`方法：

参考：`yum ` 安装配置supervior命令

https://blog.csdn.net/DongGeGe214/article/details/80264811

详细code如下：

```sh
$ sudo su - #切换为root用户

# yum install epel-release
# yum install -y supervisor
# systemctl enable supervisord # 开机自启动
# systemctl start supervisord # 启动supervisord服务

# systemctl status supervisord # 查看supervisord服务状态
# ps -ef|grep supervisord # 查看是否存在supervisord进程
```



**注意：**不要和systemctl弄混，二者相互冲突



第一个错误：

```shell
[root@s2-vm-02-39 ~]# journalctl -xe
-- Unit supervisord.service has failed.
--
-- The result is failed.
Jun 02 03:59:14 s2-vm-02-39 systemd[1]: Unit supervisord.service entered failed state.
Jun 02 03:59:14 s2-vm-02-39 systemd[1]: supervisord.service failed.
Jun 02 03:59:14 s2-vm-02-39 polkitd[29099]: Unregistered Authentication Agent for unix-process:29591:197680977 (system bus name :1.7712, object path /org/freedesktop/PolicyKit1/Auth
Jun 02 04:00:01 s2-vm-02-39 systemd[1]: Started Session 3790 of user root.
-- Subject: Unit session-3790.scope has finished start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
--
-- Unit session-3790.scope has finished starting up.
--
-- The start-up result is done.
Jun 02 04:00:01 s2-vm-02-39 CROND[29641]: (root) CMD (/usr/lib64/sa/sa1 1 1)
Jun 02 04:01:01 s2-vm-02-39 systemd[1]: Started Session 3791 of user root.
-- Subject: Unit session-3791.scope has finished start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
--
-- Unit session-3791.scope has finished starting up.
--
-- The start-up result is done.
Jun 02 04:01:01 s2-vm-02-39 CROND[29701]: (root) CMD (run-parts /etc/cron.hourly)
Jun 02 04:01:01 s2-vm-02-39 run-parts(/etc/cron.hourly)[29704]: starting 0anacron
Jun 02 04:01:01 s2-vm-02-39 run-parts(/etc/cron.hourly)[29710]: finished 0anacron
Jun 02 04:01:26 s2-vm-02-39 dbus-daemon[495]: dbus[495]: [system] Activating via systemd: service name='org.freedesktop.PackageKit' unit='packagekit.service'
Jun 02 04:01:26 s2-vm-02-39 dbus[495]: [system] Activating via systemd: service name='org.freedesktop.PackageKit' unit='packagekit.service'
Jun 02 04:01:26 s2-vm-02-39 systemd[1]: Starting PackageKit Daemon...
-- Subject: Unit packagekit.service has begun start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
--
-- Unit packagekit.service has begun starting up.
Jun 02 04:01:26 s2-vm-02-39 PackageKit[29738]: daemon start
Jun 02 04:01:26 s2-vm-02-39 PackageKit[29738]: daemon quit
Jun 02 04:01:26 s2-vm-02-39 systemd[1]: Started PackageKit Daemon.
-- Subject: Unit packagekit.service has finished start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
--
-- Unit packagekit.service has finished starting up.
--
-- The start-up result is done.
lines 4556-4599/4599 (END)
```



修改 `/etc/supervisord.conf	`

用于配置supervisor管理的**进程的配置文件**



```shell
; Sample supervisor config file.
;
; For more information on the config file, please see:
; http://supervisord.org/configuration.html
;
; Notes:
;  - Shell expansion ("~" or "$HOME") is not supported.  Environment
;    variables can be expanded using this syntax: "%(ENV_HOME)s".
;  - Quotes around values are not supported, except in the case of
;    the environment= options as shown below.
;  - Comments must have a leading space: "a=b ;comment" not "a=b;comment".
;  - Command will be truncated if it looks like a config file comment, e.g.
;    "command=bash -c 'foo ; bar'" will truncate to "command=bash -c 'foo ".

[unix_http_server]
file=/var/run/supervisor.sock   ; the path to the socket file
;chmod=0700                 ; socket file mode (default 0700)
;chown=nobody:nogroup       ; socket file uid:gid owner
;username=user              ; default is no username (open server)
;password=123               ; default is no password (open server)

[inet_http_server]         ; inet (TCP) server disabled by default
port=127.0.0.1:9001        ; ip_address:port specifier, *:port for all iface
;username=user              ; default is no username (open server)
;password=123               ; default is no password (open server)

[supervisord]
logfile=/var/log/supervisord.log ; main log file; default $CWD/supervisord.log
logfile_maxbytes=50MB        ; max main logfile bytes b4 rotation; default 50MB
logfile_backups=10           ; # of main logfile backups; 0 means none, default 10
loglevel=info                ; log level; default info; others: debug,warn,trace
pidfile=/var/run/supervisord.pid ; supervisord pidfile; default supervisord.pid
nodaemon=false               ; start in foreground if true; default false
minfds=1024                  ; min. avail startup file descriptors; default 1024
minprocs=200                 ; min. avail process descriptors;default 200
;umask=022                   ; process file creation umask; default 022
;user=chrism                 ; default is current user, required if root
;identifier=supervisor       ; supervisord identifier, default is 'supervisor'
;directory=/tmp              ; default is not to cd during start
;nocleanup=true              ; don't clean up tempfiles at start; default false
;childlogdir=/tmp            ; 'AUTO' child log dir, default $TEMP
;environment=KEY="value"     ; key value pairs to add to environment
;strip_ansi=false            ; strip ansi escape codes in logs; def. false

; The rpcinterface:supervisor section must remain in the config file for
; RPC (supervisorctl/web interface) to work.  Additional interfaces may be
; added by defining them in separate [rpcinterface:x] sections.

[rpcinterface:supervisor]
supervisor.rpcinterface_factory = supervisor.rpcinterface:make_main_rpcinterface

; The supervisorctl section configures how supervisorctl will connect to
; supervisord.  configure it match the settings in either the unix_http_server
; or inet_http_server section.

[supervisorctl]
serverurl=unix:///var/run/supervisor.sock ; use a unix:// URL  for a unix socket
;serverurl=http://127.0.0.1:9001 ; use an http:// url to specify an inet socket
;username=chris              ; should be same as in [*_http_server] if set
;password=123                ; should be same as in [*_http_server] if set
;prompt=mysupervisor         ; cmd line prompt (default "supervisor")
;history_file=~/.sc_history  ; use readline history if available

; The sample program section below shows all possible program subsection values.
; Create one or more 'real' program: sections to be able to control them under
; supervisor.

;[program:theprogramname]
;command=/bin/cat              ; the program (relative uses PATH, can take args)
;process_name=%(program_name)s ; process_name expr (default %(program_name)s)
;numprocs=1                    ; number of processes copies to start (def 1)
;directory=/tmp                ; directory to cwd to before exec (def no cwd)
;umask=022                     ; umask for process (default None)
;priority=999                  ; the relative start priority (default 999)
;autostart=true                ; start at supervisord start (default: true)
;startsecs=1                   ; # of secs prog must stay up to be running (def. 1)
;startretries=3                ; max # of serial start failures when starting (default 3)
;autorestart=unexpected        ; when to restart if exited after running (def: unexpected)
;exitcodes=0,2                 ; 'expected' exit codes used with autorestart (default 0,2)
;stopsignal=QUIT               ; signal used to kill process (default TERM)
;stopwaitsecs=10               ; max num secs to wait b4 SIGKILL (default 10)
;stopasgroup=false             ; send stop signal to the UNIX process group (default false)
;killasgroup=false             ; SIGKILL the UNIX process group (def false)
;user=chrism                   ; setuid to this UNIX account to run the program
;redirect_stderr=true          ; redirect proc stderr to stdout (default false)
;stdout_logfile=/a/path        ; stdout log path, NONE for none; default AUTO
;stdout_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
;stdout_logfile_backups=10     ; # of stdout logfile backups (0 means none, default 10)
;stdout_capture_maxbytes=1MB   ; number of bytes in 'capturemode' (default 0)
;stdout_events_enabled=false   ; emit events on stdout writes (default false)
;stderr_logfile=/a/path        ; stderr log path, NONE for none; default AUTO
;stderr_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
;stderr_logfile_backups=10     ; # of stderr logfile backups (0 means none, default 10)
;stderr_capture_maxbytes=1MB   ; number of bytes in 'capturemode' (default 0)
;stderr_events_enabled=false   ; emit events on stderr writes (default false)
;environment=A="1",B="2"       ; process environment additions (def no adds)
;serverurl=AUTO                ; override serverurl computation (childutils)

; The sample eventlistener section below shows all possible eventlistener
; subsection values.  Create one or more 'real' eventlistener: sections to be
; able to handle event notifications sent by supervisord.

;[eventlistener:theeventlistenername]
;command=/bin/eventlistener    ; the program (relative uses PATH, can take args)
;process_name=%(program_name)s ; process_name expr (default %(program_name)s)
;numprocs=1                    ; number of processes copies to start (def 1)
;events=EVENT                  ; event notif. types to subscribe to (req'd)
;buffer_size=10                ; event buffer queue size (default 10)
;directory=/tmp                ; directory to cwd to before exec (def no cwd)
;umask=022                     ; umask for process (default None)
;priority=-1                   ; the relative start priority (default -1)
;autostart=true                ; start at supervisord start (default: true)
;startsecs=1                   ; # of secs prog must stay up to be running (def. 1)
;startretries=3                ; max # of serial start failures when starting (default 3)
;autorestart=unexpected        ; autorestart if exited after running (def: unexpected)
;exitcodes=0,2                 ; 'expected' exit codes used with autorestart (default 0,2)
;stopsignal=QUIT               ; signal used to kill process (default TERM)
;stopwaitsecs=10               ; max num secs to wait b4 SIGKILL (default 10)
;stopasgroup=false             ; send stop signal to the UNIX process group (default false)
;killasgroup=false             ; SIGKILL the UNIX process group (def false)
;user=chrism                   ; setuid to this UNIX account to run the program
;redirect_stderr=false         ; redirect_stderr=true is not allowed for eventlisteners
;stdout_logfile=/a/path        ; stdout log path, NONE for none; default AUTO
;stdout_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
;stdout_logfile_backups=10     ; # of stdout logfile backups (0 means none, default 10)
;stdout_events_enabled=false   ; emit events on stdout writes (default false)
;stderr_logfile=/a/path        ; stderr log path, NONE for none; default AUTO
;stderr_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
;stderr_logfile_backups=10     ; # of stderr logfile backups (0 means none, default 10)
;stderr_events_enabled=false   ; emit events on stderr writes (default false)
;environment=A="1",B="2"       ; process environment additions
;serverurl=AUTO                ; override serverurl computation (childutils)

; The sample group section below shows all possible group values.  Create one
; or more 'real' group: sections to create "heterogeneous" process groups.

;[group:thegroupname]
;programs=progname1,progname2  ; each refers to 'x' in [program:x] definitions
;priority=999                  ; the relative start priority (default 999)

; The [include] section can just contain the "files" setting.  This
; setting can list multiple files (separated by whitespace or
; newlines).  It can also contain wildcards.  The filenames are
; interpreted as relative to this file.  Included files *cannot*
; include files themselves.

[include]
files = /etc/supervisord.d/*.ini
```



例如：可以将`/etc/supervisord.conf`的最后一行改为：

```
[include]
files = /etc/supervisord.d/conf.d/*.conf /etc/supervisord.d/conf.d/*.ini /etc/supervisord.d/*.ini
```



接下来，将进程的配置文件，放到[include]指定的目录下。





# supervisor管理进程



- 停止进程方式：

每次查询，杀掉进程

```
netstat -tlnp |grep 9001
tcp        0      0 127.0.0.1:9001          0.0.0.0:*               LISTEN      30908/python
```



```
ps axu |grep  30908
root     30908  0.0  0.3 221804 12020 ?        Ss   04:21   0:00 /usr/bin/python /bin/supervisord -c /etc/supervisord.conf
root     31635  0.0  0.0 112780   732 pts/0    S+   04:33   0:00 grep --color=auto 30908
```



**netstat -tlnp**查询tcp端口

**ps axu**查询进程



- 开启进程方式：

```
sudo /usr/bin/supervisord -c /etc/supervisord.conf
```





# 遇到的问题有：

##### 1.问题一：

```
unix:///var/run/supervisor.sock refused connection
```



解决方法：

`include命名错误：`

```
/etc/supervisord.d/conf.d/
```

```
files = /etc/supervisor.d/conf.d/*.conf /etc/supervisor.d/conf.d/*.ini /etc/supervisord.d/*.ini
```





##### 2.问题二：

```
unix:///var/run/supervisor.sock no such file
```



解决方法：

- 自己手动创建：

```shell
This should solve this problem:

sudo touch /var/run/supervisor.sock
sudo chmod 777 /var/run/supervisor.sock
sudo service supervisor restart
```



启动`supervisors.conf` 

```shell
/usr/bin/supervisord -c /etc/supervisord.conf
```

- 或者使用绝对路径：

```
[root@s2-vm-02-39 lina_work]# supervisorctl -c /etc/supervisord.conf
unix:///var/run/supervisor.sock no such file
supervisor>
[root@s2-vm-02-39 lina_work]# /usr/bin/supervisord -c /etc/supervisord.conf
```





##### 3.问题三

输出文件找不到

```
stdout_logfile = /var/log/lina/lina_test.log
```



解决方式，自己手动创建一个`touch`





4. ##### 问题四：

java.nio.file.NoSuchFileException: /tmp/lina_work

```
21-06-02 07:27:16 INFO  c.b.l.l.Lina$:31 - parsing config application_test.conf...
2021-06-02 07:27:16 INFO  c.b.l.l.Lina$:42 - parse config OK: pos /opt/lina/position.test.save, dir /tmp/lina_work remote addresses a1: 172.18.2.53:5002
2021-06-02 07:27:16 INFO  c.b.l.l.Lina$:49 - blacklist is: SimpleConfigObject({"index":1,"list":["p9-xg.byteimg.com","p9-hs.byteimg.com","v9-dy.ixigua.com","p9-dy.byteimg.com","p9-tt.byteimg.com","p9-dy.bytecdn.cn","v9-dy-y.ixigua.com","v9-dy-z.ixigua.com","v9-dy-x.ixigua.com","v9-hs.ixigua.com","v9-tt.ixigua.com","v9-default.ixigua.com","v9-vvideov.ixigua.com","v9-ollqv.ixigua.com","v9-vllqv.ixigua.com","v9-dy-ipv6.ixigua.com","v9-vvideosv.ixigua.com","v9-ovideov.ixigua.com","v9-dy-a-x.bytecdn.cn","v9-dy-b-x.bytecdn.cn","v9-dy-c-x.bytecdn.cn","v9-dy-b-x.ixigua.com","p9-tt-ipv6.byteimg.com","p9-dy-ipv6.byteimg.com","f.video.weibocdn.com","st.dl.eccdnx.com","st.dl.pinyuncloud.com","v9.tiktokcdn.com","v9.muscdn.com","v9-vcheckout.muscdn.com","v9-in.tiktokcdn.com","lf9-tk-tos.tiktokcdn.com","p9-image-va-diancha-os.jiyunhudong.com","p9-image-sg-diancha-os.jiyunhudong.com","p9-image-diancha-os.jiyunhudong.com","v9-ph.tiktokcdn.com"]}).
2021-06-02 07:27:16 INFO  c.b.l.l.Lina$:55 - starting metric...
2021-06-02 07:27:16 ERROR c.b.l.l.Metric$:45 - file not exeists /tmp/hostname.conf
2021-06-02 07:27:16 INFO  c.b.l.l.Metric:78 - hostname is s2-vm-02-39
2021-06-02 07:27:16 INFO  c.b.l.l.Lina$:57 - metric started.
2021-06-02 07:27:16 INFO  c.b.l.l.Lina$:62 - starting file manager
2021-06-02 07:27:16 INFO  c.b.l.l.FileMan:47 - watching directory /tmp/lina_work.
2021-06-02 07:27:16 ERROR c.b.l.l.FileMan:57 - failed to watch dir /tmp/lina_work, retry in 10s.
java.nio.file.NoSuchFileException: /tmp/lina_work
	at sun.nio.fs.UnixException.translateToIOException(UnixException.java:86)
	at sun.nio.fs.UnixException.asIOException(UnixException.java:111)
	at sun.nio.fs.LinuxWatchService$Poller.implRegister(LinuxWatchService.java:246)
	at sun.nio.fs.AbstractPoller.processRequests(AbstractPoller.java:260)
	at sun.nio.fs.LinuxWatchService$Poller.run(LinuxWatchService.java:364)
	at java.lang.Thread.run(Thread.java:748)
2021-06-02 07:27:26 ERROR c.b.l.l.FileMan:57 - failed to watch dir /tmp/lina_work, retry in 10s.
java.nio.file.NoSuchFileException: /tmp/lina_work
	at sun.nio.fs.UnixException.translateToIOException(UnixException.java:86)
	at sun.nio.fs.UnixException.asIOException(UnixException.java:111)
	at sun.nio.fs.LinuxWatchService$Poller.implRegister(LinuxWatchService.java:246)
	at sun.nio.fs.AbstractPoller.processRequests(AbstractPoller.java:260)
	at sun.nio.fs.LinuxWatchService$Poller.run(LinuxWatchService.java:364)
	at java.lang.Thread.run(Thread.java:748)
```



解决方案，

创建两个文件夹**/tmp/lina_work**







# 遇见问题的其它问题：



##### ERROR(no such process)

第一次解决方案：

```
[unix_http_server]
file=/var/run/supervisor.sock   ; the path to the socket file
```

结果：无果





**根本原因**，是自己用错程序，在**ansible**中不应该使用supervisorctl而应该使用supervisord，这样就成功了。



![image-20210618133905286](https://tva1.sinaimg.cn/large/008i3skNly1grmd5krxp7j30hs0baq8e.jpg)



最后成功





查看superviosr是否在运行方式

```
ps aux | grep supervisord
```

或者通过

```
supervisorctl查看
```



解决问题的文档：

https://liyangliang.me/posts/2015/06/using-supervisor/



```
[include]
files = /etc/supervisord.d/conf.d/*.conf /etc/supervisord.d/conf.d/*.ini /etc/supervisord.d/*.ini
; files = supervisord.d/*.ini
```

路径：

```
/etc/supervisord.d/conf.d
```





#  结果：



lina日志：

```
2021-06-03 06:47:55 INFO  c.b.l.l.Lina$:31 - parsing config application_test.conf...
2021-06-03 06:47:55 INFO  c.b.l.l.Lina$:42 - parse config OK: pos /opt/lina/position.test.save, dir /tmp/lina_work remote addresses a1: 172.18.2.53:5002
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:49 - blacklist is: SimpleConfigObject({"index":1,"list":["p9-xg.byteimg.com","p9-hs.byteimg.com","v9-dy.ixigua.com","p9-dy.byteimg.com","p9-tt.byteimg.com","p9-dy.bytecdn.cn","v9-dy-y.ixigua.com","v9-dy-z.ixigua.com","v9-dy-x.ixigua.com","v9-hs.ixigua.com","v9-tt.ixigua.com","v9-default.ixigua.com","v9-vvideov.ixigua.com","v9-ollqv.ixigua.com","v9-vllqv.ixigua.com","v9-dy-ipv6.ixigua.com","v9-vvideosv.ixigua.com","v9-ovideov.ixigua.com","v9-dy-a-x.bytecdn.cn","v9-dy-b-x.bytecdn.cn","v9-dy-c-x.bytecdn.cn","v9-dy-b-x.ixigua.com","p9-tt-ipv6.byteimg.com","p9-dy-ipv6.byteimg.com","f.video.weibocdn.com","st.dl.eccdnx.com","st.dl.pinyuncloud.com","v9.tiktokcdn.com","v9.muscdn.com","v9-vcheckout.muscdn.com","v9-in.tiktokcdn.com","lf9-tk-tos.tiktokcdn.com","p9-image-va-diancha-os.jiyunhudong.com","p9-image-sg-diancha-os.jiyunhudong.com","p9-image-diancha-os.jiyunhudong.com","v9-ph.tiktokcdn.com"]}).
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:55 - starting metric...
2021-06-03 06:47:56 INFO  c.b.l.l.Metric:78 - hostname is s2-vm-02-39
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:57 - metric started.
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:62 - starting file manager
2021-06-03 06:47:56 INFO  c.b.l.l.FileMan:47 - watching directory /tmp/lina_work.
2021-06-03 06:47:56 INFO  c.b.l.l.FileMan:54 - watch /tmp/lina_work OK.
2021-06-03 06:47:56 INFO  c.b.l.l.FileMan:164 - file /tmp/lina_work/test.log not exists.
2021-06-03 06:47:56 INFO  c.b.l.l.FileMan:146 - Read dir ok, 0 files left in /tmp/lina_work, in process 0 in queue 0 bad 0 to resume Map() registry 0
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:64 - file manager started.
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:66 - starting flume
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:70 - sampler config is: SamplerConf(1,8,Map(hw.a.yximgs.com -> List(3, 5), hw6.a.yximgs.com -> List(3, 5), hw6.a.kwimgs.com -> List(3, 5), hw2.a.kwimgs.com -> List(3, 5)))
2021-06-03 06:47:56 INFO  c.b.l.l.FlumeSink:80 - properties of a1 {compression-type=deflate, compression-level=6, ssl=false, connect-timeout=60000, hosts.172.18.2.53=172.18.2.53:5002, hosts=172.18.2.53, truststore=/opt/lina/ssl/flumeTruststore.jks, maxIoWorkers=2, truststore-type=JKS, request-timeout=60000, batch-size=50}
2021-06-03 06:47:56 INFO  c.b.l.l.FlumeSink:94 - connecting flume...
2021-06-03 06:47:56 INFO  c.b.l.l.FlumeSink:230 - flume a1 connected.
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:147 - staring upload stream...
2021-06-03 06:47:56 INFO  c.b.l.l.Lina$:166 - stream is running
2021-06-03 06:48:01 ERROR c.b.l.l.Metric:109 - failed to update metric , akka.stream.StreamTcpException: Tcp command [Connect(127.0.0.1:10699,None,List(),Some(10 seconds),true)] failed because of Connection refused [{"metric":"log_lina","endpoint":"s2-vm-02-39","value":0,"tags":"confVer=0.1,sites=a1","fields":"tx_rate=0.000,err_num=0,file_left=0,bytes=0,lines=0,drop=0","timestamp":1622717220,"counterType":"GAUGE","step":60}]
```



lina配置文件：

```
[root@s2-vm-02-39 conf.d]# cat lina_test.conf
[program:lina_test]
environment=MALLOC_ARENA_MAX=1
command = java -Djava.net.preferIPv4Stack=true -Xmx128m -XX:-UseGCOverheadLimit -XX:+UseConcMarkSweepGC -jar /opt/lina/Lina.jar application_test.conf
directory = /opt/lina/
stopasgroup = true
autostart = true
autorestart = true
startsecs = 10
startretries = 3
user = root
redirect_stderr=true
stdout_logfile = /var/log/lina/lina_test.log
```







# 使用 supervisor 管理进程

Supervisor ([http://supervisord.org](http://supervisord.org/)) 是一个用 Python 写的进程管理工具，可以很方便的用来启动、重启、关闭进程（不仅仅是 Python 进程）。除了对单个进程的控制，还可以同时启动、关闭多个进程，比如很不幸的服务器出问题导致所有应用程序都被杀死，此时可以用 supervisor 同时启动所有应用程序而不是一个一个地敲命令启动。

## 安装

Supervisor 可以运行在 Linux、Mac OS X 上。如前所述，supervisor 是 Python 编写的，所以安装起来也很方便，可以直接用 pip :

```bash
sudo pip install supervisor
```

如果是 Ubuntu 系统，还可以使用 apt-get 安装。

## supervisord 配置

Supervisor 相当强大，提供了很丰富的功能，不过我们可能只需要用到其中一小部分。安装完成之后，可以编写配置文件，来满足自己的需求。为了方便，我们把配置分成两部分：supervisord（supervisor 是一个 C/S 模型的程序，这是 server 端，对应的有 client 端：supervisorctl）和应用程序（即我们要管理的程序）。

首先来看 supervisord 的配置文件。安装完 supervisor 之后，可以运行`echo_supervisord_conf` 命令输出默认的配置项，也可以重定向到一个配置文件里：

```bash
echo_supervisord_conf > /etc/supervisord.conf
```

去除里面大部分注释和“不相关”的部分，我们可以先看这些配置：

```ini
[unix_http_server]
file=/tmp/supervisor.sock   ; UNIX socket 文件，supervisorctl 会使用
;chmod=0700                 ; socket 文件的 mode，默认是 0700
;chown=nobody:nogroup       ; socket 文件的 owner，格式： uid:gid

;[inet_http_server]         ; HTTP 服务器，提供 web 管理界面
;port=127.0.0.1:9001        ; Web 管理后台运行的 IP 和端口，如果开放到公网，需要注意安全性
;username=user              ; 登录管理后台的用户名
;password=123               ; 登录管理后台的密码

[supervisord]
logfile=/tmp/supervisord.log ; 日志文件，默认是 $CWD/supervisord.log
logfile_maxbytes=50MB        ; 日志文件大小，超出会 rotate，默认 50MB
logfile_backups=10           ; 日志文件保留备份数量默认 10
loglevel=info                ; 日志级别，默认 info，其它: debug,warn,trace
pidfile=/tmp/supervisord.pid ; pid 文件
nodaemon=false               ; 是否在前台启动，默认是 false，即以 daemon 的方式启动
minfds=1024                  ; 可以打开的文件描述符的最小值，默认 1024
minprocs=200                 ; 可以打开的进程数的最小值，默认 200

; the below section must remain in the config file for RPC
; (supervisorctl/web interface) to work, additional interfaces may be
; added by defining them in separate rpcinterface: sections
[rpcinterface:supervisor]
supervisor.rpcinterface_factory = supervisor.rpcinterface:make_main_rpcinterface

[supervisorctl]
serverurl=unix:///tmp/supervisor.sock ; 通过 UNIX socket 连接 supervisord，路径与 unix_http_server 部分的 file 一致
;serverurl=http://127.0.0.1:9001 ; 通过 HTTP 的方式连接 supervisord

; 包含其他的配置文件
[include]
files = relative/directory/*.ini    ; 可以是 *.conf 或 *.ini
```

我们把上面这部分配置保存到 /etc/supervisord.conf（或其他任意有权限访问的文件），然后启动 supervisord（通过 -c 选项指定配置文件路径，如果不指定会按照这个顺序查找配置文件：$CWD/supervisord.conf, $CWD/etc/supervisord.conf, /etc/supervisord.conf）：

```bash
supervisord -c /etc/supervisord.conf
```

查看 supervisord 是否在运行：

```bash
ps aux | grep supervisord
```

## program 配置

上面我们已经把 supervisrod 运行起来了，现在可以添加我们要管理的进程的配置文件。这些配置可以都写到 supervisord.conf 文件里，如果应用程序很多，最好通过 include 的方式把不同的程序（组）写到不同的配置文件里。

为了举例，我们新建一个目录 /etc/supervisor/ 用于存放这些配置文件，相应的，把 /etc/supervisord.conf 里 include 部分的的配置修改一下：

```
[include]
files = /etc/supervisor/*.conf
```

假设有个用 Flask 开发的用户系统 usercenter, 生产环境使用 gunicorn 运行。项目代码位于 `/home/leon/projects/usercenter`，WSGI 对象位于 wsgi.py。在命令行启动的方式是这样的：

```bash
cd /home/leon/projects/usercenter
gunicorn -w 8 -b 0.0.0.0:17510 wsgi:app
```

对应的配置文件可能是：

```ini
[program:usercenter]
directory = /home/leon/projects/usercenter ; 程序的启动目录
command = gunicorn -w 8 -b 0.0.0.0:17510 wsgi:app  ; 启动命令
autostart = true     ; 在 supervisord 启动的时候也自动启动
startsecs = 5        ; 启动 5 秒后没有异常退出，就当作已经正常启动了
autorestart = true   ; 程序异常退出后自动重启
startretries = 3     ; 启动失败自动重试次数，默认是 3
user = leon          ; 用哪个用户启动
redirect_stderr = true  ; 把 stderr 重定向到 stdout，默认 false
stdout_logfile_maxbytes = 20MB  ; stdout 日志文件大小，默认 50MB
stdout_logfile_backups = 20     ; stdout 日志文件备份数
; stdout 日志文件，需要注意当指定目录不存在时无法正常启动，所以需要手动创建目录（supervisord 会自动创建日志文件）
stdout_logfile = /data/logs/usercenter_stdout.log
```

其中 `[program:usercenter]` 中的 `usercenter` 是应用程序的唯一标识，不能重复。对该程序的所有操作（start, restart 等）都通过名字来实现。

### Tips 1: Python 环境

有两种方式指定程序使用的 Python 环境：

1. `command` 使用绝对路径。假设使用 pyenv 来管理 Python 环境，上面例子中的 gunicorn 路径可以替换为 `/home/leon/.pyenv/versions/usercenter/bin/gunicorn`. 这种方式一目了然，推荐。
2. 通过 `environment` 配置 `PYTHONPATH`. `environment=PYTHONPATH=$PYTHONPATH:/home/leon/.pyenv/versions/usercenter/bin/`. `environment` 这个配置项非常有用，可以用来给程序传入环境变量。

### Tips 2: 后台进程

Supervisor 只能管理在前台运行的程序，所以如果应用程序有后台运行的选项，需要关闭。

### Tips 3: 子进程

有时候用 Supervisor 托管的程序还会有子进程（如 Tornado），如果只杀死主进程，子进程就可能变成孤儿进程。通过这两项配置来确保所有子进程都能正确停止：

```ini
stopasgroup=true
killasgroup=true
```

## 使用 supervisorctl

Supervisorctl 是 supervisord 的一个命令行客户端工具，启动时需要指定与 supervisord 使用同一份配置文件，否则与 supervisord 一样按照顺序查找配置文件。

```bash
supervisorctl -c /etc/supervisord.conf
```

上面这个命令会进入 supervisorctl 的 shell 界面，然后可以执行不同的命令了：

```bash
> status    # 查看程序状态
> stop usercenter   # 关闭 usercenter 程序
> start usercenter  # 启动 usercenter 程序
> restart usercenter    # 重启 usercenter 程序
> reread    ＃ 读取有更新（增加）的配置文件，不会启动新添加的程序
> update    ＃ 重启配置文件修改过的程序
```

上面这些命令都有相应的输出，除了进入 supervisorctl 的 shell 界面，也可以直接在 bash 终端运行：

```bash
$ supervisorctl status
$ supervisorctl stop usercenter
$ supervisorctl start usercenter
$ supervisorctl restart usercenter
$ supervisorctl reread
$ supervisorctl update
```

## 其它

除了 supervisorctl 之外，还可以配置 supervisrod 启动 web 管理界面，这个 web 后台使用 Basic Auth 的方式进行身份认证。

除了单个进程的控制，还可以配置 group，进行分组管理。

经常查看日志文件，包括 supervisord 的日志和各个 pragram 的日志文件，程序 crash 或抛出异常的信息一半会输出到 stderr，可以查看相应的日志文件来查找问题。

Supervisor 有很丰富的功能，还有其他很多项配置，可以在官方文档获取更多信息：http://supervisord.org/index.html







# 参考：

https://liyangliang.me/posts/2015/06/using-supervisor/

https://stackoverflow.com/questions/28145360/getting-error-on-supervison-on-supervisorctl-error-no-such-process



https://github.com/Supervisor/supervisor/issues/480



https://cloud.tencent.com/developer/article/1725966

