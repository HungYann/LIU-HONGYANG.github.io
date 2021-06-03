---
layout: post
author: LIU,HONGYANG
tags: [Linux]
---



# Linux scp命令



本地复制到远程

```sh
scp local_file remote_username@remote_ip:remote_folder 
```



远程复制到本地

```shell
scp root@www.runoob.com:/home/root/others/music /home/space/music/1.mp3 
```



复制目录命令格式：

```
scp -r local_folder remote_username@remote_ip:remote_folder
```







# Linux运行supervisor





安装supervisor：

参考：

https://blog.csdn.net/DongGeGe214/article/details/80264811



```sh
$ sudo su - #切换为root用户

# yum install epel-release
# yum install -y supervisor
# systemctl enable supervisord # 开机自启动
# systemctl start supervisord # 启动supervisord服务

# systemctl status supervisord # 查看supervisord服务状态
# ps -ef|grep supervisord # 查看是否存在supervisord进程
```



注意：不要和systemctl弄混，二者相互冲突



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



修改 */etc/supervisord.conf*  



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



最后一行改为：

```
[include]
files = /etc/supervisord.d/conf.d/*.conf /etc/supervisord.d/conf.d/*.ini /etc/supervisord.d/*.ini
```



**启动时，**



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





运行：

```
sudo /usr/bin/supervisord -c /etc/supervisord.conf
```





### 遇到的问题有：

1.

问题：

```
unix:///var/run/supervisor.sock refused connection
```



解决：

include命名错误：

```
/etc/supervisord.d/conf.d/
```



```
files = /etc/supervisor.d/conf.d/*.conf /etc/supervisor.d/conf.d/*.ini /etc/supervisord.d/*.ini
```





2.

问题：

```
unix:///var/run/supervisor.sock no such file
```



解决：

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

使用绝对路径：

```
[root@s2-vm-02-39 lina_work]# supervisorctl -c /etc/supervisord.conf
unix:///var/run/supervisor.sock no such file
supervisor>
[root@s2-vm-02-39 lina_work]# /usr/bin/supervisord -c /etc/supervisord.conf
```





3.

输出文件找不到

```
stdout_logfile = /var/log/lina/lina_test.log
```



解决方式，自己手动创建一个





4.

问题：

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



5.最终问题

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









### 参考：



https://stackoverflow.com/questions/28145360/getting-error-on-supervison-on-supervisorctl-error-no-such-process



https://github.com/Supervisor/supervisor/issues/480



https://cloud.tencent.com/developer/article/1725966