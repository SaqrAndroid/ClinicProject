package com.clinic.admin.controllers;

import com.clinic.admin.dtos.AppointmentRequestDTO;
import com.clinic.admin.models.Appointment;
import com.clinic.admin.services.HomeService;
import com.github.javafaker.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ahmed
 */


@RestController
@RequestMapping("/")
public class HomeController {


    @Autowired
    HomeService homeService;


    //Admin can open a home page which lists all today's appointments.
    @GetMapping("dashboard")
    public ResponseEntity<List<Appointment>> getAllTodayAppointments()
    {
        List<Appointment> dashboardData = homeService.getDashboardData();
        return new ResponseEntity<>(dashboardData,HttpStatus.OK) ;
    }

}
