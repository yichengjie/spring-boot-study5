1. springboot默认会使用application.properties, application-default.properties配置文件
2. 指定默认激活文件不能直接再application.properties中配置（不生效）
```$xslt
在application.properties中添加激活文件配置将不生效
spring.profiles.default=dev
```
3. 可以在命令行中添加启动参数
```$xslt
--spring.profiles.default=dev
```
4. spring.profiles.active也可以激活profile文件,但是与spring.profiles.default互斥（也同样在启动参数中添加参数）
5. 自定义配置名称而非默认application(不常用，也不推荐使用)
```$xslt
命令行添加参数 --spring.config.name=
```

