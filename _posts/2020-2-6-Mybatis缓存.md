Mybatis一级、二级缓存

### 一级缓存：
同一个SqlSession对象

![](https://tva1.sinaimg.cn/large/006tNbRwgy1gbn23apl2tj313k0iewoq.jpg)

```{}

SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();

RoleMapper roleMapper = sqlession.getMapper(RoleMapper.class);

Role role = roleMapper.getRole(1);
Role role2 = roleMapper.getRole(1);

```

第二次访问时，不再查询数据库，直接从sqlSession中取值。


如果执行

```
sqlSession.commmit()
```

则清理，一级缓存

### 二级缓存：

![](https://tva1.sinaimg.cn/large/0082zybpgy1gbn3vxlnzjj31bk0jaapc.jpg)


Mybatis自带二级缓存，同一个namespace生成的mapper对象，
namespace的值就是接口的全类名，通过接口可以产生代理对象（studentMapper对象）

```{}
StudentMapper studentMapper  = session.getMapper(StudentMapper.class);
```

如果是同一个接口，则是二级缓存

```{}
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build();

SqlSession sqlSession = new SqlSessionFactory.openSession();

StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

SqlSession sqlSession2 = new SqlSessionFactory.openSession();

StudentMapper studentMapper2 = sqlSession.getMapper(StudentMapper.class);

```
开启二级缓存

```{}
<setting name="cacheEnabled" value="true"/>

```



声明


```{}
<cache/>
```

序列化：从内存-〉硬盘

反序列化：从硬盘-〉内存


触发将对象执行二级缓存的时机：

学生序列化，学生中的级联属性和父**session.close()**
进行缓存的时刻



### 禁用：
useCache="false"




### 清理：

commit()




