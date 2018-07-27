1.使用MyEclipse创建的web application

2.配置使用官方下载的apache tomcat服务器，而不要使用内置的应用服务器
Window——>Preferences
MyEclipse——>Servers——>Tomcat
将Integrated Sandbox里面的几个服务disable

3.该项目需要部署到ROOT下，
选中工程，右键-->Properties-->Myeclipse-->Project Factes——>Web；
可以看到选项列表，
其中Web-root folder改成/WebRoot，Web Context-root从项目名称改成/ROOT。

4.UTF-8的问题
Window——>Preferences
General——>Workspace里面的Text file encoding改成UTF-8

5.某些包的访问异常Access Restriction
Window——>Preferences
Java——>Compiler——>Errors/Warnings
Deprecated and restricted API下面的
Forbidden reference的Error改为Warning


