



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck25nyc2gj311y03ktb2.jpg)

```python
import requests
url = "https://www.google.com"
resp = requests.get(url)
print(resp.text)
```



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck2rh9wf7j313o03c41d.jpg)



**python的open函数：**

以二进制格式打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。
如果该文件不存在，创建新文件。一般用于非文本文件如图片等。

```python
import requests

url = "https://en.wikipedia.org/robots.txt"

resp = requests.get(url)

print("download")
with open("robots.txt","wb") as f:
    f.write(resp.content)
    

print("display")
print(resp.text)
```



![00831rSTgy1gck2sgn2d3j3126036tc2](https://tva1.sinaimg.cn/large/00831rSTgy1gck8ybo8auj3126036gm2.jpg)



在wikipedia中打开https://en.wikipedia.org/wiki/Main_Page文件，并提取所有的头文件

使用find_all函数查找所有头文件tag并暂时，使用循环将结果打印出来



```python
import requests
from bs4 import BeautifulSoup

url = "https://en.wikipedia.org/wiki/Main_Page"

resp = requests.get(url)

soup = BeautifulSoup(resp.text,"lxml")

soup = soup.find_all(["h1","h2","h3","h4","h5","h6","h7"])

for item in soup:
    print(item.get_text().strip())
```



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck3i4ddhwj313203sn0t.jpg)

找到Python文本中的a标签
再提取它的属性即可



```python
import requests
from bs4 import BeautifulSoup

url = "https://en.wikipedia.org/wiki/Python"

rs = requests.get(url)
soup = BeautifulSoup(rs.text,'lxml')


for link in soup.find_all("a"):
    if 'href' in link.attrs:    
        print(link.attrs['href'])

```



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck53g8uenj30zw02gwga.jpg)

![](https://tva1.sinaimg.cn/large/00831rSTgy1gck6ex2hnlj31j60gy43e.jpg)



```python
import requests
from bs4 import BeautifulSoup

account_name = input('Please unput your account name:')

url = "https://twitter.com/LeoLiu16981745"

resp = requests.get(url)

soup = BeautifulSoup(resp.text,'lxml')
try:
    print("The number of followers u have founded",soup.find('li',{'class':'ProfileNav-item ProfileNav-item--following'}).find('a').find('span','ProfileNav-value').get_text())

except:
    print("We didn't find you account!")


```



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck6fh1s4aj312u03swgm.jpg)
Fint the number of twitter of DonaldTrump



```python
import requests
from bs4 import BeautifulSoup

url="https://twitter.com/realDonaldTrump/"

resp = requests.get(url)
soup=BeautifulSoup(resp.text,'lxml')

re = soup.find('li',{'class':'ProfileNav-item ProfileNav-item--tweets is-active'}).find('a').find('span',{'class':'ProfileNav-value'}).attrs['data-count']



print("The number of Twitter posted by trump is {}".format(re))

```



![](https://tva1.sinaimg.cn/large/00831rSTgy1gck7q1ytipj314i03w0w4.jpg)





```python
import requests
from pprint import pprint


def weather_data(query):
    res = requests.get(
        'http://api.openweathermap.org/data/2.5/weather?' + query + '&APPID=****************************8&units=metric');
    return res.json();


def print_weather(result, city):
    print("{}'s temperature: {}°C ".format(city, result['main']['temp']))
    print("Wind speed: {} m/s".format(result['wind']['speed']))
    print("Description: {}".format(result['weather'][0]['description']))
    print("Weather: {}".format(result['weather'][0]['main']))


def main():
    city = input('Enter the city:')
    print()
    try:
        query = 'q=' + city;
        w_data = weather_data(query);
        print_weather(w_data, city)
        print()
    except:
        print('City name not found...')


if __name__ == '__main__':
    main()

```



Twitter爬虫收集信息：



```python
#收集Twitter
import requests
from bs4 import BeautifulSoup

url = "https://twitter.com/realDonaldTrump"

resp = requests.get(url)

soup = BeautifulSoup(resp.text,'lxml')

count = 0

for li in soup.find('div',{'class':'ProfileTimeline'}).find_all('p'):
    print ("第几条twitter",count)
    print (li.get_text())
    count=count+1
    
#生成词云 
from wordcloud import WordCloud
wordcloud = WordCloud().generate(mytext)
%pylab inline
import matplotlib.pyplot as plt
plt.imshow(wordcloud, interpolation='bilinear')
plt.axis("off")

```

