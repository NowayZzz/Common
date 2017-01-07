<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
Apache的配置文件

配置文件所在目录：
/etc/httpd/conf/
主配置文件：
httpd.conf
旧版本中的配置文件：
资源配置文件：srm.conf
访问许可权配置文件：access.conf

AccessConfig和ResourceConfig

为了对旧版本的Apache兼容， Apache服务器在每次启动时都查找并读取access.conf和srm.conf文件的内容。httpd.conf文件中的AccessConfig和ResourceConfig指令用于指定access.conf和srm.conf文件的位置，默认值为：
AccessConfig conf/access.conf ResourceConfig conf/srm.conf
出于安全性的考虑，可以设置为：
AccessConfig /dev/null
ResourceConfig /dev/null
指定这两个文件为空设备文件“/dev/null ”，这样可以避免恶意的修改access.conf和srm.conf文件对系统配置的影响。

配置的内容

1、全局变量
2、配置主服务器
3、配置虚拟主机


全局变量

1．选择服务器启动类型 ServerType
ServerType standalone | inetd
ServerType用于定义apache服务器的运行模式，默认值standalone为独立运行的服务器，如设置为inetd则由xinetd服务器负责apache服务器的启动。

2．设置服务器的根目录 ServerRoot
ServerRoot "/etc/httpd "
ServerRoot用于指定apache服务器的配置文件及日志文件存放的根目录，默认为目录"/etc/httpd " 。

3．设置加锁文件 <IfModule ! mpm_winnt.c>......


4．设置ScoreBoardFile 维护进程的内部数据，通常不需设置。


5．设置PidFile 指定存储httpd父进程的PID的文件
PidFile /var/run/httpd.pid
PidFile用于指定记录httpd进程号（PID）的文件位置，默认值为“/var/run/httpd.pid”。

6．设置超时时间 Timeout
Timeout 300
指定站点响应的秒数。若超过这段时间仍未收到或送出数据，就断开连接。
KeepAlive On|Off
启用此项，表示允许保持连接，让每次连接能提出多个请求。避免每请求一个文件就跟服务器建立一次连接。
MaxKeepAliveRequests 100
每次连接可提出请求的数量，设置为0表示数量不限，默认值为100。
KeepAliveTimeout 15
连续两个请求之间的时间如果超过15秒还未到达，则视为连接中断。

7．设置服务器进程数 <IfModule prefork.c>......
<IfModule prefork.c>
MinSpareServers 5
MaxSpareServers 20
提供浏览服务的httpd进程的数目需要随连接数目的多少而变化，因此需要随时保持几个闲置的httpd进程等候新的连接请求。若闲置的进程数少于5个（默认值），则表示闲置进程太少，需要将其增加到5个；若多于20个（默认值）则表示闲置进程太多，需将其减少到20个。
StartServers 8
当apache服务器启动时，httpd进程的数目，默认值为8。
MaxClients 150
同事接入的数目太多时会降低系统访问性能，设置此参数可限制同时连接的最大数值，默认值为150。
< /IfModule>

8．设置地址绑定 Listen
Listen 12.34.56.78:80
用于设置apache服务器监听指定IP和（或）端口上的连接请求。
Listen 80
指定apache服务器监听的端口号。

9．选择模块 LoadModule ......
载入模块指令
Apache服务器采用动态共享对象（DSO，Dynamic Shared Object）的机制，在启动Apache服务器时可根据实际需要载入适当的模块，使其具有相应的功能。
载入模块的相关指令有：
LoadModule
ClearModuleList
AddModule

LoadModule指令用于动态载入模块，即将模块外挂在Apache服务器上。
语法：
LoadModule 模块名称 模块文件路径全名
实例：
LoadModule status_module modules/mod_status.so

ClearModuleList
ClearModuleList指令用于清空Apache服务器内建的模块列表。通常先使用该指令清空列表，再使用AddModule指令向模块列表加入模块。

AddModule
AddModule指令用于向模块列表加入新的模块名称。
语法：
AddModule 模块
实例：
AddModule mod_status.c

10．配置状态信息 ExtendedStatus

配置主服务器

1．设置用户和组 User和Group
User apache Group apache 设置httpd用哪个用户帐号和组来启动，默认使用apache用户和组。

2．设置Email地址 ServerAdmin
ServerAdmin root@localhost
服务器管理员的邮件地址，当服务器运行出错时将向此邮件地址发信。

3．设置服务器名 ServerName
ServerName localhost
设置主机的名称，此名称会被送到远程连接程序，以取代安装Apache主机的真实名称。默认值是localhost，行首加#号，关闭此功能。

4．设置正式名称 UseCanonicalName


5．设置文档目录 DocumentRoot
DocumentRoot "/var/www/html"
指定Apache服务器存放网页的文档根目录。

6．设置访问选项和覆盖 <Directory />Options ......
容器指令（container directive）通常包括在<>括号内，较容易识别。条件指令<IfDefine>和<IfModule>不是容器指令，他们是例外。常用的容器指令有：
<Directory>
<Files>
<Location>
<VirtualHost>

<Directory>… </Directory>
<Directory /> 设置“/”根目录的访问权限
Options FollowSymLinks
AllowOverride None
</Directory> 目录属性设置结束
使用<Directory>… </Directory>设置指定目录的访问权限，其中可包含：
Options
AllowOverride
Order
Allow
Deny
五个属性。


Options属性
Options FollowSymLinks Indexes MultiViews
Options可以组合设置下列选项：
All：用户可以在此目录中作任何事情。
ExecCGI：允许在此目录中执行CGI程序。
FollowSymLinks：服务器可使用符号链接指向的文件或目录。
Indexes：服务器可生成此目录的文件列表。
None：不允许访问此目录。


AllowOverride
AllowOverride None
AllowOverride会根据设定的值决定是否读取目录中的.htaccess文件，来改变原来所设置的权限。
All：读取.htaccess文件的内容，修改原来的访问权限。
None：不读取.htaccess文件
为避免用户自行建立.htaccess文件修改访问权限，http.conf文件中默认设置每个目录为： AllowOverride None。


AccessFileName
AccessFileName filename
AccessFileName指令用于指定保护目录设定文件的文件名称，默认值为“.htaccess”。
AccessFileName .acl

Allow
设定允许访问Apache服务器的主机
Allow from all
允许所有主机的访问
Allow from 202.96.0.97 202.96.0.98
允许来自指定IP地址主机的访问

Deny 设定拒绝访问Apache服务器的主机 Deny from all 拒绝来自所有主机的访问 Deny from 202.96.0.99 202.96.0.88 拒绝指定IP地址主机的访问

Order Order allow,deny Order用于指定allow和deny的先后次序。

7．设置用户目录 UserDir
UserDir public_html
UserDir用于设定用户个人主页存放的目录，默认为“public_html”目录，即/home/anyuser/public_html。

8．设置目录索引 DirectoryIndex
DirectoryIndex index.html index.htm index.shtml index.php
DirectoryIndex指令用于指定目录中默认的索引文件名称，可同时指定多个文件名称，两两之间用空格分割。默认值为index.html。

9．设置访问控制 AccessFileName
AccessFileName .htaccess
定义每个目录下的访问控制文件的文件名，默认为.htaccess ;编辑文件如下：

<Files>…</Files>
<Files>容器包含只应用于指定文件的指令，文件应该由文件名（必要时使用统配符）指定。
实例：
<Files = “^\.ht”>
Order allow,deny
Deny from all
</Files>

<Location>… </Location>
<Location>容器包含只应用于特定URL的指令。
实例：
<Location /server-status>
SetHandler server-status
order deny,allow
allow from 127.0.0.1
deny from all
</Location>

10．设置MIME类型文件 TypesConfig


11．配置日志文件 HostnameLookups


12．设置服务器信息 ServerTokens


13．设置目录别名 Alias
Alias用于设置路径别名
Alias /doc/ /usr/share/doc/
给“/usr/share/doc/” 设置路径别名为“/doc/”

14．设置索引选项 AddIconByEncoding 为目录索引中的不同类型的文件产生不同的图标。


15．定义编码和语言 AddEncoding 定义一些压缩文件的MIME类型。


16．添加MIME类型和处理类型 AddType


17．自定义错误响应 ErrorDocument


18．设置浏览器响应 BrowserMatch

建立虚拟Web站点

1、基于IP的虚拟主机的配置
实例：
添加不同的IP地址：
# ifconfig eth0:0 192.168.0.2
# route add -host 192.168.0.2 eth0:0
编辑/etc/httd/conf/httpd.conf文件添加如下内容：
<VirtualHost 192.168.0.2>
ServerAdmin webmaster@server.astronomy.org
DocumentRoot /var/www/mailserver
ServerName mail.astronomy.org
ErrorLog logs/mailserver error_log
CustomLog logs/mailserver-access_log common
</VirtualHost>
重启服务
# /usr/local/apache2/bin/apachectl restart


2、基于域名的虚拟主机的配置
实例：在DNS中添加相关的主机记录；修改httpd.conf 文件
NameVirtualHost 192.168.0.1
<VirtualHost 192.168.0.1>
ServerAdmin webmaster@server.astronomy.org
DocumentRoot /var/www/mailserver
ServerName mail.astronomy.org
ErrorLog logs/mailserver error_log
CustomLog logs/nameserver access_log common
</VirtualHost>
重启服务
# /usr/local/apache2/bin/apachectl restart
  </body>
</html>
