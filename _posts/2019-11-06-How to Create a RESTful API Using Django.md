# Agenda

## 1.What is an api
Api refers to **application programming interface**

It is a set of subroutine definitations, protocols and tools for building application software.

It helps in connecitng various software components

An api make it easier for developers to use certain technologies in building applications by using certain predefined operations.



## 2.Introduciton to Rest Framework

- It describe an architecture which stands for REpresentaionl State Transfer
- It is used for web based architecture for data communication 
- It uses HTTP to make calls between machines

The following HTTP methods are most commonly used in a REST based architecture:

**GET** records and retrieve the data

**PUT** is used to change the state or update a resources which can be a file it can be object or any block

**POST** is usually used to create the resources 

**DELETE** is used to remove that resource



## 3.What is REST API?

- When Web Service use REST architecture, they are called RESTFul APIs or REST APIs.

- A REST API is a set of web addresses that respond with pure information, not a formatted web pge

- An api returens **json**, which is a common format. You'll see all of information surrounded with quotation marks, {} ,[] and descriptive titles for each bit of info.



## 4.Demo-Create a Restful api

Open the terminal and type the following command for Django installation 

```python
pip install djangorestframework
```

```python
python manage.py makemigrations
python manage.py migrate
```

Create a user
![](https://tva1.sinaimg.cn/large/006y8mN6gy1g8nainarrwj30vs07aab9.jpg)


run server:

```python
python manage.py runserver
```
References:

[Video Django Rest Framework | How to Create a RESTful API Using Django | Django Tutorial | Edureka](https://www.youtube.com/watch?v=ejJ-2oz4AgI)

