本文计划利用Eureka实现一个简答的服务注册于发现的例子，需要创建三个角色：服务注册中心、服务提供者、服务消费者。
可以通过服务注册，当客户端访问时，自动实现了 load balance功能，使用了 eureka、ribbon 组件


这里我们还可以使用Spring Cloud应用中的@SpringCloudApplication注解来修饰应用主类，
该注解的具体定义如下所示。我们可以看到该注解中包含了上我们所引用的三个注解，这
也意味着一个Spring Cloud标准应用应包含服务发现以及断路器。
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public @interface SpringCloudApplication {
}