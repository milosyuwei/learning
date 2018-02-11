启动过程中的可监听事件
ApplicationStartingEvent
ApplicationEnvironmentPreparedEvent
ApplicationPreparedEvent
ApplicationReadyEvent
ApplicationFailedEvent
ContextRefreshedEvent


start() is a method of Lifecycle interface which is extended by ConfigurableApplicationContext and explicitly implemented by org.springframework.context.support.AbstractApplic ationContext.
Maybe the most difficult (not obvious) distinction between start and refresh is that:
refresh is usually called implicitly during creation of concrete ApplicationContext, so we (developers) are more used to it
start is always explicit
So - if you want to get ContextStartedEvent, you should call start() on ApplicationContext.
Also - some beans may implement SmartLifecycleInterface - when they say (through that interface) that they should "autoStartup",
then they're started during ApplicationContext.refresh() - but this has nothing to do with generation of ContextStartedEvent.

重点
they're started during ApplicationContext.refresh()