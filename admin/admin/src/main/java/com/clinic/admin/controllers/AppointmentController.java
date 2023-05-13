package com.clinic.admin.controllers;

import com.clinic.admin.dtos.AppointmentCancelReasonDTO;
import com.clinic.admin.dtos.AppointmentRequestDTO;
import com.clinic.admin.dtos.AppointmentSearchDTO;
import com.clinic.admin.exceptions.ServiceNotAvailableException;
import com.clinic.admin.models.Appointment;
import com.clinic.admin.models.Doctor;
import com.clinic.admin.services.AppointmentService;
import com.clinic.admin.services.DoctorService;
import com.clinic.admin.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmed
 */

@RestController
@RequestMapping("/appointment")
public class AppointmentController {


    @Autowired
    AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;



    @GetMapping("{Id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("Id") Long Id){
        Appointment  appointment=  appointmentService.getAppointmentById(Id);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }


    //Admin can add new appointments.
    @PostMapping
    public ResponseEntity<Appointment> addNewAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO){
        Appointment  appointment=  appointmentService.addNewAppointment(appointmentRequestDTO);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }


    //Admin can cancel and log the reason.
    @PostMapping("cancel")
    public ResponseEntity<Appointment> cancelAppointment(@RequestBody AppointmentCancelReasonDTO appointmentCancelReasonDTO){
        Appointment  appointment =  appointmentService.cancelAppointment(appointmentCancelReasonDTO);
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }


    // Admin can filter appointments by date (future or history).
    //Admin can filter appointments by patient name (search field).
    //Admin can preview patient appointments history.

    @GetMapping("search")
    public ResponseEntity<List<Appointment>> SearchForAppointments(@RequestBody AppointmentSearchDTO appointmentSearchDTO){

        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments =appointmentService.SearchForAppointments(appointmentSearchDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(appointments,HttpStatus.OK);
    }






}
