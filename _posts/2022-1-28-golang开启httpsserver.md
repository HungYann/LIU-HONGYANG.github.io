---
layout: post
author: LIU,HONGYANG
tags: [Go]
---

http服务和https服务不同，默认网页开启http服务的端口为80，而https服务的端口为443

本文主要是构建测试脚本，用于对业务需求的测试，首先是server端[代码](https://cloud.tencent.com/developer/article/1066385)：

server端需要使用`openssl`开启tls服务，命令如下：

```shell
openssl genrsa -out key.pem 2048
openssl req -new -x509 -key key.pem -out cert.pem -days 3650
```

运行服务器代码：

```shell
package main

import (
    "io"
    "log"
    "net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
    io.WriteString(w, "hello world!")
}

func main() {
    http.HandleFunc("/hello", helloHandler)
    err := http.ListenAndServeTLS(":8080", "cert.pem", "key.pem", nil)
    if err != nil {
        log.Fatal("ListenAndServeTLS:", err.Error())
    }
}
```
为了与echo框架融合，使用[goalng&echo开启https服务](https://studygolang.com/articles/31491)开启服务


**e.StartTLS中传入了相应的key.perm和cert.pem值，此处两值可以由上处生成**


开启https服务[代码](https://studygolang.com/articles/31491)如下：

```go
package main

import (
    "github.com/labstack/echo"
    "github.com/labstack/echo/middleware"
    "net/http"
)

func main() {
    e := echo.New()
    //使用重定向中间件将http连接重定向到https
    e.Pre(middleware.HTTPSRedirect())
    e.Use(middleware.Recover())
    e.Use(middleware.Logger())
    e.GET("/", func(c echo.Context) error {
        return c.HTML(http.StatusOK, `
            <h1>Welcome to Echo!</h1>
            <h3>TLS certificates automatically installed from Let's Encrypt :)</h3>
        `)
    })

    go func() {
        e.Logger.Fatal(e.Start(":80"))
    }()
    e.Logger.Fatal(e.StartTLS(":443", "crt/server.crt", "crt/server.key"))
}
```


最后，用于测试的服务端代码如下：

```go
package main

import (
	"encoding/json"
	"fmt"
	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	"net/http"
)

type Response struct {
	GetLogServerRes GetLogServerResStruct
}

type GetLogServerResStruct struct {
	ResultCode string `json:"resultcode"`
	TaskId string `json:"taskid"`
	Protocol string `json:"protocol"`
	LogServerIp string `json:"logserverip"`
	LogServerPort string `json:"logserverport"`
	Username string `json:"username""`
	Passport string `json:"passport"`
}

type ParamsInput struct {
	TaskId string `query:"taskid" validate:"required"`
	DevicdId string `query:"deviceid" validate:"required"`
	CurlogServerIp string `query:"curlogserverip"`
	CurlogServerPort string `query:"curlogserverport"`
	Iscurlogerror string `query:"iscurlogerror"`
}

func ServerAPI(echo *echo.Echo) {
	echo.GET("/logmanager/rest/cdncache/GetLogServer", StartEngine)
}

func main() {
	e := echo.New()
	//使用重定向中间件将http连接重定向到https
	e.Pre(middleware.HTTPSRedirect())
	e.Use(middleware.Recover())
	e.Use(middleware.Logger())

	ServerAPI(e)

	go func() {
		e.Logger.Fatal(e.Start(":80"))
	}()
	e.Logger.Fatal(e.StartTLS(":8443", "cert.pem", "key.pem"))
}

func StartEngine(context echo.Context) error {
	//https://localhost:8443/logmanager/rest/cdncache/GetLogServer?taskid=001&deviceid=ott001&curlogserverip=10.53.69.78&curlogserverport=21&iscurlogerror=1
	input := &ParamsInput{}
	context.Bind(input)
	context.Validate(input)

	fmt.Println("taskid", input.TaskId)
	fmt.Println("deviceid", input.DevicdId)
	fmt.Println("curlogserverip", input.CurlogServerIp)
	fmt.Println("curlogserverport", input.CurlogServerPort)
	fmt.Println("iscurlogerror", input.Iscurlogerror)

	resultCode := "0"
	taskId := "21"
	protocol := "1"
	logserverIp := "10.53.69.80"
	logserverPort := "21"
	username := "logserver001"
	passport := "001"

	logServerRes := GetLogServerResStruct{
		ResultCode: resultCode,
		TaskId: taskId,
		Protocol: protocol,
		LogServerIp: logserverIp,
		LogServerPort: logserverPort,
		Username: username,
		Passport: passport,
	}

	resp := Response{
		logServerRes,
	}

	res, err := json.Marshal(resp)
	if err !=nil {
		panic(err)
	}

	return context.String(http.StatusOK,string(res))
	// demo
	//{
	//	"GetLogServerRes": {
	//	"resultcode": "0",
	//		"taskid": "21",
	//		"protocol": "1",
	//		"logserverip": "10.53.69.80",
	//		"logserverport": "21",
	//		"username": "logserver001",
	//		"passport": "001"
	//}
	//}

	//返回例子
	//{
	//	"GetLogServerRes": {
	//	"resultcode": "0",
	//		"taskid": "21",
	//		"protocol": "1",
	//		"logserverip": "10.53.69.80",
	//		"logserverport": "21",
	//		"username": "logserver001",
	//		"passport": "001"
	//}
	//}
}

```

客户端请求代码如下：

```go
package main

import (
	"crypto/tls"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"time"
)

func main()  {
	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{Transport: tr}
	req, err := http.NewRequest("GET", "https://localhost:8443/logmanager/rest/cdncache/GetLogServer", nil)
	if err != nil {
		log.Fatalln("err:",err)
	}

	//https://localhost:8443/logmanager/rest/cdncache/GetLogServer?curlogserverip=10.53.69.78&curlogserverport=21&deviceid=ott001&iscurlogerror=1&taskid=001
	// http://localhost:8443/logmanager/rest/cdncache/GetLogServer?taskid=001&deviceid=ott001&curlogserverip=10.53.69.78&curlogserverport=21&iscurlogerror=1
	//https://localhost:8443/logmanager/rest/cdncache/GetLogServer?curlogserverip=10.53.69.78&curlogserverport=21&deviceid=ott001&iscurlogerror=1&taskid=001
	params := req.URL.Query()
	params.Add("taskid","001")
	params.Add("deviceid","ott001")
	params.Add("curlogserverip","10.53.69.78")
	params.Add("curlogserverport","21")
	params.Add("iscurlogerror","1")

	req.URL.RawQuery = params.Encode()
	fmt.Println(req.URL)
	time.Sleep(1*time.Second)

	resp, err := client.Do(req)
	if err != nil {
		fmt.Println("err:", err)
	}
	//defer resp.Body.Close()
	body, _ := ioutil.ReadAll(resp.Body)
	fmt.Println(string(body))

	//var data interface{}
	//json.Unmarshal(body, &data)
	//fmt.Println(data)
}

```
