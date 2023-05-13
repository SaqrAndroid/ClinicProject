package com.clinic.doctor.services;

import com.clinic.doctor.models.Doctor;

import java.util.Optional;

/**
 * @author Ahmed
 */
public interface DoctorService {
    public Doctor findByName(String name);

}
