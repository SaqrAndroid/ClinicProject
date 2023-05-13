package com.clinic.admin.services;

import com.clinic.admin.exceptions.ResourceNotFoundException;
import com.clinic.admin.exceptions.RestTemplateResponseErrorHandler;
import com.clinic.admin.exceptions.ServiceNotAvailableException;
import com.clinic.admin.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ahmed
 */

@Service
public class DoctorService {

    @Autowired
    private UtilService utilService;

    @Autowired
    private RestTemplate restTemplate;

    public Doctor getDoctorByName(String doctorName)
    {
        if("".equalsIgnoreCase(doctorName) || doctorName  == null)
        {
            throw new ResourceNotFoundException("Please provide Doctor Name ");
        }

        String serviceUrl = utilService.getServiceUrl("DOCTOR-SERVICE");
        if("".equalsIgnoreCase(serviceUrl) || serviceUrl ==null){
            throw new ServiceNotAvailableException(String.format("Service [%s] Not Available Ensure it is up ","DOCTOR-SERVICE"));
        }

        StringBuilder builder =new StringBuilder();
        builder.append(serviceUrl).append("/doctor").append("/").append(doctorName);

        System.out.println(serviceUrl+"/doctor");
        Doctor doctor = restTemplate.getForObject(builder.toString(), Doctor.class);

        return doctor;
    }


}
