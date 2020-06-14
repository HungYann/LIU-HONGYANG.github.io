```
layout: post
author: LIU,HONGYANG
tags: [Mybatis]
```



Mybatis配置


MyBatis配置文件元素

```{}
<? xml version="1.0" encoding="UTF-8"?>
<configuration>
	<properties>
	<settings>
	<typeAliases>
	<typeHandlers>
	<objectFactory>
	<plugins>
	<environments>
		<environment>
			<transactionManager>
			<dataSource>
		</environment>

	<databaseIdProvider>
	<mappers>
</configuration>
```
Mybatis的配置顺序不能颠倒

### Property子元素



```{}
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

	<properties resource="jdbc.properties"/>
	<typeAliases>
		<typeAlias alias="role" type="com.learn.ssm.chapter3.pojo.Role"/>
	</typeAliases>

	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC"/>
				
			<dataSource type="POOLED">
				<property name="driver" value="${database.driver}" />
				<property name="url" value="${database.url}" />
				<property name="username" value="${database.username}" />
				<property name="password" value="${database.password}" />
			</dataSource>
		</environment>
	</environments>

	<mappers>
		<mapper resource="com/learn/ssm/chapter3/mapper/RoleMapper.xml" />
	</mappers>
	

</configuration>


```

### 使用properties文件

jdbc.properties

```{}

database.driver=com.mysql.jdbc.Driver
database.url=jdbc:mysql://localhost:3306/ssm
databaes.username=root
database.password=123456
```

### 使用程序传递方式传递参数

在实际的生产环境中，数据库的用户密码保密，运维人员一般将用户和密码经过加密成为密文。

```{}
String resource = "mybatis-config.xml";
InputStream inputStream;

InputStream in = Reources.getResourceAsStream("jdbc.properties");

Properties props = new Properties();
props.load(in);
String username = props.getProperty("database.username");

String password = props.getProperty("database.password");

//解密用户和密码，并在属性中重置

props.put("database.username",CodeUtils.decode(username));

props.put("database.password",CodeUtils.decode(password));

inputStream = Resources.getResourceAsStream(resource);

//使用程序传递的方式覆盖原有的properties属性参数

SqlSessionFactroy = new SqlSessionFactoryBuilder().build(inputStream,props);
```

首先使用Resources对象读取了一个jdbc.properties配置文件，然后获取了它原来配置的用户名和密码，进行解密并重置，最后使用SqlSessionFactoryBuilder的build方法， 传递多个properties参数来完成。这将覆盖之前配置的密文。

### settings设置

全量settings的配置样例

```{}
<settings>
	<setting name="cacheEnabled" value="true"/>
	<setting name="lazyLoadingEnabled" value="true"/>
	
	<setting name="multipleResultSetsEnabled" value="true"/>
	
	<setting name="useColumnLabel" value="true"/>
	
	<setting name="useGeneratedKeys" value="false"/>
	
	<setting name="autoMappingBehavior" value="PARTIAL"/>
	
	<setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
	
	<setting 
name="defaultExecutorType" value="SIMPLE"/>

	<setting 
name="defaultStatementTimeout" value="25"/>

	<setting
name="defaultFetchSize" value="100"/>

	
<settings>
```

### typeAliases别名


自定义别名：

```{}
<typeAliases>
	<typeAlias alias="role" type="com.learn.ssm.chapter4.pojo.Role"/>
	
	<typeAlias alias="user"
type="com.learn.ssm.chapter4.pojo.User"/>

</typeAliases>

```
如果有很多类需要定义别名，Mybatis还支持扫描别名。

```{}
<typeAliases>
	<package name="com.learn.ssm.chapter4.pojo"
</typeAliases>
```

系统定义的typeHandler

在Mybatis中typeHandler需要实现接口org.apache.ibatis.type.TypeHandler.

TypeHandler.java

```{}

public interface TypeHandler<T>{
	void setParameter(PreparedStatement ps, int i, T parameter, jdbcType jdbcType) throws SQLException;
	
T getResult(Result rs, String columnName) throws SQLException;

T getResult(Result rs, int columnIndex) throws SQLException;

T getResult(Result rs, int columnIndex) throws SQLxception;

T getResult(CallableStatement cs, int columnIndex) throws SQLException;

}
```

- 其中T是泛型，专指javaType,比如我们需要String的时候，那么实现类可以写为implements  TypeHandler<String>.

- setParameter方法，是使用typeHandler通过PreparedStatement对象进行设置SQL参数的时候使用的具体方法，其中i是参数在SQL的下标，parameter是参数，jdbcType是数据库类型。

- 其中有三个getResult的方法，它的作用是从JDBC结果集中获取数据进行转换，要么使用列名(columnName) 要么使用下标(columnIndex)获取数据库的数据，其中最后一个getResult方法是存储过程专用的。




