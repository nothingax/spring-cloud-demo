package com.demo.feignclient;

import com.demo.remote.PersonRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: 继承api中的remote service 来实现feignClient
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:20 PM
 */
@FeignClient(value = "provider-demo" ,fallbackFactory = PersonClientFallback.class/* provider工程的项目名，注册到了eureka中 */)
public interface PersonClient extends PersonRemoteService {
}
