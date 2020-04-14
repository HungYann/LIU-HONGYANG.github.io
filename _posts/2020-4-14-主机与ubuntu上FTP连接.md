---
layout: post
author: LIU,HONGYANG
tags: [Linux]
---

本篇博文主要描述在虚拟机中安装FTP服务器，然后在Mac主机上连接虚拟机，传输文件





##### 1.下载FTP

Ubuntu上的FTP, vsftpd “very secure FTP daemong”



```shell
#卸载应用
sudo apt-get purge vsftpd
#重新安装
sudo apt-get install vsftpd
```





##### 2.创建新用户（可选）

```shell
sudo adduser newAccount
```



##### 3.修改配置文件



```shell
sudo gedit /etc/vsftpd.conf
```

在文件中添加如下代码：

```shell
userlist_deny=NO
userlist_enable=YES
userlist_file=/etc/allowed_users
seccomp_sandbox=NO
local_root=/home/liuhongyang/
local_enable=YES
write_enable=YES
utf8_filesystem=YES

```



##### 4.创建允许访问的用户列表



```shell
sudo gedit /etc/allowed_users
```



##### 5.检查禁止访问的用户名单



```shell
sudo vim /etc/ftpusers
```



##### 6.重启服务器

```
重启
sudo /etc/init.d/vsftpd restart
#启动
sudo /etc/init.d/vsftpd start
#停止
sudo /etc/init.d/vsftpd stop
```





##### 7.关闭防火墙



```shell
ufw disable
```

![Screenshot 2020-04-12 at 23.38.30](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdtowidkqmj30kk0aetbi.jpg)



##### 8.更改虚拟机配置



注意：改成Bridge模式



![image-20200414225251317](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdtop824gnj31040immzt.jpg)



##### 9.查看本机ip



```shell
ifconfig
```



##### 10.在浏览器中输入：

如：

```shell
ftp://10.0.2.15/
```



![Screenshot 2020-04-14 at 21.05.29](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdtowv10haj31z20mk0v8.jpg)



或者使用客户端：

`FileZilla`:



![Screenshot 2020-04-14 at 22.55.47](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdtowrse5uj318c0g2ju5.jpg)