package com.clinic.doctor.services.impl;

import com.clinic.doctor.exceptions.ResourceNotFoundException;
import com.clinic.doctor.models.Doctor;
import com.clinic.doctor.repo.DoctorRepository;
import com.clinic.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed
 */

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor findByName(String name) {
        return  doctorRepository.findByName(name).orElseThrow
                (() -> new ResourceNotFoundException(String.format("Cannot Find Doctor With Name:  %s", name)));
    }
}
