# 汉典医谷文献与产品服务后台

## 构建说明

### 1. 使用MyEclipse创建的web application

### 2. 配置使用官方下载的apache tomcat服务器，而不要使用内置的应用服务器

Window——>Preferences
MyEclipse——>Servers——>Tomcat
将Integrated Sandbox里面的几个服务disable

### 3. 该项目需要部署到ROOT下，

选中工程，右键-->Properties-->Myeclipse-->Project Factes——>Web；
可以看到选项列表，
其中Web-root folder改成/WebRoot，Web Context-root从项目名称改成/ROOT。

### 4. UTF-8的问题

Window——>Preferences
General——>Workspace里面的Text file encoding改成UTF-8

tomcat服务器的conf/server.xml文件需增加对UTF8的url支持（最后一行）

```
<Connector executor="tomcatThreadPool"
               port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               URIEncoding="UTF-8"/>   
```

### 5. 某些包的访问异常Access Restriction

Window——>Preferences
Java——>Compiler——>Errors/Warnings
Deprecated and restricted API下面的
Forbidden reference的Error改为Warning


