---
layout: post
author: LIU,HONGYANG
tags: [Scala]
---





如何使用**systemctl** 进行配置？

1.移动文件

conf到`/usr/lib/systemd`文件目录下



2.启动运行：

```
systemctl start connectors-agglog-yundun.service
```





```
connectors-agglog-yundun.service            loaded failed failed    JVM Application Instance of connectors-agglo
```



```
## Systemd Service Unit File for JVM Applications Service       ##
#   Rendered by Ansible, last modify @ 2018.07.05                #
#                   Initial written by hao.feng@baishancloud.com #

[Unit]
Description=JVM Application Instance of connectors-agglog-yundun
Wants=network-online.target
After=network-online.target
After=syslog.target

[Service]
WorkingDirectory=/opt/aggyunduntest/white-peak/data/connectors-agglog/yundun
EnvironmentFile=-/etc/sysconfig/connectors-agglog-yundun
SyslogIdentifier=connectors-agglog-yundun
ExecStart=/usr/bin/java $JAVA_OPTIONS $JVMAPP_MAINCLS $JVMAPP_JARARGS
ExecStop=/bin/kill -s TERM $MAINPID
Restart=on-failure
Type=simple
User=connectors-agglog-yundun

# See MAX_OPEN_FILES in sysconfig
LimitNOFILE=65535
# Specifies the maximum number of processes
LimitNPROC=2048
# Specifies the maximum memory of processes can lock
LimitMEMLOCK=infinity
# Connects standard output to /dev/null
StandardOutput=null
# Connects standard error to journal
StandardError=journal

[Install]
WantedBy=multi-user.target
```





##### 文件配置含义





```
  UNIT                             LOAD   ACTIVE SUB    DESCRIPTION
● connectors-agglog-yundun.service loaded failed failed JVM Application Instance of connectors-agglog-yundun

LOAD   = Reflects whether the unit definition was properly loaded.
ACTIVE = The high-level unit activation state, i.e. generalization of SUB.
SUB    = The low-level unit activation state, values depend on unit type.

1 loaded units listed. Pass --all to see loaded but inactive units, too.
To show all installed unit files use 'systemctl list-unit-files'.
```



```
[root@s2-vm-02-39 system]# systemctl is-failed connectors-agglog-yundun
failed
```



使用`status` 查看失败状态

```
[root@s2-vm-02-39 system]# systemctl status connectors-agglog-yundun
● connectors-agglog-yundun.service - JVM Application Instance of connectors-agglog-yundun
   Loaded: loaded (/usr/lib/systemd/system/connectors-agglog-yundun.service; disabled; vendor preset: disabled)
   Active: failed (Result: start-limit) since Mon 2021-06-21 06:58:36 EDT; 4min 6s ago
  Process: 15122 ExecStart=/usr/bin/java $JAVA_OPTIONS $JVMAPP_MAINCLS $JVMAPP_JARARGS (code=exited, status=217/USER)
 Main PID: 15122 (code=exited, status=217/USER)

Jun 21 06:58:35 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exit.../USER
Jun 21 06:58:35 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:58:35 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: start request repeated too quickly for connectors-agglog-yundun.service
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: Failed to start JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:58:36 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Hint: Some lines were ellipsized, use -l to show in full.
```



使用`journalctl -u [Unit]`查看日志失败原因

````
[root@s2-vm-02-39 system]# journalctl -u connectors-agglog-yundun.service
-- Logs begin at Mon 2021-06-21 11:01:44 EDT, end at Mon 2021-06-21 07:01:01 EDT. --
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:42 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service holdoff time over, scheduling restart.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Stopped JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: start request repeated too quickly for connectors-agglog-yundun.service
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Failed to start JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
Jun 21 06:35:43 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service failed.
Jun 21 06:47:41 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
Jun 21 06:47:41 s2-vm-02-39 systemd[1]: connectors-agglog-yundun.service: main process exited, code=exited, stat
Jun 21 06:47:41 s2-vm-02-39 systemd[1]: Unit connectors-agglog-yundun.service entered failed state.
````



配置

```
/etc/sysconfig
```



几个变量：



```
JVMAPP_JARARGS=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/agglog.conf
```



```
JAVA_OPTIONS=-Xmx4096M -Xms4096M -server -Dlogback.configurationFile=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/logback.xml
```



```
JVMAPP_MAINCLS=atiesh.Atiesh
```





cat connectors-agglog-yundun.service

```
## Systemd Service Unit File for JVM Applications Service       ##
#   Rendered by Ansible, last modify @ 2018.07.05                #
#                   Initial written by hao.feng@baishancloud.com #

[Unit]
Description=JVM Application Instance of connectors-agglog-yundun
Wants=network-online.target
After=network-online.target
After=syslog.target

[Service]
WorkingDirectory=/opt/aggyunduntest/white-peak/data/connectors-agglog/yundun
EnvironmentFile=/etc/sysconfig/connectors-agglog-yundun
SyslogIdentifier=connectors-agglog-yundun
ExecStart=/usr/bin/java $JAVA_OPTIONS $JVMAPP_MAINCLS $JVMAPP_JARARGS
ExecStop=/bin/kill -s TERM $MAINPID
Restart=on-failure
Type=simple
User=connectors-agglog-yundun

# See MAX_OPEN_FILES in sysconfig
LimitNOFILE=65535
# Specifies the maximum number of processes
LimitNPROC=2048
# Specifies the maximum memory of processes can lock
LimitMEMLOCK=infinity
# Connects standard output to /dev/null
StandardOutput=null
# Connects standard error to journal
StandardError=journal

[Install]
WantedBy=multi-user.target
```



```
connectors-agglog-yundun.service           loaded active running   JVM Application Instance of connectors-agg
```





配置成功

````
[root@s2-vm-02-39 system]# systemctl status connectors-agglog-yundun.service
● connectors-agglog-yundun.service - JVM Application Instance of connectors-agglog-yundun
   Loaded: loaded (/usr/lib/systemd/system/connectors-agglog-yundun.service; disabled; vendor preset: disabled)
   Active: active (running) since Mon 2021-06-21 21:52:51 EDT; 2s ago
 Main PID: 22311 (java)
   CGroup: /system.slice/connectors-agglog-yundun.service
           └─22311 /usr/bin/java -Xmx4096M -Xms4096M -server -Dlogback.configurationFile=/opt/aggyunduntest...

Jun 21 21:52:51 s2-vm-02-39 systemd[1]: Started JVM Application Instance of connectors-agglog-yundun.
````





##### 配置软链接，



首先找到配置文件service的路径，

```shell
/usr/lib/systemd/system/connectors-agglog-yundun.service
```



找到jar包：



```shell
/opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar
```



该包的原链接位置在于,

```
/opt/white-peak/releases/connectors-agglog-servicereceiver/log-connector-agglog_2.11.jar
```

实际位置在于：

```
/opt/aggyunduntest/white-peak/releases/connectors-agglog-yundun/log-connector-agglog_2.11.jar
```



删除原来的软链接：



重新添加一个软链接（需要：保持**软链接**的地址不发生变化）

```
[root@s2-vm-02-39 connectors-agglog-yundun]# ln -s log-connector-agglog_2.11.jar /opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar
ln: failed to create symbolic link ‘/opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar’: File exists
```



```
[root@s2-vm-02-39 yundun]# ln -s /opt/aggyunduntest/white-peak/releases/connectors-agglog-yundun/log-connector-agglog_2.11.jar /opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.ja
```







```

```



思路如下：

进入实际链接所在的包，对文件修改成和配置一样的软链接路径



##### 5.connectors-agglog-yundun(bash文件)



```
## Configuration File for JVM Applications Service              ##
#   Rendered by Ansible, last modify @ 2018.06.20                #
#                   Initial written by hao.feng@baishancloud.com #

# Set JVM ENV
JAVA_HOME=/etc/alternatives/java_sdk_1.8.0

# Set name of jvm application name
JVMAPP_NAME=connectors-agglog-yundun
JVMAPP_USER=connectors-agglog-yundun

# Set Paths
JVMAPP_HOMEDIR=/opt/aggyunduntest/white-peak/connectors-agglog/yundun
JVMAPP_DATADIR=/opt/aggyunduntest/white-peak/data/connectors-agglog/yundun
JVMAPP_LOGSDIR=/opt/aggyunduntest/white-peak/logs/connectors-agglog/yundun
JVMAPP_PIDFILE=/var/run/connectors-agglog-yundun.pid

# Set Runtime
JVMAPP_MAINCLS=atiesh.Atiesh
JVMAPP_JARFILE=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar

# Java Options and Classpath
#   We don't use the var JVMAPP_CLASSPATH here for compatible with systemd
#   because we also use this file as EnvironmentFile of systemd service unit
#   the same reason we put JVMAPP_JARFILE inside CLASSPATH.
JAVA_OPTIONS=-Xmx4096M -Xms4096M -server -Dlogback.configurationFile=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/logback.xml
CLASSPATH=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-format-java.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-format-scala_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-connector_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-connector-remotecfg_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-connector-filters_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/log-connector-tailog_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/caffeine.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-semantics-filesystem_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-semantics-aliyun_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-semantics-http_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-semantics-kafka_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-semantics-syslog_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh-utils-http_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/atiesh_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-java8-compat_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-parser-combinators_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-xml_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-library.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-reflect.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-actor_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-http-core_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-http_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-parsing_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-protobuf_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/akka-stream_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/config.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/scala-logging_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/ssl-config-core_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/reactive-streams.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/logback-classic.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/logback-core.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/slf4j-api.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/json4s-ast_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/json4s-core_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/json4s-native_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/json4s-scalap_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/kamon-core_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/kamon-system-metrics_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/kamon-prometheus_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/nanohttpd.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/oshi-core.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/kafka-clients.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/kafka_2.11.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/zookeeper.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/zkclient.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/metrics-core.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/aliyun-log-producer.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/aliyun-log.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/fastjson.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/happy-dns-java.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/qiniu-java-sdk.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jsr305.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/gson.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/error_prone_annotations.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/failureaccess.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/guava.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/listenablefuture.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/j2objc-annotations.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/protobuf-java.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-beanutils.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-codec.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-collections.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-digester.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-lang.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-logging.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/commons-validator.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/httpclient.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/httpcore.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/syslog-java-client.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jackson-annotations.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jackson-core.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jackson-databind.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/okhttp.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/okio.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/paranamer.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jna-platform.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jna.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/lz4.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/ezmorph.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/jopt-simple.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/json-lib-jdk15.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/checker-qual.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/animal-sniffer-annotations.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/lz4-java.jar:/opt/aggyunduntest/white-peak/connectors-agglog/yundun/libs/snappy-java.jar
# JVM Start Arguments - Jar
JVMAPP_JARARGS=/opt/aggyunduntest/white-peak/connectors-agglog/yundun/agglog.conf
```



- 检查包路径是否正确

```shell
CLASSPATH=
/opt/aggyunduntest/white-peak/connectors-agglog/yundun/current.jar
```



- 修改配置文件

```
 vim /opt/aggyunduntest/white-peak/connectors-agglog/yundun/agglog.conf
```



主要修改两个地方：

metrics-basename， accept-topics

和默认的topic `topic = "mytopic`

```
  source {
        ## Http Source - Service Log Aggregator ##
        http-aggreator-service-src {
            # fqdn of source classname
            fqcn = "com.baishancloud.log.connector.agglog.source.YunDunJsonHttpAggregator"
            # fqdn of akka dispatcher name to use
            akka-dispatcher = "atiesh.dispatchers.source-blocking-io-dispatcher"

            # interceptors and sinks to use
            interceptors = ["transparent"]
            sinks = ["agglog-producer-service-sink"]
            metrics-basename = "metrics-basename"
            accept-topics = ["mytopic","yundunlog-test-a","yundunlog-test-b"]
            # sink selecting strategy
            sink-select-strategy = "first-accepted"

            # http listening
            listen-url = "http://0.0.0.0:13999"
            max-connections = 32
            # http source task dispatcher
            http-akka-dispatcher = "atiesh.dispatchers.source-agglog-service-fjp-dispatcher"
        }
    }
    
    
    
    agglog-producer-service-sink {
            fqcn = "com.baishancloud.log.connector.agglog.sink.KafkaDispatcher"
            akka-dispatcher = "atiesh.dispatchers.sink-blocking-io-dispatcher"

            # kafka producer topic
            topic = "mytopic"
            # infinite retry
            must-send = true
```





##### 开启Kafka producer

```
bin/kafka-console-producer.sh --broker-list localhost92 --topic mytopic
```



##### 开启Kafka consumer

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mytopic
```





##### 测试

accept-topics = ["mytopic","yundunlog-test-a","yundunlog-test-b"]

1.案例一：

```
echo '[{"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-1\"}"},
{"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-2\"}"}, 
{"headers": {"topic": "yundunlog-test-c"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



```
[guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-78] c.b.l.c.a.source.YunDunJsonHttpAggregator -  error type: <()>
```





-----





```
echo '[{"headers": {"topic": "mytopic2"}, "body": "{\"hostname\": \"ss-1\"}"}, {"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-2\"}"}, {"headers": {"topic": "yundunlog-test-c"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



```
{"hostname": "ss-4"}
{"hostname": "ss-2"}
{"hostname": "ss-1"}
{"hostname": "ss-3"}
```



```
[guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-59] c.b.l.c.a.source.YunDunJsonHttpAggregator -  error type: <()>
```



---

成功案例：

问题，默认指定的topic可能会有问题

["mytopic","yundunlog-test-a","yundunlog-test-b"]

```
echo '[{"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-1\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-2\"}"}, {"headers": {"topic": "yundunlog-test-c"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



```
{"hostname": "ss-3"}
{"hostname": "ss-1"}
{"hostname": "ss-4"}
```



```
echo '[{"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-11\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-21\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-31\"}"}, {"body": "{\"hostname\": \"ss-41\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



```
{"hostname": "ss-41"}
{"hostname": "ss-11"}
```



成功，但是会有WARN



```
[WARN ] [06/22/2021 02:29:45.819] [kafka-producer-network-thread | producer-1] org.apache.kafka.clients.NetworkClient - [Producer clientId=producer-1] Error while fetching metadata with correlation id 22 : {yundunlog-test-b=LEADER_NOT_AVAILABLE}
[WARN ] [06/22/2021 02:29:45.934] [kafka-producer-network-thread | producer-1] org.apache.kafka.clients.NetworkClient - [Producer clientId=producer-1] Error while fetching metadata with correlation id 25 : {yundunlog-test-a=LEADER_NOT_AVAILABLE}
```



---



```
echo '[{"headers": {"topic": "yundunlog-test-a"}, "body": "{\"hostname\": \"ss-1\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-2\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]'
```





##### 返回两个错误：



- 文件太大错误：

修改配置项目:`max-body-length`

```
echo '[{"headers": {"topic": "mytopic2"}, "body": "{\"hostname\": \"ss-111111111111111111111111111111111111111111111111111111111111111111111\"}"}, {"headers": {"topic": "mytopic"}, "body": "{\"hostname\": \"ss-222222222222222222222222aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"}"}, {"headers": {"topic": "yundunlog-test-c"}, "body": "{\"hostname\": \"ss-333333333333333333\"}"}, {"body": "{\"hostname\": \"ss-44444444444444\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



修改配置项后，需要重启，才能生效。因为可能无动态加载过程。

version 2

```
[WARN ] [06/22/2021 03:39:49.272] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-15] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src>  The content is too large and deserts it
```

version 3

```
[WARN ] [06/22/2021 06:21:10.937] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-12] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src> has been discarded, as the content is too large
```



version 4

```
[WARN ] [06/22/2021 23:02:38.543] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-14] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src> of the request has been discarded, as the content is too large
```







destroied it. because 



- 文件格式错误：



```
echo '[{"headers": ["topic": "yundunlog-test-a"}, "body": "{\"hostname\": \"ss-1\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-2\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```



version 1

```
[ERROR] [06/22/2021 03:05:02.082] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-194] c.b.l.c.a.source.YunDunJsonHttpAggregator -  error type: <()>
```

version 2

```
[WARN ] [06/22/2021 03:41:09.778] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-24] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src> The json format is not available
```

version 3

```
[WARN ] [06/22/2021 06:20:00.261] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-23] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src> has been discarded, as the json format is not available
```



version 4

```
[WARN ] [06/22/2021 23:03:12.924] [guardian-atiesh.dispatchers.source-agglog-service-fjp-dispatcher-21] c.b.l.c.a.source.YunDunJsonHttpAggregator - source <http-aggreator-service-src> of the request has been discarded, because of an unexpected exception
```







- 无法获取到header错误





```
echo '[{"": ["topic": "yundunlog-test-a"}, "body": "{\"hostname\": \"ss-1\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-2\"}"}, {"headers": {"topic": "yundunlog-test-b"}, "body": "{\"hostname\": \"ss-3\"}"}, {"body": "{\"hostname\": \"ss-4\"}"}]' | gzip | curl -vs --data-binary @- --no-buffer -H "Content-Encoding: gzip" -X "POST"  -i http://localhost:13999/
```





---

Discard the illegal format data of Cloud Shield



```
logger.warn(s"source <${getName}> has been discarded, as the content is too large")

logger.warn(s"source <${getName}> has been discarded, as the json format is not available")
```





修提交PR

```
cp /Users/liuhongyang/IdeaProjects/log-connector/connector-agglog/src/main/scala/com/baishancloud/log/connector/agglog/source/YunDunJsonHttpAggregator.scala ~/Github/log-connector/connector-agglog/src/main/scala/com/baishancloud/log/connector/agglog/source/
```

