```
layout: post
author: LIU,HONGYANG
tags: [JavaEE]
```



# Mybatis映射器**select**

### 简单的select元素的应用


- id 配合Mapper的全限定名，联合成为一个唯一的标示

- parameterType 表示这条SQL接受的参数类型

- resultType表示这条SQL返回的结果类型

- **#{firstName}** 是被传递进去的参数

```{}
<select id="countUserByFirstName" parameterType="string" resultType="int">
	select count(*) total from t_user where user_name like contact(#{firstName},'%')
</select>
```

与映射文件匹配的接口方法程序：

```{}
public Integer countUserByFirstName(String firstName);
```






### 传递多个参数

#### 使用map接口传递参数

```{}
public List<Role> findRolesByMap(Map<String,Object> parameterMap);
```

SQL中配置的方法为：

```{}
<select id="findRolesByMap" parameterType="map" resultType="role">

 	select id, role_name as roleName, note from t_role where role_name like contact('%',#{roleName},'%')
and note like concat('%',#{note},'%')

</select>
```


代码清单：

```{}
RoleMapperroleMapper = sqlSession.getMapper(RoleMapper.class);

Map<String,Object> parameterMap = new HashMap<String,Object>();

parameterMap.put("roleName",1);

parameterMap.put("note",1);

List<Role> roles = roleMapper.findRolesByMap(parameterMap);
```



#### 使用注解传递多个参数

```{}
public List<Role> findRolesByAnnotation(@Param("roleName") String rolename, @Param("note") String note)
```

此时并不需要给出parameterType属性，让Mybatis自动探索

#### 通过Java Bean传递多个参数

```{}
public class RoleParams
{
	private String roleName;
	private String note;
}

```

接口方法定义：


```{}
public List<Role> findRolesByBean(RoleParams roleParam);
```

修改映射文件：

```{}
<select id="findRolesByBean" parameterType="com.learn.ssm.chapter5.param.RoleParams" resultType="role">
	select id, role_name as roleName, note from t_role where role_name like concat('%',#{roleName},'%') and note like concat ('%',#{note},'%')
</select>
```

引入Java Bean定义的属性作为参数

```{}
RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

RoleParams roleParams = new RoleParams();

roleParams.setRoleName("1");

roleParams.setNote("1");

List<Role> roles = roleMapper.findRolesByBean(roleParams);

```

### 对于返回值

#### 使用resultMap映射结果集

```{}
<mapper namespace="com.learn.ssm.chapter5.mapper.RoleMapper">
	<resultMap id="roleMap"	 type="role">
		<id property="id" column="id"/>
		<result property="roleName" column="role_name"/>
		
		<result property="note" column="note"/>
		
	</resultMap>
	
	<select id="getRoleUseResultMap" parameterType="long" resultMap="roleMap">
		select id, role_name, note from t_role where id = #{id}
	</select>
</mapper>
```