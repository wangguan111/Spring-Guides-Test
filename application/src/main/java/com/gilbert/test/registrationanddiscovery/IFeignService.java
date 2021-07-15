package com.gilbert.test.registrationanddiscovery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gilbertwang
 */
@FeignClient("eureka-client")
public interface IFeignService {

    @RequestMapping(value = "service")
    Object service();
}
