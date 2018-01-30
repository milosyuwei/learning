使用Eureka作为服务注册中心，在服务启动后，各个微服务会将自己注册到Eureka server。那么服务之间是如何调用？又是如何进行负载均衡的呢？本文讲讲服务之间调用及负载均衡Ribbon。

目前，在Spring cloud 中服务之间通过restful方式调用有两种方式
- restTemplate+Ribbon
- feign  读[fein]

从实践上看，采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。

注意：spring-cloud-starter-feign 里面已经包含了 spring-cloud-starter-ribbon（Feign 中也使用了 Ribbon）


七：Eureka与Spring Cloud Security
Eureka与Spring Cloud Security结合可以进行相应的权限认证。
1：在pom.xml中加入Security的依赖

 <!--权限认证-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

2：properties定义权限的用户名密码

#权限认证（默认就是开启的）
security:
  basic:
    enabled: true
  user:
    name: eureka
    password: 123456

3：访问Eureka的后台，需要输入对应的用户名密码