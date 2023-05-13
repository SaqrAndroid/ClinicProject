package com.clinic.admin.services;

import com.clinic.admin.dtos.AppointmentRequestDTO;
import com.clinic.admin.models.Appointment;
import com.clinic.admin.models.Doctor;
import com.clinic.admin.models.Patient;
import com.clinic.admin.repo.AppointmentRepository;
import com.clinic.admin.repo.DoctorRepository;
import com.clinic.admin.repo.PatientRepository;
import com.clinic.admin.utils.AppointmentType;
import com.github.javafaker.App;
import com.github.javafaker.Faker;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Ahmed
 */

@Service
public class HomeService {


    @Autowired
    AppointmentRepository appointmentRepo;

    @Autowired
    PatientRepository patientRepo;

    @Autowired
    DoctorRepository doctorRepo;


    public List<Appointment> getDashboardData()
    {
        LocalDate today = LocalDate.now();
       return appointmentRepo.findAllWithCreationDateTimeBefore(new Date(),AppointmentType.PENDING);
    }






}
