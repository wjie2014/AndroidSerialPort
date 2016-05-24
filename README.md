## AndroidSerialPort

Android 串口操作

ConsoleActivity 实现了双串口同时读取数据，即：接有两个串口设备的Android设备。

读取的串口分别为：

（1）"/dev/ttyS3", 9600
（2）"/dev/ttyS2", 9600

注：可根据串口设备需求设置自己的串口，只需在Application中设置SerialPort即可，若仅用一个串口设备，则只需一个SerialPort，只开一个ReadThread线程读取即可。

本项目根据项目需求结合android_serialport_api修改完成，如有疑问请联系我。

------

github地址：https://github.com/wjie2014

博客地址：http://blog.studyou.cn/

Gmail邮箱：w489657152@gmail.com

简书地址：http://www.jianshu.com/users/287bfff5fee0/latest_articles

------
