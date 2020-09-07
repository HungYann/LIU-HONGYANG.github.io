---
layout: post
author: LIU,HONGYANG
tags: [Python]
---



### WHAT IS AN API

API stands for application programming interface. API basically helps one web application to communicate with another application.

Let's assume you are developing an android application which has feature to detect the name of a famous person in an image.

Introduce to achieve this you have 2 options:

**option 1:**

Option 1 is to collect the images of all the famous personalities around the world, build a machine learning/ deep learning or whatever model it is and use it in your application.

**option 2:**

Just use someone elses model using api to add this feature in your application.

Large companies like Google, they have their own personalities. So if we use their Api, we would not know what logic/code whey have writting inside and how they have trained the model.  You will only be given an api(or an url). It works like a black box where you send your request(in our case its the image), and you get the response(which is the name of the person in that image)

## Here is an example:


![](https://tva1.sinaimg.cn/large/006y8mN6gy1g8n19m8oa5j30r20p210k.jpg)

- PREREQUISITES

```python
conda install jango
conda install -c conda-forge djangorestframework
```

- Step 1

Create the django project, open the command prompt therre and enter the following command:

```python
django-admin startproject SampleProject

```

- Step 2

Navigate the project folder and create a web app using the command line.

```python
python manage.py startapp MyApp
```

- Step 3

open the setting.py and add the below lines into of code in the  INSTALLED_APPS section:

```python
'rest_framework',
'MyApp'
```

- Step 4

Open the views.py file inside MyApp folder and add the below lines of code:

```python
from django.shortcuts import render
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.http import JsonResponse
from django.core import serializers
from django.conf import settings
import json
# Create your views here.
@api_view(["POST"])
def IdealWeight(heightdata):
    try:
        height=json.loads(heightdata.body)
        weight=str(height*10)
        return JsonResponse("Ideal weight should be:"+weight+" kg",safe=False)
    except ValueError as e:
        return Response(e.args[0],status.HTTP_400_BAD_REQUEST)
```

- Step 5

Open urls.py file and add the below lines of code:

```python
from django.conf.urls import url
from django.contrib import admin
from MyApp import views
urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^idealweight/',views.IdealWeight)
]
```
- Step 6

We can start the api with below commands in command prompt:

```python
python manage.py runserver

```

Finally open the url:

http://127.0.0.1:8000/idealweight/


![](https://tva1.sinaimg.cn/large/006y8mN6gy1g8n2px0h78j30rg0osjt7.jpg)

***

References:

[Create a Simple API Using Django REST Framework in Python](https://dzone.com/articles/create-a-simple-api-using-django-rest-framework-in)
