package com.clinic.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahmed
 */


@Service
public class UtilService {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getServiceUrl(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances == null || instances.isEmpty()) {
            throw new RuntimeException("No instances available for service: " + serviceName);
        }
        ServiceInstance serviceInstance = instances.get(0);
        return serviceInstance.getUri().toString();
    }
}
