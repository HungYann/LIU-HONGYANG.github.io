---
layout: post
author: LIU,HONGYANG
tags: [Python]
---



simplejson文件转化成json文件



在将simplejson转化成json文件真是走了无数坑，比如如下错误

遇到的有错误

```
JSONDecodeError: Expecting property name enclosed in double quotes: line 1 column 2 (char 1)
```



```
ValueError: Expected object or value
```



```
TypeError: Input string must be text, not bytes
```



最后解决方法十分简单



### 一、普通文本

比如json_data = "{'property': 'text'}"这样的，直接转换：

```python
import ast
import json
json_data = "{'property': 'text'}"
data = json.dumps(ast.literal_eval(json_data))
```



输出结果：

![image-20200331151156671](https://tva1.sinaimg.cn/large/00831rSTgy1gdd4pajfisj30em01uq2y.jpg)





### 二、simplejson文件



针对simplejson文件

```python
import simplejson as json
import ast

with open('places.simplejson') as f:
    data = f.readlines()
    for i in range(len(data)):
        data[i]= ast.literal_eval(data[i])
with open('places.json', 'w') as f:
    json.dump(data, f)
```

转换前：

![image-20200401141818386](https://tva1.sinaimg.cn/large/00831rSTgy1gde8tgnqmpj31i604qgnw.jpg)



转换后：

![Screenshot 2020-03-31 at 18.11.20](https://tva1.sinaimg.cn/large/00831rSTgy1gde8thxdyuj317m0rkqid.jpg)





### 三、验证

最后的结果是否正确，使用如下网站验证一下即可：

[jslint](https://jslint.com/)

结果如下：

![image-20200401141214970](https://tva1.sinaimg.cn/large/00831rSTgy1gde8tjzpzoj31yq0sswgm.jpg)





总之，是不是非常方便？但是成功是痛苦的，我也走了无数坑。



>  simplejson文件：



```json
{'name': u'Diamond Valley Lake Marina', 'price': None, 'address': [u'2615 Angler Ave', u'Hemet, CA 92545'], 'hours': [[u'Monday', [[u'6:30 am--4:15 pm']]], [u'Tuesday', [[u'6:30 am--4:15 pm']]], [u'Wednesday', [[u'6:30 am--4:15 pm']], 1], [u'Thursday', [[u'6:30 am--4:15 pm']]], [u'Friday', [[u'6:30 am--4:15 pm']]], [u'Saturday', [[u'6:30 am--4:15 pm']]], [u'Sunday', [[u'6:30 am--4:15 pm']]]], 'phone': u'(951) 926-7201', 'closed': False, 'gPlusPlaceId': '104699454385822125632', 'gps': [33.703804, -117.003209]}
{'name': u'Blue Ribbon Cleaners', 'price': None, 'address': [u'Parole', u'Annapolis, MD'], 'hours': None, 'phone': u'(410) 266-6123', 'closed': False, 'gPlusPlaceId': '103054478949000078829', 'gps': [38.979759, -76.547538]}
{'name': u'Portofino', 'price': None, 'address': [u'\u0443\u043b. \u0422\u0443\u0442\u0430\u0435\u0432\u0430, 1', u'Nazran, Ingushetia, Russia', u'366720'], 'hours': [[u'Monday', [[u'9:30 am--9:00 pm']]], [u'Tuesday', [[u'9:30 am--9:00 pm']]], [u'Wednesday', [[u'9:30 am--9:00 pm']], 1], [u'Thursday', [[u'9:30 am--9:00 pm']]], [u'Friday', [[u'9:30 am--9:00 pm']]], [u'Saturday', [[u'9:30 am--9:00 pm']]], [u'Sunday', [[u'9:30 am--9:00 pm']]]], 'phone': u'8 (963) 173-38-38', 'closed': False, 'gPlusPlaceId': '109810290098030327104', 'gps': [43.22776, 44.762726]}
{'name': u"T C's Referee Sports Bar", 'price': u'$$', 'address': [u'5322 W 26th St', u'Sioux Falls, SD 57106'], 'hours': [[u'Monday', [[u'11:00 am--10:00 pm']]], [u'Tuesday', [[u'11:00 am--10:00 pm']], 1], [u'Wednesday', [[u'11:00 am--10:00 pm']]], [u'Thursday', [[u'11:00 am--10:00 pm']]], [u'Friday', [[u'11:00 am--1:00 am']]], [u'Saturday', [[u'11:00 am--1:00 am']]], [u'Sunday', [[u'11:00 am--10:00 pm']]]], 'phone': u'(605) 361-2208', 'closed': False, 'gPlusPlaceId': '100327153115986850675', 'gps': [43.529494, -96.792244]}
{'name': u'Carrefour - Palembang Square', 'price': None, 'address': [u'Jl. Angkatan 45', u'Kompleks Palembang Square', u'South Sumatra 30137, Indonesia'], 'hours': [[u'Monday', [[u'8:00 am--9:00 pm']]], [u'Tuesday', [[u'8:00 am--9:00 pm']]], [u'Wednesday', [[u'8:00 am--9:00 pm']], 1], [u'Thursday', [[u'8:00 am--9:00 pm']]], [u'Friday', [[u'8:00 am--9:00 pm']]], [u'Saturday', [[u'8:00 am--9:00 pm']]], [u'Sunday', [[u'8:00 am--9:00 pm']]]], 'phone': u'(0711) 359918', 'closed': False, 'gPlusPlaceId': '103368487323937936043', 'gps': [-2.976256, 104.742662]}
{'name': u'Pizzeria Montana', 'price': None, 'address': [u'Stuttgarter Stra\xdfe 44', u'75378 Bad Liebenzell', u'Germany'], 'hours': None, 'phone': u'07052 933486', 'closed': False, 'gPlusPlaceId': '103903929544828620241', 'gps': [48.767817, 8.75612]}
{'name': u'Single Tree Inn', 'price': None, 'address': [u'2033 W Oklahoma Ave', u'Ulysses, KS 67880'], 'hours': None, 'phone': u'(620) 356-1500', 'closed': False, 'gPlusPlaceId': '103848836623683146185', 'gps': [37.576427, -101.386182]}
{'name': u'Sekolah Menengah Kebangsaan Tun Abang Haji Openg', 'price': None, 'address': [u'Jalan Demak', u'Petra Jaya', u'Kuching, Sarawak, Malaysia'], 'hours': None, 'phone': None, 'closed': False, 'gPlusPlaceId': '114537653478141145113', 'gps': [1.572255, 110.362958]}
{'name': u'\u5929\u5c71\u6e6f\u6cbb\u90f7', 'price': None, 'address': [u'208 Yumotochaya', u'Hakone, Ashigarashimo District, Kanagawa 250-0312', u'Japan'], 'hours': None, 'phone': u'0460-86-4126', 'closed': False, 'gPlusPlaceId': '100062881646354125752', 'gps': [35.224978, 139.088382]}
{'name': u'Old Chicago', 'price': u'$$', 'address': [u'17960 NW Evergreen Pkwy', u'Beaverton, OR 97006'], 'hours': [[u'Monday', [[u'11:00 am--1:00 am']]], [u'Tuesday', [[u'11:00 am--1:00 am']]], [u'Wednesday', [[u'11:00 am--1:00 am']]], [u'Thursday', [[u'11:00 am--1:00 am']], 1], [u'Friday', [[u'11:00 am--1:00 am']]], [u'Saturday', [[u'11:00 am--1:00 am']]], [u'Sunday', [[u'11:00 am--1:00 am']]]], 'phone': u'(503) 533-4650', 'closed': False, 'gPlusPlaceId': '118222137795476771294', 'gps': [45.535176, -122.862242]}
{'name': u'China Cottage', 'price': u'$$', 'address': [u'3718 Wilmington Pike', u'Dayton, OH 45429'], 'hours': [[u'Monday', [[u'11:00 am--10:00 pm']]], [u'Tuesday', [[u'11:00 am--10:00 pm']]], [u'Wednesday', [[u'11:00 am--10:00 pm']], 1], [u'Thursday', [[u'11:00 am--10:00 pm']]], [u'Friday', [[u'11:00 am--11:00 pm']]], [u'Saturday', [[u'11:00 am--11:00 pm']]], [u'Sunday', [[u'11:00 am--10:00 pm']]]], 'phone': u'(937) 294-4724', 'closed': False, 'gPlusPlaceId': '106432060150136868000', 'gps': [39.692899, -84.136173]}
{'name': u'La Quinta Inn & Suites Ormond Beach/Daytona Beach', 'price': None, 'address': [u'1571 N US Highway 1', u'Ormond Beach, FL 32174'], 'hours': None, 'phone': u'(386) 236-2031', 'closed': False, 'gPlusPlaceId': '111282238562509982741', 'gps': [29.337011, -81.128647]}
{'name': u"Dicola's Pizzeria", 'price': None, 'address': [u'626 Can Do Expy # 1 , Hazle, PA 18202'], 'hours': None, 'phone': u'(570) 384-0520', 'closed': False, 'gPlusPlaceId': '104869934485244376571', 'gps': [40.9908, -76.0117]}
{'name': u'\u304a\u98df\u4e8b\u306e\u5e97\u9577\u53cb\u30fb\u8ca1\u5149\u5bfa\u5e97', 'price': None, 'address': [u'242-1 Zaikoji', u'Hyuga, Miyazaki 883-0021', u'Japan'], 'hours': None, 'phone': u'0982-53-6979', 'closed': False, 'gPlusPlaceId': '107096337073237318840', 'gps': [32.414204, 131.63109]}
{'name': u'Mastercuts', 'price': None, 'address': [u'666 Brandon Town Ctr', u'Brandon Town Center', u'Brandon, FL 33511'], 'hours': [[u'Monday', [[u'10:00 am--9:00 pm']]], [u'Tuesday', [[u'10:00 am--9:00 pm']]], [u'Wednesday', [[u'10:00 am--9:00 pm']]], [u'Thursday', [[u'10:00 am--9:00 pm']], 1], [u'Friday', [[u'10:00 am--9:00 pm']]], [u'Saturday', [[u'10:00 am--9:00 pm']]], [u'Sunday', [[u'11:00 am--6:00 pm']]]], 'phone': u'(813) 653-9008', 'closed': False, 'gPlusPlaceId': '101043078960050199039', 'gps': [27.926719, -82.326991]}
{'name': u'Polo Club Cranbrook', 'price': None, 'address': [u'14531 Ella Blvd', u'Houston, TX 77014'], 'hours': [[u'Monday', [[u'9:00 am--6:00 pm']]], [u'Tuesday', [[u'9:00 am--6:00 pm']]], [u'Wednesday', [[u'9:00 am--6:00 pm']], 1], [u'Thursday', [[u'9:00 am--6:00 pm']]], [u'Friday', [[u'9:00 am--6:00 pm']]], [u'Saturday', [[u'10:00 am--5:00 pm']]], [u'Sunday', [[u'1:00--5:00 pm']]]], 'phone': u'(281) 872-1222', 'closed': False, 'gPlusPlaceId': '111230142112807464303', 'gps': [29.979443, -95.439439]}
{'name': u'\u3060\u308b\u307e\u6599\u7406\u5e97', 'price': None, 'address': [u'Japan', u'\u3012250-0012 Kanagawa, Odawara, Honcho, \uff12\u4e01\u76ee1\u221230'], 'hours': [[u'Monday', [[u'11:00 am--7:00 pm']]], [u'Tuesday', [[u'11:00 am--7:00 pm']]], [u'Wednesday', [[u'11:00 am--7:00 pm']], 1], [u'Thursday', [[u'11:00 am--7:00 pm']]], [u'Friday', [[u'11:00 am--7:00 pm']]], [u'Saturday', [[u'11:00 am--7:00 pm']]], [u'Sunday', [[u'11:00 am--7:00 pm']]]], 'phone': u'0465-22-4128', 'closed': False, 'gPlusPlaceId': '102351867462083324344', 'gps': [35.251385, 139.159618]}
{'name': u'Orritel Hotel', 'price': None, 'address': [u'Survey No. 274/275/2, Bhosale Farm, Wakad-Hinjewadi Road, Hinjewadi', u'Dange Chowk Rd, Bhatewara Nagar, Hinjewadi Village, Hinjawadi', u'Pune, Maharashtra 411057, India'], 'hours': None, 'phone': u'020 6654 4600', 'closed': False, 'gPlusPlaceId': '115604558784949331949', 'gps': [18.596771, 73.742188]}
{'name': u'\uae4c\uc0ac\ub85c\uae4c \uc5ec\uc758\ub3c4\uc810', 'price': None, 'address': [u'25 Yeoeuido-dong', u'Yeongdeungpo-gu, Seoul', u'South Korea'], 'hours': None, 'phone': u'02-780-8133', 'closed': True, 'gPlusPlaceId': '100781305512646551142', 'gps': [37.524039, 126.926004]}
{'name': u'Fly Now', 'price': None, 'address': [u'Tha Muang District', u'Kanchanaburi', u'Thailand'], 'hours': None, 'phone': None, 'closed': False, 'gPlusPlaceId': '104147185033261390592', 'gps': [13.979946, 99.587241]}
{'name': u'Seminole Metal Finishing Inc.', 'price': None, 'address': [u'967 Explorer Cove', u'Altamonte Springs, FL 32701'], 'hours': None, 'phone': u'(407) 332-8949', 'closed': False, 'gPlusPlaceId': '107457864393084725818', 'gps': [28.681282, -81.35249]}
{'name': u'Byron Center High School', 'price': None, 'address': [u'8500 Burlingame Ave SW', u'Byron Center, MI 49315'], 'hours': None, 'phone': u'(616) 878-6600', 'closed': False, 'gPlusPlaceId': '105455649021131746775', 'gps': [42.810092, -85.702919]}
{'name': u'Garras Auto Vidros', 'price': None, 'address': [u'R. Prof Eug\xeania R Perito, 54', u'Tubar\xe3o - SC', u'88705-370, Brazil'], 'hours': None, 'phone': u'(48) 3626-6578', 'closed': False, 'gPlusPlaceId': '115937906176039685660', 'gps': [-28.471037, -48.990852]}
{'name': u'Smokey Mountain Wings', 'price': u'$$', 'address': [u'3607 Outdoor Sportsman Pl', u'Kodak, TN 37764'], 'hours': None, 'phone': u'(865) 465-3121', 'closed': False, 'gPlusPlaceId': '100184392614713668281', 'gps': [35.98598, -83.610598]}
{'name': u'San Antonio Moving Company', 'price': None, 'address': [u'San Antonio, TX'], 'hours': [[u'Monday', [[u'8:00 am--8:00 pm']]], [u'Tuesday', [[u'8:00 am--8:00 pm']]], [u'Wednesday', [[u'8:00 am--8:00 pm']]], [u'Thursday', [[u'8:00 am--8:00 pm']], 1], [u'Friday', [[u'8:00 am--8:00 pm']]], [u'Saturday', [[u'8:00 am--8:00 pm']]], [u'Sunday', [[u'8:00 am--8:00 pm']]]], 'phone': u'(210) 228-4062', 'closed': False, 'gPlusPlaceId': '107059752736783728413', 'gps': [29.517006, -98.436528]}
{'name': u"Lara's Place", 'price': None, 'address': [u'Bredabaan 1053/B2', u'2930 Brasschaat', u'Belgium'], 'hours': None, 'phone': u'03 663 76 78', 'closed': False, 'gPlusPlaceId': '105176407214255949518', 'gps': [51.323673, 4.520526]}
{'name': u'Muten Kura Sushi Iizuka', 'price': None, 'address': [u'8-3 Benbun', u'Iizuka, Fukuoka 820-0088', u'Japan'], 'hours': None, 'phone': u'0948-26-2610', 'closed': False, 'gPlusPlaceId': '101218790935826663894', 'gps': [33.621265, 130.670522]}
{'name': u'Caf\xe9 Langereis', 'price': None, 'address': [u'Amstel 202', u'1017 AH Amsterdam', u'Netherlands'], 'hours': [[u'Monday', [[u'11:00 am--1:00 am']]], [u'Tuesday', [[u'11:00 am--1:00 am']]], [u'Wednesday', [[u'11:00 am--1:00 am']], 1], [u'Thursday', [[u'11:00 am--1:00 am']]], [u'Friday', [[u'11:00 am--3:00 am']]], [u'Saturday', [[u'11:00 am--3:00 am']]], [u'Sunday', [[u'11:00 am--1:00 am']]]], 'phone': u'020 785 0641', 'closed': False, 'gPlusPlaceId': '110421660062903864339', 'gps': [52.366119, 4.900194]}
{'name': u'Juliet Photography', 'price': None, 'address': [u'8825 Man Of War Dr', u'Waxhaw, NC 28173'], 'hours': None, 'phone': u'(704) 243-3226', 'closed': False, 'gPlusPlaceId': '110904357941128849034', 'gps': [34.998894, -80.797123]}
{'name': u'TAB Maclean & District Bowling Club', 'price': None, 'address': [u'1 McLachlan St', u'Maclean NSW 2463', u'Australia'], 'hours': [[u'Monday', [[u'9:00 am--10:00 pm']]], [u'Tuesday', [[u'9:00 am--10:00 pm']]], [u'Wednesday', [[u'9:00 am--11:00 pm']], 1], [u'Thursday', [[u'9:00 am--11:00 pm']]], [u'Friday', [[u'9:00 am--11:30 pm']]], [u'Saturday', [[u'9:00 am--11:00 pm']]], [u'Sunday', [[u'9:00 am--9:30 pm']]]], 'phone': u'(02) 6645 3711', 'closed': False, 'gPlusPlaceId': '106077875776555854791', 'gps': [-29.455973, 153.199185]}

```







> json文件：

db.json:



```json
{
  "places": [
    {
      "name": "Diamond Valley Lake Marina",
      "price": null,
      "address": [
          "2615 Angler Ave",
          "Hemet, CA 92545"
      ],
      "hours": [
          [
              "Monday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ],
          [
              "Tuesday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ],
          [
              "Wednesday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ],
              1
          ],
          [
              "Thursday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ],
          [
              "Friday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ],
          [
              "Saturday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ],
          [
              "Sunday",
              [
                  [
                      "6:30 am--4:15 pm"
                  ]
              ]
          ]
      ],
      "phone": "(951) 926-7201",
      "closed": false,
      "gPlusPlaceId": "104699454385822125632",
      "gps": [
          33.703804,
          -117.003209
      ]
    }
  ],
  "users": [
    {
      "userName": "Jacquelyn Dorris",
      "jobs": [
          [
              "PS Medical Supplies, Inc.",
              "Customer Service",
              [
                  [
                      1,
                      1,
                      2012
                  ],
                  [
                      1,
                      1,
                      2013
                  ],
                  1
              ],
              "",
              ""
          ]
      ],
      "currentPlace": [
          "Pomona, CA",
          [
              [],
              340552270,
              -1177523050,
              1
          ]
      ],
      "previousPlaces": [
          [
              "Upland, Ca",
              [
                  [],
                  340975100,
                  -1176483880,
                  1
              ]
          ],
          [
              "Azusa, CA",
              [
                  [],
                  341336190,
                  -1179075630,
                  1
              ]
          ],
          [
              "Rancho Cucamonga, CA",
              [
                  [],
                  341063990,
                  -1175931080,
                  1
              ]
          ]
      ],
      "education": [
          [
              [],
              [],
              [],
              [],
              [],
              6
          ],
          [
              [
                  "Upland High School",
                  "",
                  [
                      [
                          1,
                          1,
                          2008
                      ],
                      [
                          1,
                          1,
                          2012
                      ]
                  ],
                  "",
                  ""
              ]
          ]
      ],
      "gPlusUserId": "100000035085750632094"
    }
  ],
  "reviews": [
    {
        "rating": 5,
        "reviewerName": "Jacquelyn Dorris",
        "reviewText": null,
        "categories": [
            "Indoor Lodging"
        ],
        "gPlusPlaceId": "104699454385822125632",
        "unixReviewTime": 1390212286,
        "reviewTime": "Jan 20, 2014",
        "gPlusUserId": "100000035085750632094"
    }
  ]
}

```



places.json



```json
[
    {
        "name": "Diamond Valley Lake Marina",
        "price": null,
        "address": [
            "2615 Angler Ave",
            "Hemet, CA 92545"
        ],
        "hours": [
            [
                "Monday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ],
            [
                "Tuesday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ],
            [
                "Wednesday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ],
                1
            ],
            [
                "Thursday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ],
            [
                "Friday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ],
            [
                "Saturday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ],
            [
                "Sunday",
                [
                    [
                        "6:30 am--4:15 pm"
                    ]
                ]
            ]
        ],
        "phone": "(951) 926-7201",
        "closed": false,
        "gPlusPlaceId": "104699454385822125632",
        "gps": [
            33.703804,
            -117.003209
        ]
    }
]
```



reviews.json:



```json
[
    {
        "rating": 5,
        "reviewerName": "Malaba Geoffrey",
        "reviewText": null,
        "categories": [
            "Indoor Lodging"
        ],
        "gPlusPlaceId": "111999087671228795673",
        "unixReviewTime": 1390212286,
        "reviewTime": "Jan 20, 2014",
        "gPlusUserId": "100000042779388982190"
    }
]
```









### References:



http://www.programmersought.com/article/8873495031/



http://zetcode.com/python/simplejson/



https://developer.rhino3d.com/guides/rhinopython/python-xml-json/



https://jslint.com/



https://blog.csdn.net/yatere/article/details/6606316