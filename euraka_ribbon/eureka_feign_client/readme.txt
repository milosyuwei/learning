必须在配置文件中加这个，feign的hystrix才能有效
feign:
  hystrix:
    enabled: true  #必须加这个，feign的hystrix才能有效