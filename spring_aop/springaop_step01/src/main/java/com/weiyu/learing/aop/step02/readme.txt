在 这个示例中，使用接口进行 pointcut 可以发现代理类为
before
-- sayHello() --
class com.sun.proxy.$Proxy53
说明使用 jdk proxy

修改 Chinese 的继承，去掉继承，修改App中，Person 为Chinese，修改 Advice 修改pointcut的点为 Chinese 类。可以看到如下结果
before
-- sayHello() --
class com.weiyu.learing.aop.step02.Chinese$$EnhancerBySpringCGLIB$$310595d4

说明使用了 CGLib 代理