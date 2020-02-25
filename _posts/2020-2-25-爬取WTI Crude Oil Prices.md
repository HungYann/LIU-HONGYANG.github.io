目标网站：

https://www.macrotrends.net/2516/wti-crude-oil-prices-10-year-daily-chart



Python爬取石油价格：



```python
import requests
import requests.cookies
import json
import time
import pandas as pd
from bs4 import BeautifulSoup


url='https://www.macrotrends.net/2516/wti-crude-oil-prices-10-year-daily-chart'
res = requests.get(url)

df = pd.read_html(res.text)
pan = pd.DataFrame(df[0])
d={'Year':pan.iloc[:,0],'AverageClosing Price':pan.iloc[:,1],'Year Open':pan.iloc[:,2],'Year High':pan.iloc[:,3],'Year Low':pan.iloc[:,4],'Year Close':pan.iloc[:,5],'Annual% Change':pan.iloc[:,6]}
df = pd.DataFrame(data=d)
df.to_csv('oil_price.csv')
```

![Screenshot 2020-02-25 at 14.11.38](https://tva1.sinaimg.cn/large/0082zybpgy1gc8mhf7corj30la0uitd3.jpg)