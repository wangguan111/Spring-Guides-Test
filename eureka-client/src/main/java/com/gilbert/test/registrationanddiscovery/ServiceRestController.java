package com.gilbert.test.registrationanddiscovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author gilbertwang
 */
@RestController
public class ServiceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service")
    public List<String> service() {
        return this.discoveryClient.getServices();
    }
}
