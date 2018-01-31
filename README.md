# 搭建环境 #

这次主要想用springboot来进行开发，试试大名鼎鼎的springboot是怎么快速搭建环境的。

开发环境使用IDEA，springBoot版本使用的是1.47。当时构建的时候好像高版本有点问题。**于是使用了1.4.7**

```xml

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.7.RELEASE</version>
        <relativePath/>
    </parent>
```

在慕课网之前学了SpringBoot的热部署的知识，当时学了但是没有去实践。现在当然要实践一下了。

参考之前的笔记：[https://zhongfucheng.bitcron.com/post/javaee/springbootre-bu-shu](https://zhongfucheng.bitcron.com/post/javaee/springbootre-bu-shu)


再次说明的是：使用IDEA的话，需要手动CTRL+F9来进行编译才能热部署。


于是乎，最简单的springboot环境就搭建好了。

```xml

 <!--Web必要的-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--springBoot  Test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>


        <!--Spring 官方提供的热部署插件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>


    <build>
        <plugins>
            <!--开启热部署-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
            </plugin>
        </plugins>
    </build>
```


配置文件使用的是yml类型的。因为yml类型的比properties看起来会更加简洁。

```yml


#服务端容器的配置
server:
  port: 8080
  address: http://www.zhongfucheng.top


```



当时搭建环境后还去验证了是否成功的。**主要使用Controller来返回数据给浏览器，如果访问成功，那么浏览器就会有相对应的数据了。**


#  前端页面 #

上次使用的是Bootstrap框架来搭建前端页面的布局，这次使用的是Materialize来进行布局。Materialize用起来也是十分方便的。[http://www.materializecss.cn/navbar.html](http://www.materializecss.cn/navbar.html)

在页面上主要遇到的问题有：

- 表单验证，主要使用的是jqueryValidation插件。参考：
	- [http://www.runoob.com/jquery/jquery-plugin-validate.html](http://www.runoob.com/jquery/jquery-plugin-validate.html),
	- [http://blog.csdn.net/u014800380/article/details/52106923](http://blog.csdn.net/u014800380/article/details/52106923),
	- [http://blog.163.com/jackswu%40yeah/blog/static/1406291232011111232211452/](http://blog.163.com/jackswu%40yeah/blog/static/1406291232011111232211452/)
- 从后台拿到的时间进行格式化。参考：
	- [http://www.cnblogs.com/zhanghaoliang/p/5997619.html](http://www.cnblogs.com/zhanghaoliang/p/5997619.html)
- 在页面上的布局和表单验证还有一个外国的网站可以参考一下：
	- [http://demo.geekslabs.com/materialize-v1.0/form-validation.html](http://demo.geekslabs.com/materialize-v1.0/form-validation.html)
- 发送短信时，按钮倒数1分钟，并刷新无效。参考：
	- [https://segmentfault.com/a/1190000005914976](https://segmentfault.com/a/1190000005914976)
- 发送短信的API。参考：
	- [http://www.dahancloud.com/index.html](http://www.dahancloud.com/index.html)
- 背景音乐播放的参考资料：
	- [http://www.w3school.com.cn/html/html_audio.asp](http://www.w3school.com.cn/html/html_audio.asp)，
	- [http://blog.csdn.net/ithomer/article/details/48622023](http://blog.csdn.net/ithomer/article/details/48622023)
- 本来想要在各个页面上切换音乐，而音乐不会断的。虽然没有去搞成功，但学习了几个方案：
	- 做成SPA页面(使用pjax)来进行加载页面
	- 将播放时间currentTime记录到localStorge中，跳转的时候加载回去
	- 使用iframe标签框架，将播放音乐的框架隐藏(该方法无法控制音乐的暂停和相关的操作)
	- 参考资料：
		- [http://blog.jobbole.com/78876/](http://blog.jobbole.com/78876/)，
		- [http://blog.linjunhalida.com/blog/pjax/](http://blog.linjunhalida.com/blog/pjax/)，
		- [https://segmentfault.com/a/1190000002723469](https://segmentfault.com/a/1190000002723469)，
		- [http://blog.csdn.net/iefreer/article/details/45499747](http://blog.csdn.net/iefreer/article/details/45499747)，
		- [https://www.jianshu.com/p/557cad38e7dd](https://www.jianshu.com/p/557cad38e7dd)，
		- [https://segmentfault.com/a/1190000002920768#dsfasdfdsfdsfxvczv](https://segmentfault.com/a/1190000002920768#dsfasdfdsfdsfxvczv)
		- 可能遇到的问题：[https://developers.google.com/web/updates/2016/03/play-returns-promise?hl=en](https://developers.google.com/web/updates/2016/03/play-returns-promise?hl=en)
- 使用js获取随机数，参考：
	- [https://www.cnblogs.com/starof/p/4988516.html](https://www.cnblogs.com/starof/p/4988516.html)
- jquery live方法的弃用，使用on方法替代。
	- [http://blog.csdn.net/aya19880214/article/details/39998117](http://blog.csdn.net/aya19880214/article/details/39998117)
- 使用的富文本编辑器
	- [https://www.kancloud.cn/wangfupeng/wangeditor3/335779](https://www.kancloud.cn/wangfupeng/wangeditor3/335779)
- 搜索相关JS的文件CDN：
	- [http://www.bootcdn.cn/](http://www.bootcdn.cn/)
- JS常用工作类的封装：
	- [https://segmentfault.com/a/1190000013041329](https://segmentfault.com/a/1190000013041329)
- 网页素材查找：
	- [http://www.lanrentuku.com/](http://www.lanrentuku.com/)

# 后端技术 #

这次主要是因为在前段时间学习了springboot和SpringData JPA，所以就想用用，看看是不是真的那么方便。

SpringBoot相关资料：

- 我自己的笔记：
	- [https://zhongfucheng.bitcron.com/?s=springBoot](https://zhongfucheng.bitcron.com/?s=springBoot)
- [http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/](http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)
- [http://www.cnblogs.com/magicalSam/p/7196340.html](http://www.cnblogs.com/magicalSam/p/7196340.html)
- springBoot整合mybatis，这次是没有用到的，可能以后会用到:
	- [http://www.cnblogs.com/elvinle/p/7999612.html](http://www.cnblogs.com/elvinle/p/7999612.html)
- springBoot Github Demo:
	- [https://github.com/t-hong/springboot-examples](https://github.com/t-hong/springboot-examples)


SpringData JPA相关资料：

- 我自己的笔记：[https://zhongfucheng.bitcron.com/?s=SpringData%20JPA](https://zhongfucheng.bitcron.com/?s=SpringData%20JPA)
- [http://blog.csdn.net/qq_35797610/article/details/78737211](http://blog.csdn.net/qq_35797610/article/details/78737211)



SpringBoot遇到的问题有：

- 测试Controller使用MockMVC测试
	- [http://www.cnblogs.com/xd03122049/p/6001457.html](http://www.cnblogs.com/xd03122049/p/6001457.html)
	- [http://blog.csdn.net/xiao_xuwen/article/details/52890730](http://blog.csdn.net/xiao_xuwen/article/details/52890730)
	- [http://www.cnblogs.com/xiaohunshi/p/5706943.html](http://www.cnblogs.com/xiaohunshi/p/5706943.html)
- springBoot拦截器
	- [http://blog.csdn.net/catoop/article/details/50501696](http://blog.csdn.net/catoop/article/details/50501696) 
- SpringBoot拦截器无法注入Bean
	- [https://my.oschina.net/u/1790105/blog/1490098](https://my.oschina.net/u/1790105/blog/1490098)
	- [http://blog.csdn.net/mjlfto/article/details/65635135](http://blog.csdn.net/mjlfto/article/details/65635135)	
- 出现: Could not find acceptable representation原因及解决方法
	- [http://blog.csdn.net/neosmith/article/details/51557957](http://blog.csdn.net/neosmith/article/details/51557957)
	- 或者是因为返回的JSON数据，而你返回了一个页面(使用RESTCONTROLLER没有注意）
- springBoot下使用统一异常处理方法：
	- [http://www.cnblogs.com/magicalSam/p/7198420.html](http://www.cnblogs.com/magicalSam/p/7198420.html)	
- springBoot集成Freemarker
	- [http://ifeve.com/spring-boot-%E9%9B%86%E6%88%90-freemarker-%E8%AF%A6%E8%A7%A3%E6%A1%88%E4%BE%8B/](http://ifeve.com/spring-boot-%E9%9B%86%E6%88%90-freemarker-%E8%AF%A6%E8%A7%A3%E6%A1%88%E4%BE%8B/)
	- [http://blog.csdn.net/z69183787/article/details/73850417](http://blog.csdn.net/z69183787/article/details/73850417)


SpringData JPA遇到的问题有：

- CascadeType jpa spring 理解：最好在开始的使用只使用REFRESH，当遇到问题的时候再添加MERGER等等，不然一开始会很乱
	- [http://blog.sina.com.cn/s/blog_9c2cda810101jw4a.html](http://blog.sina.com.cn/s/blog_9c2cda810101jw4a.html)
- 一对多，多对一的配置问题。注解写在GETTER方法上，不要写在属性上。这样会避免很多不必要的错误
	- [https://www.jianshu.com/p/0a2163273b3e](https://www.jianshu.com/p/0a2163273b3e)
	- [http://blog.csdn.net/ABAP_Brave/article/details/52845986](http://blog.csdn.net/ABAP_Brave/article/details/52845986)
	- [http://blog.csdn.net/lyg_2012/article/details/70195062](http://blog.csdn.net/lyg_2012/article/details/70195062)
	- [http://blog.sina.com.cn/s/blog_76c4136a0102y70d.html](http://blog.sina.com.cn/s/blog_76c4136a0102y70d.html)
	- [http://blog.csdn.net/mendeliangyang/article/details/52366799/](http://blog.csdn.net/mendeliangyang/article/details/52366799/)
	- [https://www.jianshu.com/p/5c416a780b3e](https://www.jianshu.com/p/5c416a780b3e)
- 异常处理：
	- detached entity passed to persist异常：
		- [http://blog.csdn.net/csujiangyu/article/details/48223641](http://blog.csdn.net/csujiangyu/article/details/48223641)
	-  JPA一堆多循环引用错误 HttpMessageNotWritableException：
		-  [http://blog.csdn.net/wangping1223/article/details/78062881](http://blog.csdn.net/wangping1223/article/details/78062881)

后台遇到的问题有：

- mysql统计查询思路：
	- [http://blog.csdn.net/w3chhhhhh/article/details/54097890](http://blog.csdn.net/w3chhhhhh/article/details/54097890)
- 网站自动登陆的设计：
	- [https://www.cnblogs.com/jacksoft/p/5216862.html](https://www.cnblogs.com/jacksoft/p/5216862.html) 
- 上传图片压缩，缩放的问题
	- [http://www.cnblogs.com/atyou/archive/2013/08/04/3236068.html](http://www.cnblogs.com/atyou/archive/2013/08/04/3236068.html)
- 异常处理：
	- [https://www.zhihu.com/search?type=content&q=java%E6%8A%9B%E5%87%BA%E5%BC%82%E5%B8%B8](https://www.zhihu.com/search?type=content&q=java%E6%8A%9B%E5%87%BA%E5%BC%82%E5%B8%B8)

 










