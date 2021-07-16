---
layout: post
author: LIU,HONGYANG
tags: [环境配置]
---



# scp、rsync 和ssh的使用

### 安全拷贝



方法一：`scp`可以实现服务器之间数据安全拷贝

```shell
scp -r $dir/$name username@host:/$dir/$name
```

方法二：
同步命令
```shell
rsync -av test/ username@host:/root/test/
```
### ssh免秘登陆

- 1.ssh-key-gen生成密钥对

- 2.拷贝到B服务器上

- 3.ssh访问B服务器（数据用私钥A加密）

- 4.接收到数据后，去授权key中查找A的公钥，并解密数据（授权key authorized_keys）

- 5.采用A公钥加密的数据返回A

- 6.接收到数据后，用A的私钥解数据

生成免密登陆的方式：

1.生成密钥
```shell
ssh-keyegn
```
2.复制到目标机器

```shell
ssh-copy-id host
```

3.免秘访问
```shell
ssh host
```

查看authorized_keys

### ansible脚本生成ssh免秘登陆

1.进入`.ssh/`目录，使用`ssh-keygen`生成密钥

2.`Enter file in which to save the key` /home/.ssh/ansible

```shell
ls
- ansible
- ansible.pub
```

3.使用ssh-copy-id分发ansible

```shell
ssh-copy-id -i .ssh/ansible host
```

4.访问

```shell
ssh -i .ssh/ansible host
```

5.执行

```shell
ansible web1 -m ping -i inventory.ini --private-key=/home/osboxes/.ssh/ansible
```