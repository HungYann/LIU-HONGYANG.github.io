---
layout: post
author: LIU,HONGYANG
tags: [Git]
---





设置SSH Key**

Github上连接已有仓库时的认证，是通过使用SSH的公开密钥

在终端terminal中输入

*ssh-keygen*

该命令的含义是 generate ssh key, 然后一直按回车键即可。



![image-20200609152047076](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfm2c1j9faj30oo0ioabv.jpg)





 

在cd 到 .ssh目录下：

可以看到id_rsa文件是私有密钥，id_rsa.pub是公开密钥

 

查看id_rsa.pub内容，cat ~/.ssh/id_rsa.pub

点击SSH and GPG keys的目录， 在github栏目Key的位置输入明钥内容



![image-20200609152112201](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfm2ckfnozj31yz0u0n3x.jpg)



 

 

添加完成后，如下图所示：



![image-20200609152142004](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfm2cznfjpj31z40jyqa2.jpg)



 

**验证**

完成以上设置后，开始使用本地私人密钥和Github进行认证通信

在本地终端输入：

*ssh -T git@github.com*

 

出现下图所示内容，表示认证成功！



![image-20200609152206182](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfm2deva92j31qi05y40w.jpg) 