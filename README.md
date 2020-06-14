# spring-cloud-archetype
## 适用于生产环境的spring cloud 工程骨架
### 更新中...




矛盾

1、provider和consumer都使用了全局异常处理器,出现异常时，将code 、message等信息封装到通用的WebResp返回对象

当consumer通过feign调用provider时，provide报错，会被包装成通用的WebResp返回对象
但provider也会对前端提供自己的接口






hystrix 
当前服务调用下游，下游故障或网络问题（导致响应延迟、404等等），导致请求本服务大量堆积，拖垮本服务，导致级联故障。
为避免这种问题，使用hystrix进行降级、熔断
未使用hystrix
```

feign.RetryableException: Read timed out executing GET http://provider-demo/remote/person/findByIdReturnSlow?id=1

```

使用：
属性文件中开启feign中hystrix的使用
编写fallback类，并在feign的注解中应用


ErrorDecoder 和 globalExceptionHandler 能同时使用吗？
能

GlobalExceptionHandler 与 hystrix的fallback如何配合使用？
虽然GlobalExceptionHandler处理后，返回的是包裹对象，而不是异常，单只要加上status注解，就可以在上游进入fallback
下游有全局异常处理器，执行失败后，返回了封装好的对象，consumer为什么会进fallback？

feign重试：
provider 长时间未返回时，consumer会重试，重试和fallback是不是有冲突呢？



全局异常处理器返回的对象与自己new的对象有什么区别吗？
当全局异常处理上加了注解比如 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 就不一样了


provider在使用全局异常处理器时，要明确设置不同异常对应的statusCode，比如参数错误是400，
这样当上游consumer中添加自定义的ErrorDecoder，根据特定的异常，转换为本服务的异常。


服务关闭 再启动后，有一段时间依然调用的是旧的逻辑，为什么？

使用feign 调用时，badRequest异常不会进入fallback，与服务内普通异常一样，如果你服务内有全局异常处理器，
它将会被全局异常处理器捕获处理。

feign调用，不能使用路径参数，使用路径参数，下游无法做参数的必填校验，一旦出现参数没有值，路径url会与预期不同，请求会变成404请求。

### ErrorDecoder
provider 中的全局异常处理器，发生异常时，可以对异常处理返回新对象，但不要忘了全局异常处理器的异常处理方法上加上
@ResponseStatus(xxx)注解，否则provider相当于返回一个普通对象，上游的ErrorDecoder无法捕获。
上游的ErrorDecoder在捕获后，可以根据status code 做自定义处理，如果provider异常细节感兴趣的话，也可以打印出来，便于定位问题。
做完自定义处理后，可以重写异常抛出。
badRequest异常不会进入fallback，其他异常会进入fallback。
这就是errorDecoder、globalException、fallback的使用，可以下游异常信息，也可以正常使用本服务的fallback


有的希望直接抛异常呢？可以直接在fallback 里抛异常
如果不希望所有接口都熔断，该怎么处理

feign 的重试（http）与hystrix 有什么关系？







ribbon 与 hystrixLoadBalanced 的配合使用需要注意什么，
文档 hystrix 超时时间应长与 ribbon的超时时间*重试次数。

由此可见，ribbon管理的是链接重试

负载均衡：客户端与服务端负载均衡如何选择

ribbon 是客户端负载均衡，当与eureka一起使用时，才会生效
客户端负载均衡与服务端负载均衡，eureka提供负载均衡功能吗？ribbon 扮演什么角色？
dubbo 的负载均衡是实现原理、sca的负载均衡原理。


ribbon 是客户端负载均衡，负载均衡策略有哪些，具有超时与重试机制，超时时间有connectionTime 和
readTime ，两个时间有什么区别吗？
ribbon 上下的加载开启饥饿加载，避免第一次调用超时。

ribbon 定制客户端有什么用处？

当注册中心是一个公共注册中心时，ribbon负载均衡建议脱离eureka来使用，为什么呢？什么是服务侵入性问题。

客户端负载均衡的使用、结合注册中心，
负载均衡策略的选择，可以针对接口配置不同的负载均衡策略

// feign 为http的客户端，http 有连接池、超时重试机制
hystrix 的超时机制，超时是判定feign请求超时，判定为请求失败


hystrix 和 ribbon都使用时，hystrix的超时时间，要大于ribbon的超时时间*重试次数。

feign 的超时重=重试在使用ribbon的情况下，机制是怎样的？


ribbon 停止维护，官方文档写到，不再推荐使用ribbon做负载均衡，而是使用 spring-cloud-balancer
https://cloud.spring.io/spring-cloud-openfeign/reference/html/#netflix-feign-starter






添加这个jvm参数，当应用关闭是，会打印字符串常量池
-XX:+PrintStringTableStatistics