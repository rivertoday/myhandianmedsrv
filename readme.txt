1.ʹ��MyEclipse������web application

2.����ʹ�ùٷ����ص�apache tomcat������������Ҫʹ�����õ�Ӧ�÷�����
Window����>Preferences
MyEclipse����>Servers����>Tomcat
��Integrated Sandbox����ļ�������disable

tomcat��������conf/server.xml�ļ������Ӷ�UTF8��url֧�֣����һ�У�
<Connector executor="tomcatThreadPool"
               port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               URIEncoding="UTF-8"/>   

3.����Ŀ��Ҫ����ROOT�£�
ѡ�й��̣��Ҽ�-->Properties-->Myeclipse-->Project Factes����>Web��
���Կ���ѡ���б�
����Web-root folder�ĳ�/WebRoot��Web Context-root����Ŀ���Ƹĳ�/ROOT��

4.UTF-8������
Window����>Preferences
General����>Workspace�����Text file encoding�ĳ�UTF-8

5.ĳЩ���ķ����쳣Access Restriction
Window����>Preferences
Java����>Compiler����>Errors/Warnings
Deprecated and restricted API�����
Forbidden reference��Error��ΪWarning


