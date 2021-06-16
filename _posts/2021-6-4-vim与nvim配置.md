---
layout: post
author: LIU,HONGYANG
tags: [Vim]
---



# 部分一：Vim环境搭建

教程如下：



- 安装vim-plug

[🦄 vim-plug 的安装和使用](https://segmentfault.com/a/1190000018089782)

- 安装coc.nvim (vim自动补全插件coc.nvim安装)

[vim自动补全插件coc.nvim安装](https://www.jianshu.com/p/55cf1fa7a467)

[neoclide/coc.nvim](https://github.com/neoclide/coc.nvim)

- vim-scala

[derekwyatt/vim-scala](https://github.com/derekwyatt/vim-scala)

- 其它常用插件auvbim

[Vim Awesome](https://vimawesome.com/)



```
curl -fLo ~/.config/nvim/autoload/plug.vim --create-dirs \
    https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
```



其中vim的文件配置路径如下：

```
 vim /Users/liuhongyang/.vimrc
```



# 部分二：配置Nvim



### 添加neovim:

##### 方式一：

https://github.com/neovim/neovim/wiki/Installing-Neovim

```shell
curl -LO https://github.com/neovim/neovim/releases/download/nightly/nvim-macos.tar.gz
tar xzf nvim-macos.tar.gz
./nvim-osx64/bin/curl -LO https://github.com/neovim/neovim/releases/download/nightly/nvim-macos.tar.gznvim
```



##### 方式二：(采用此方式)

```shell
brew install neovim
```



修改配置环境

```shell
nvim ~/.bash_profile
```



在文件中添加如下内容：

```
export PATH=$PATH:/usr/local/ant/bin
export PATH=$PATH:./nvim-osx64/bin
```





检查nvim配置

```
nvim +checkhealth
```



如果配置不支持Python语言，需要

```shell
pip install neovim --upgrade
```





### 插键vim-plug：



安装`vim-plug`脚本



``` shell
curl -fLo ~/.vim/autoload/plug.vim --create-dirs \
    https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
```



命令不生效的原因：

未将自己的配置生效



```sh
 source ~/.zshrc
```



自己的init.vim配置文件路径



```shell
call plug#begin('/Users/liuhongyang/.config/nvim/plugged')
Plug 'tpope/vimi-sensible'
call plug#end()
```





### 插件



注意，参考此文件时，[vim自动补全插件coc.nvim安装](https://www.jianshu.com/p/55cf1fa7a467)，需要将bash换成zsh

```shell
# 如果你的默认 shell 是 bash，请将 zsh 换成 bash
$ curl -L https://iterm2.com/misc/install_shell_integration.sh | zsh
```





