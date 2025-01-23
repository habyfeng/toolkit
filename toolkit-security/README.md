## Spring Security最简配置
1. application.yaml

配置应用名称

2. HelloSecurityApplication.java

应用启动类

3. IndexController.java

首页控制器

4. Index.html

首页页面

## getResourceAsStream

class.getResourceAStream() path 不以'/'开头时默认是从此类所在的包下取资源，以'/'开头则是从ClassPath根下获取。
其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。

class.getClassLoader().getResourceAsStream(String name)，默认从classpath中找文件，name不能带"/"，否则会抛空指针。

这里容易混淆，但其实也很容易理解，通过某个class获取资源时，以这个class文件所在的目录为参照物，
如果你想以根目录为参照物，则必须加个斜杠；
而通过类加载器加载资源时，没有明确的起点，只能都按照根目录作为参照物，
干脆统一都不加斜杠算了。

## 工作目录

其实就是执行命令时，终端（命令行）所在的当前目录（linux中中pwd的执行结果） 。

Java 中获取当前工作目录使用 System.getProperty() 方法
