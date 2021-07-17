package com.gilbert.test.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gilbertwang
 */
@RestController
public class EurekaConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IFeignService helloService;

    @Value("${provider.client.name}")
    private String clientName;

    @Value("${provider.client.url}")
    private String clientUrl;


    @RequestMapping(value = "feign")
    public Object feignRequest(){
        return helloService.service();
    }

    @RequestMapping(value = "rest")
    public Object commonRequest(){
        String url = "http://"+ clientName +"/" + clientUrl;
        return restTemplate.getForObject(url,Object.class);
    }
}