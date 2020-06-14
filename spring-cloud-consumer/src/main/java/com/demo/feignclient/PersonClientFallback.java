package com.demo.feignclient;

import com.demo.model.Person;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjw
 * @version 1.0
 */
@Component
@Slf4j
public class PersonClientFallback implements FallbackFactory<PersonClient> {

    @Override
    public PersonClient create(Throwable throwable) {
        return new PersonClient() {
            @Override
            public Person findById(String id) {
                return new Person().setName("zh");
            }

            @Override
            public Person findPersonHeadPicReturnSlow(String id) {
                log.error("/网络故障/404/下游长时间未响应等等原因,触发fallback", throwable);
                return new Person().setHeadPicUrl("default head pic url,触发熔断后手动创建的对象");
            }

            @Override
            public Person testProviderException(String s) {
                log.error("判断provider执行时异常，是否会走熔断？");
                return null;
            }
        };
    }
}
