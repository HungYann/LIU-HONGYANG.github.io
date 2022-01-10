---
layout: post
author: LIU,HONGYANG
tags: [Spark]
---

## intellij idea搭建Spark环境

### 遇到哪些问题

- maven使用idea自带的，无法判断是否成功下载包

- jdk版本过高（17），导致不兼容

- maven打包错误


### 解决方案

#### 问题一：使用idea自带的maven，无法判断是否成功安装

[下载地址](https://archive.apache.org/dist/maven/maven-3/)

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8s56pjcxj30eq0m376g.jpg)

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8rv2cskyj31tg03ijrz.jpg)

下载完成后，解压到自己创建的文件目录中，

- 创建Maven

- 解压下载文件到`/Users/liuhongyang/Documents/Maven`下

修改`/Users/liuhongyang/Documents/Maven/apache-maven-3.6.3/conf/settings.xml`内容，

- 修改`localRepository`内容:

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8sbiw2dfj30pv0azjtf.jpg)

- 添加地址:

```xml
<localRepository>/Users/liuhongyang/Documents/Maven/repository</localRepository>
```
Maven最终目录结构如下：
![](https://tva1.sinaimg.cn/large/008i3skNly1gy8se76hewj30sc03imxh.jpg)

最后，将自己配置的maven添加到idea中，
![](https://tva1.sinaimg.cn/large/008i3skNly1gy8sfv95enj315i0u0q6c.jpg)

此时问题一解决。


#### 问题二：jdk版本过高

自己使用了jdk 17的版本，之所以选择这么高的版本是因为，oracle没有针对mac m1芯片开发老版本的jdk

为了安装新版本jdk，自己首先卸载老版本jdk，mac卸载老版本jdk的方式参考如下：

https://explainjava.com/uninstall-java-macos/

接着，参考教程：https://www.winsonlo.com/it/howto/zulu-jdk8-on-m1/

下载[azul](https://www.azul.com/downloads/?version=java-8-lts&os=macos&package=jdk)版本的jdk

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8skip285j31470u0jvc.jpg)

注意一定要选择 `arm 64`版本的`dmg`文件，不要选错，否则mac将会调用Rosetta 2转译,性能下降

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8smwqqn5j313a0aoaap.jpg)

最后，参考[How to change the intellij idea jdk version](https://mkyong.com/intellij/how-to-change-the-intellij-idea-jdk-version/), 在project structure中修改java版本，即可完成jdk版本过高的修改


#### 问题三：maven打包错误


maven打包错误原因很多，首先贴上自己配置的pom.xml文件，
尤其需要注意scala版本，

- 首先确保scala版本用了2.12，也就是目前工业界常用的版本，如下：

![](https://tva1.sinaimg.cn/large/008i3skNly1gy8ss9owqdj319a0gowhx.jpg)

- 其次，需要用到ali镜像源，加快速度，只需要在project下一级中，添加如下镜像地址：

```xml
<repositories>
     <repository>
         <id>alimaven</id>
         <name>aliyun maven</name>
         <url>https://maven.aliyun.com/nexus/content/repositories/central/</url>
     </repository>
 </repositories>

```
点击此处，更新
![](https://tva1.sinaimg.cn/large/008i3skNly1gy8swc4nlhj315c0l4ju9.jpg)


- 最后，需要在项目中安装`scala`和`maven`的编译插件

```xml
<build>
       <plugins>
           <!-- 测试代码运行插件，可以在打包之前跳过test包下符合命名规范的所有类的代码 -->
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-surefire-plugin</artifactId>
               <version>2.22.2</version>
               <configuration>
                   <skipTests>true</skipTests>
               </configuration>
           </plugin>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-shade-plugin</artifactId>
               <version>3.2.4</version>
               <executions>
                   <execution>
                       <phase>package</phase>
                       <goals>
                           <goal>shade</goal>
                       </goals>
                       <configuration>
                           <artifactSet>
                               <excludes>
                                   <exclude>com.google.code.findbugs:jsr305</exclude>
                                   <exclude>org.slf4j:*</exclude>
                                   <exclude>log4j:*</exclude>
                               </excludes>
                           </artifactSet>
                           <filters>
                               <filter>
                                   <!-- Do not copy the signatures in the META-INF folder.
                                   Otherwise, this might cause SecurityExceptions when using the JAR. -->
                                   <artifact>*:*</artifact>
                                   <excludes>
                                       <exclude>META-INF/*.SF</exclude>
                                       <exclude>META-INF/*.DSA</exclude>
                                       <exclude>META-INF/*.RSA</exclude>
                                   </excludes>
                               </filter>
                           </filters>
                           <transformers>
                               <transformer
                                       implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                           </transformers>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
           <!-- java编译插件 -->
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-compiler-plugin</artifactId>
               <version>3.8.1</version>
               <executions>
                   <execution>
                       <phase>compile</phase>
                       <goals>
                           <goal>compile</goal>
                       </goals>
                   </execution>
               </executions>
           </plugin>

           <!-- scala编译插件 -->
           <plugin>
               <groupId>net.alchim31.maven</groupId>
               <artifactId>scala-maven-plugin</artifactId>
               <version>3.2.2</version>
               <executions>
                   <execution>
                       <goals>
                           <goal>compile</goal>
                           <goal>testCompile</goal>
                       </goals>
                   </execution>
               </executions>
               <configuration>
                   <scalaVersion>2.12.10</scalaVersion>
                   <args>
                       <arg>-target:jvm-1.8</arg>
                   </args>
               </configuration>
           </plugin>
       </plugins>
       <resources>
           <!--打包资源文件-->
           <resource>
               <directory>src/main/resources</directory>
               <includes>
                   <include>**</include>
               </includes>
           </resource>
       </resources>
   </build>

```
需要注意的是，如果编译插件有安装不成功的情况，可以用如下方式，手动下载

**例如：**
copy如下内容到dependency下，再进行手动同步，同步完成后，从dependency移除即可。

```xml
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>2.22.2</version>
```

最后，pom的全部配置如下，提供给搭建参考。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<!--    <parent>-->
<!--        <artifactId>atguigu-classes</artifactId>-->
<!--        <groupId>com.atguigu.bigdata</groupId>-->
<!--        <version>1.0.0</version>-->
<!--    </parent>-->
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.baishan</groupId>
    <artifactId>spark-core</artifactId>
    <version>1.0.0</version>

    <dependencies>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-library</artifactId>
            <version>2.12.10</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-yarn_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-sql_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.27</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-hive_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.hive</groupId>
            <artifactId>hive-exec</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-streaming_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-streaming-kafka-0-10_2.12</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <!-- 测试代码运行插件，可以在打包之前跳过test包下符合命名规范的所有类的代码 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <artifactSet>
                                <excludes>
                                    <exclude>com.google.code.findbugs:jsr305</exclude>
                                    <exclude>org.slf4j:*</exclude>
                                    <exclude>log4j:*</exclude>
                                </excludes>
                            </artifactSet>
                            <filters>
                                <filter>
                                    <!-- Do not copy the signatures in the META-INF folder.
                                    Otherwise, this might cause SecurityExceptions when using the JAR. -->
                                    <artifact>*:*</artifact>
                                    <excludes>
                                        <exclude>META-INF/*.SF</exclude>
                                        <exclude>META-INF/*.DSA</exclude>
                                        <exclude>META-INF/*.RSA</exclude>
                                    </excludes>
                                </filter>
                            </filters>
                            <transformers>
                                <transformer
                                        implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <!-- java编译插件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- scala编译插件 -->
            <plugin>
                <groupId>net.alchim31.maven</groupId>
                <artifactId>scala-maven-plugin</artifactId>
                <version>3.2.2</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <scalaVersion>2.12.10</scalaVersion>
                    <args>
                        <arg>-target:jvm-1.8</arg>
                    </args>
                </configuration>
            </plugin>
        </plugins>
        <resources>
            <!--打包资源文件-->
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**</include>
                </includes>
            </resource>
        </resources>
    </build>

    <repositories>
        <repository>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>https://maven.aliyun.com/nexus/content/repositories/central/</url>
        </repository>
    </repositories>

</project>

```

本文最后，真诚感谢我的同事自强大佬的教导，让我完成Spark的初始环境搭建。
