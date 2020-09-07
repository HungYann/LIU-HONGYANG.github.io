---
layout: post
author: LIU,HONGYANG
tags: [Mybatis]
---







Mybatis分页参数RowBounds

RowBounds源码

```{}
package org.apache.ibatis.session;

public class RowBounds {
	public static final int NO_ROW_OFFSET =0;
	public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;
	public static final RowBounds DEFAULT = new RowBounds();
	
	private int offset;
	private int limit;
	
	public RowBounds()
	{
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public RowBounds(int offset, int limit)
	{
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public static int getNoRowOffset() {
		return NO_ROW_OFFSET;
	}

	public static int getNoRowLimit() {
		return NO_ROW_LIMIT;
	}

	public static RowBounds getDefault() {
		return DEFAULT;
	}	
}

```

offset属性是偏移量，即从第几行开始读取记录。limit是限制条数，从源码可知，默认值为0和Java的最大整数

使用时，仅需要给接口增加一个RowBounds参数

```{}
public List<Role> findByRowBounds(@Param("roleName") String rolename, @Param("note") String note, RowBounds rowBounds);

```

映射内容测试接口

```{}
<select id="findByRowBounds" resultType="role">
			select id, role_name as roleName, note from t_role where role_name like contact('%',#{roleName},'%')
			and note like concat('%',#{note},'%')
</select>
	
```

测试RowBounds内容

```{}
package com.learn.ssm.chapter3.main;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.pojo.Role;
import com.learn.ssm.chapter3.utils.SqlSessionFactoryUtils;

public class Chapter5Main {
	
	public static void main(String[] args) throws IOException
	{
		SqlSession sqlSession = null;
		
		sqlSession = SqlSessionFactoryUtils.getSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		RowBounds rowBounds = new RowBounds(0,20);
		List<Role> roleList = roleMapper.findByRowBounds("role_name","note",rowBounds);
		System.err.println(roleList.size());
	}	
}
```