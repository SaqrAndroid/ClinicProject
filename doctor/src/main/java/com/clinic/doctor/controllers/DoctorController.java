package com.clinic.doctor.controllers;

import com.clinic.doctor.models.Doctor;
import com.clinic.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmed
 */

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("{name}")
    public ResponseEntity<Doctor> getDoctorByName(@PathVariable("name") String name){
        Doctor doctor=  doctorService.findByName(name);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
