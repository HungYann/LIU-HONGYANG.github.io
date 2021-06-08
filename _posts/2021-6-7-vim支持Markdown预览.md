---
layout: post
author: LIU,HONGYANG
tags: [Vim]
---



##### 1.配置vim-plug



注意：nvim的路径和vim不同

```
curl -fLo ~/.vim/autoload/plug.vim --create-dirs \
https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
```



##### 2.安装插件



```
Plug 'iamcco/mathjax-support-for-mkdp'
Plug 'iamcco/markdown-preview.vim'
```





##### 3.配置快捷键盘



```
nmap <silent> <F8> <Plug>MarkdownPreview        " for normal mode
imap <silent> <F8> <Plug>MarkdownPreview        " for insert mode
nmap <silent> <F9> <Plug>StopMarkdownPreview    " for normal mode
imap <silent> <F9> <Plug>StopMarkdownPreview    " for insert mode
```





References:

https://zhuanlan.zhihu.com/p/35536223