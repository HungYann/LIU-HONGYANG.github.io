---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---





>  InputFormat

- 对输入文件进行切分，形成多个`InputSplit`文件，每个`InputSplit`对应一个`map`,

- 创建`RecordReader`,从InputSplit分片中读取数据供map使用



![image-20200413160741632](https://tva1.sinaimg.cn/large/007S8ZIlgy1gds7df8cmcj31740eagmz.jpg)



InputFormat实现类：

> FileInputFormat:处理文件的InputFormat类



1.TextInputFormat:处理文件的InputFormat类

2.SequenceFileInputFormat:从sequenceFile读取，<k,v>键值对存放的文件

3.KeyValueInputFormat:读取普通文本文件，文件按照行分割，每一行由key和value组成，key和value的分隔符若没有指定，那么整行为key,value为空

4.NLineInputFormat:是可以将N行数据划分为一个Split,作为MapTask输入

5.CombineFileInputFormat:合并多个小文件成为一个分片



> DBInputFormat:

主要用于处理用于数据库的InputFormat类



##### 生成sequenceFile文件



生成SequenceFile有两种方法：一种是就的方法

```java
package com.datapig.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.net.URI;

public class SequenceFileReadDemo {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf);
        Path path = new Path(uri);

        SequenceFile.Reader reader = null;

        try {
            reader = new SequenceFile.Reader(fs,path,conf);
            Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(),conf);
            Writable value = (Writable)ReflectionUtils.newInstance(reader.getValueClass(),conf);

            long position = reader.getPosition();
            while(reader.next(key,value)){
                String syncSeen = reader.syncSeen()?"*":"";
                position=reader.getPosition();
            }

        } finally {
            IOUtils.closeStream(reader);
        }

    }
}

```

另一种是新的方法，使用Options封装



> GenerateSequenceFile.java

```java
package com.datapig.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class GenerateSequenceFile {
    public static void main(String[] args) throws IOException {

        //1.sequenceFile文件是通过SequenceFile类生成的
        //2.createWriter的类型有conf,name:文件名称
        //keyClass:key的数据类型，valueClass:值的数据类型

        //指定文件名称(输出流)
        SequenceFile.Writer.Option name = SequenceFile.Writer.file(new Path("/Users/liuhongyang/Desktop"));
        //指定key的类型
        SequenceFile.Writer.Option keyClass = SequenceFile.Writer.keyClass(LongWritable.class);
        SequenceFile.Writer.Option valueClass = SequenceFile.Writer.valueClass(Text.class);
        //Hadoop配置项
        Configuration conf = new Configuration();
        //创建输出流
        SequenceFile.Writer writer = SequenceFile.createWriter(conf,name,keyClass,valueClass);

        //读取文本
        FileSystem fileSystem = FileSystem.get(conf);
        FSDataInputStream in = fileSystem.open(new Path("/Users/liuhongyang/Desktop/input.txt"));
        String line = null;
        Long num = 0L;

        while((line=in.readLine())!=null){
            num++;
            //输出每行数据到sequenceFile中
            writer.append(new LongWritable(num),new Text(line));
        }

        IOUtils.closeStream(writer);

    }
}

```



>  WordCount.java

```java
package com.datapig.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class WordCount {
    /**
     *
     */
    //临时配置Hadoop_home
    static {
        System.setProperty("hadoop.home.dir","/User/liuhongyang/Desktop");
    }

    public static class MyMapper extends Mapper<LongWritable, Text,Text,LongWritable> {

        /**
         * 将每行数据拆分，拆分完输出每个单词和个数
         */
        LongWritable one = new LongWritable(1);
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            super.map(key, value, context);

            String words = value.toString();
            //将每行数据拆分成各个单词
            String[] array = words.split(" ");
            //遍历各个单词
            for (String word:array){
                //输出格式<单词,1>
                context.write(new Text(word),one);
            }


        }

    }


    public static class MyReducer extends Reducer<Text,LongWritable,Text,LongWritable>{

        /**
         *将map输出结果进行全局聚合
         *key:单词，values:个数集合[1,1m ]
         */
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            super.reduce(key, values, context);
        Long sum = 0L;

        for(LongWritable value:values){
            //累加单词的个数
            sum +=value.get();
        }
        //输出最终数据结果
        context.write(key,new LongWritable(sum));

        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //0.创建一个Job
        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS","hdfs://localhost:8020");

        Job job = Job.getInstance(conf,"word-count");
        //通过类名打包成Jar包
        job.setJarByClass(WordCount.class);


        //1.输入文件
        FileInputFormat.addInputPath(job,new Path(args[0]));

        //使用sequenceFileInputFormat处理sequenceFile文件
        job.setInputFormatClass(SequenceFileInputFormat.class);

        //2.编写mapper处理逻辑
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //3.shuffle流程（暂时不处理）

        //4.编写Reduce处理逻辑
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //5.输出文件
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //6.运行Job
        boolean result = job.waitForCompletion(true);

        System.out.println(result?1:0);

    }
}

```



##### Shuffle

1）位置

在mapper和reducer处理逻辑之间，连接map和reduce的纽带

2）功能

从Map输出到Reduce输入的整个过程，广义地称为Shuffle, Shuffle横跨Map端和Reduce端，在Map端包括Spill写的过程，在Reduce端包括copy和sort读的过程。

3）写入流程

a: map输出数据经过分区，分区完后通过collect收集到内存环形缓冲区kvbuffer

b:sort将缓冲区中的数据排序

<1>按分区派

<2>每个分区中数据按照key进行排序

c:spill线程溢写到本地磁盘

每次缓冲区满就写，会产生很多小文件

d:merge将小文件合并成大文件



判断输出目录是否存在，将结果输出到表或者文件







![Screenshot 2020-04-13 at 23.02.09](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdsjcqkhquj310o0j8gyw.jpg)







![Screenshot 2020-04-13 at 23.01.55](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdsjcupfrdj31360jc10i.jpg)
