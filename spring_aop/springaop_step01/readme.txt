spring aop学习 第一步
     Spring支持四种拦截类型：目标方法调用前（before），目标方法调用后（after），目标方法调用前后（around），以及目标方法抛出异常（throw）。


为了启用 Spring 对 @AspectJ 方面配置的支持，并保证 Spring 容器中的目标 Bean 被一个或多个方面自动增强，必须在 Spring 配置文件中配置如下片段：
<!-- 启动 @AspectJ 支持 -->
<aop:aspectj-autoproxy/>

或者
当然，如果我们希望完全启动 Spring 的“零配置”功能，则还需要启用 Spring 的“零配置”支持，让 Spring 自动搜索指定路径下 Bean 类。
所谓自动增强，指的是 Spring 会判断一个或多个方面是否需要对指定 Bean 进行增强，并据此自动生成相应的代理，从而使得增强处理在合适的时候被调用。
如果不打算使用 Spring 的 XML Schema 配置方式，则应该在 Spring 配置文件中增加如下片段来启用 @AspectJ 支持。

<!-- 启动 @AspectJ 支持 -->
<bean class="org.springframework.aop.aspectj.annotation.
    AnnotationAwareAspectJAutoProxyCreator"/>


与AspectJ的静态代理不同，Spring AOP使用的动态代理，所谓的动态代理就是说AOP框架不会去修改字节码，而是在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法。
Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理。JDK动态代理通过反射来接收被代理的类，并且要求被代理的类必须实现一个接口。JDK动态代理的核心是InvocationHandler接口和Proxy类。
如果目标类没有实现接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成某个类的子类，注意，CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。