---
layout: post
author: LIU,HONGYANG
tags: [前端]
---





##### BootStrap显示echarts图表



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <script src="{{ url_for('static',filename='dist/bootstrap.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/jquery-3.4.1.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/echarts.js') }}"></script>
</head>

<body>
    <div id="main" style="width:100%;height:400px;"></div>
    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));

        // 模拟数据
        function getVirtulData(year) {
            year = year || '2017';
            var date = +echarts.number.parseDate(year + '-01-01');
            var end = +echarts.number.parseDate(year + '-12-31');
            var dayTime = 3600 * 24 * 1000;
            var data = [];
            for (var time = date; time <= end; time += dayTime) {
                data.push([
                    echarts.format.formatTime('yyyy-MM-dd', time),
                    Math.floor(Math.random() * 10000)
                ]);
            }
            return data;
        }
        var option = {
            visualMap: {
                show: false,
                min: 0,
                max: 10000
            },
            calendar: {
                range: '2017'
            },
            series: {
                type: 'heatmap',
                coordinateSystem: 'calendar',
                data: getVirtulData(2017)
            }
        };
        myChart.setOption(option);
    </script>
</body>
</html>

```



##### Bootstrap中的排版-标题



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <script src="{{ url_for('static',filename='dist/bootstrap.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/jquery-3.4.1.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/echarts.js') }}"></script>
</head>

<body>
<h1>1<small>1</small></h1>
<h2>2</h2>
<h3>3</h3>
<h4>4</h4>
<h5>5</h5>
<h6>6</h6>

<span class="h1">1</span>
<span class="h2">2</span>
<span class="h3">3</span>
<span class="h4">4</span>
<span class="h5">5</span>
<span class="h6">6</span>

</body>
</html>

```





##### Bootstrap中的排版-文本





![image-20200309180524650](https://tva1.sinaimg.cn/large/00831rSTgy1gcnu30ad8zj30j80begn6.jpg)





```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <script src="{{ url_for('static',filename='dist/bootstrap.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/jquery-3.4.1.min.js') }}"></script>
        <script src="{{ url_for('static',filename='js/echarts.js') }}"></script>
</head>

<body>

<p><mark>独家</mark>每周<ins>六七更新本书已得到</ins>《狂霸战皇》小说作者授权。在苍岚大陆，若想要成为武者，唯有觉醒自身武魂。通过武魂，才能沟通天地，吸纳灵气，进行修行。武魂的种类繁多，五花八门，数不胜数，其中分为了天地玄黄四大等级，每个等级划分十品。若是武魂等级越高，那么修炼的速度能力与潜力就越强，未来成就武者的希</p>
<p><del>独家每周六七更新本书</del>已得到<small>《狂霸战皇》小说作者授权</small>。<strong>在苍岚大陆</strong>，若想要成为武者，唯有觉醒自身武魂。通过武魂，才能沟通天地，吸纳灵气，进行修行。武魂的种类繁多，五花八门，数不胜数，其中分为了天地玄黄四大等级，每个等级划分十品。若是武魂等级越高，那么修炼的速度能力与潜力就越强，未来成就武者的希</p>

<p class="text-left">慕课网</p>

<p class="text-center">慕课网</p>

<p class="text-right">慕课网</p>

<p class="text-lowercase">Hello</p>

<p class="text-uppercase">hello</p>

<p class="text-capitalize">hello</p>
</body>
</html>

```



![image-20200309181420923](https://tva1.sinaimg.cn/large/00831rSTgy1gcnucazfkcj31z4066ac4.jpg)



##### Bootstrap中的排版-表格



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
{#        <script src="{{ url_for('static',filename='dist/bootstrap.min.js') }}"></script>#}
{#        <script src="{{ url_for('static',filename='js/jquery-3.4.1.min.js') }}"></script>#}
{#        <script src="{{ url_for('static',filename='js/echarts.js') }}"></script>#}
</head>

<body>
    <table class='table table-striped table-bordered table-hover'>

        <thead>
            <tr>
                <th>标题一</th>
                <th>标题二</th>
                <th>标题三</th>
                <th>标题四</th>
                <th>标题五</th>
            </tr>

        </thead>


        <tbody>
            <tr class="danger">
                <td>内容一</td>
                <td>内容二</td>
                <td>内容三</td>
                <td>内容四</td>
                <td>内容五</td>
            </tr>
            <tr class="waring">
                <td>内容一</td>
                <td>内容二</td>
                <td>内容三</td>
                <td>内容四</td>
                <td>内容五</td>
            </tr>
            <tr class="info">
                <td>内容一</td>
                <td>内容二</td>
                <td>内容三</td>
                <td>内容四</td>
                <td>内容五</td>
            </tr>

            <tr class="active">
                <td>内容一</td>
                <td>内容二</td>
                <td>内容三</td>
                <td>内容四</td>
                <td>内容五</td>
            </tr>

            <tr>
                <td>内容一</td>
                <td>内容二</td>
                <td>内容三</td>
                <td>内容四</td>
                <td>内容五</td>
            </tr>

        </tbody>
    </table>

</body>
</html>

```



##### Bootstrap表单



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <link rel="stylesheet" href="{{ url_for('static', filename='css/signin.css') }}" type="text/css">

</head>

<body>

<form class="form-inline">

    <div class="form-group has-success">
        <label class="control-label" for="">这是一个输入框:</label>
        <input type="text" class="form-control input-lg" placeholder="这是一个输入框">
    </div>


    <div class="form-group has-warning">
        <label class="control-label" for="">这是一个输入框:</label>
        <select class="form-control" name="" id="">
            <option value="">北京</option>
            <option value="">上海</option>
            <option value="">深圳</option>
        </select>
    </div>

    <div class="form-group has-success">
        <label class="control-label" for="">这是一个输入框:</label>
        <textarea class="form-control" name="" id="" cols="30" rows="10"></textarea>
    </div>


</form>



</body>
</html>

```





```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
</head>

<body>

<button class="btn btn-default">这是一个按钮</button>
<button class="btn btn-success">这是一个按钮</button>
<button class="btn btn-primary">这是一个按钮</button>
<button class="btn btn-info">这是一个按钮</button>
<button class="btn btn-warning">这是一个按钮</button>
<button class="btn btn-danger">这是一个按钮</button>
<button class="btn btn-link">这是一个按钮</button>
<button class="btn btn-success btn-lg">这是一个按钮</button>
<button class="btn btn-success btn-sm">这是一个按钮</button>
<button class="btn btn-success btn-sm active">这是一个按钮</button>
<button class="btn btn-success btn-sm btn-block">这是一个按钮</button>
<button class="btn btn-success btn-sm disabled">这是一个按钮</button>
<a class="btn btn-default" href="">这是a组成的按钮</a>


</body>
</html>

```



##### Bootstrap渐进



- Viewport

```html
<meta name="viewport" content="width=device-width",initial-scale=0.5, maximum-scale=1, minimum-scale=1, user-scalable=no">
```





![image-20200318210431978](https://tva1.sinaimg.cn/large/00831rSTgy1gcydu79pjsj31hi0qy10r.jpg)

- 栅格布局



```html
<!DOCTYPE html>
<html lang="en">
<head>
{#    <meta name="viewport" content="width=device-width",initial-scale=0.5, maximum-scale=1, minimum-scale=1, user-scalable=no">#}
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <link rel="stylesheet" href="{{ url_for('static', filename='css/signin.css') }}" type="text/css">

    <style>

        *{
            padding:0;
            margin:0;
        }

        .test{
            width: 300px;
            height: 200px;
            background: red;
        }
        
        @media screen and (max-width: 900px) and (min-width: 300px) {
            .test{
                width: 100%;
                height: 100px;
                background: blue;
            }
        }

    </style>
</head>

<body>

    <div class="test"></div>

</body>
</html>

```

栅格参数

栅格将屏幕分成12等份，栅格占3等份



- 单位

1.px相对于显示屏幕分辨率的长度单位

2.em相对于当前文本内的字体尺寸

3.rem与em类似，相对于HTML根节点的字体单位



1em =16px

rem与em类似

rem会继承根元素的字体大小



- 图标

  

##### Bootstrap组件



1.role

2.aria-label

3.tabIndex

4.data-





图标

1.glyphicon

2.glyphicon-star





下拉菜单









##### Bootstrap插件

1.Bootstrap插件依赖Bootstrap.js

2.Bootstrap.js基于Jquery



Bootstrap中的插件-data属性

1.通过data属性控制页面交互

2.$(document).off('.data-api')解除属性绑定



```html
<!DOCTYPE html>
<html lang="en">
<head>
{#    <meta name="viewport" content="width=device-width",initial-scale=0.5, maximum-scale=1, minimum-scale=1, user-scalable=no">#}
    <title>BootStrap</title>

        <link rel="stylesheet" href="{{ url_for('static', filename='css/bootstrap.min.css') }}" type="text/css">
        <link rel="stylesheet" href="{{ url_for('static', filename='css/signin.css') }}" type="text/css">

    <style>
        .test{

            height: 300px;
            background: red;
        }


    </style>


</head>

<body>

    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#danger">
        弹窗
    </button>

    <div id="danger">
        这是一个内容
    </div>

</body>
</html>

```

