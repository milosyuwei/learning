spring-boot-actuator是一个spring-boot提供的用于监控组件，只需要在代码中加入依赖就可以了

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
遇到的一些小问题
1.可以加入依赖

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
来保证actuator暴露接口的安全性，可以通过 -u 'user:password' 方式来访问basic auth

2.如果项目依赖的是springmvc框架，并且基础的配置文件是 application.yaml的话，可以增加 application.properties 文件来配置安全性的配置.

3.如果加入了security依赖，则所有的接口默认都需要被验证，如果只想 /admin路径下的请求进行验证，则需要加入配置

security.basic.enabled=true
security.basic.path=/admin
security.user.name=admin
security.user.password=password
4.如果项目依赖的是非springmvc框架的话， 需要在依赖中加入mvc的依赖

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
</dependency>
5.如果management.security.enabled的值是false的话，除开health接口还依赖endpoints.health.sensitive的配置外，其他接口都不需要输入用户名和密码了。

6.actuator暴露的health接口权限是由两个配置： management.security.enabled 和 endpoints.health.sensitive组合的结果进行返回的。

management.security.enabled	endpoints.health.sensitive	Unauthenticated	Authenticated
false	false	Full content	Full content
false	true	Status only	    Full content
true	false	Status only	    Full content
true	true	No content	    Full content
7.actuator组件里面除开上面提到的metrics和health接口以外，还有很多默认的其他接口，如果它默认的接口不能满足你的需求的话，还可以通过继承它的 AbstractEndpoint 类来实现自己的Endpoint

最后附加一个配置文件例子:

security.basic.enabled=true
security.basic.path=/admin    #针对/admin路径进行认证
security.user.name=admin     #认证使用的用户名
security.user.password=password   #认证使用的密码
management.security.roles=SUPERUSER

management.port=11111   #actuator暴露接口使用的端口，为了和api接口使用的端口进行分离
management.context-path=/admin   #actuator暴露接口的前缀
management.security.enabled=true   #actuator是否需要安全保证

endpoints.metrics.sensitive=false   #actuator的metrics接口是否需要安全保证
endpoints.metrics.enabled=true

endpoints.health.sensitive=false  #actuator的health接口是否需要安全保证
endpoints.health.enabled=true