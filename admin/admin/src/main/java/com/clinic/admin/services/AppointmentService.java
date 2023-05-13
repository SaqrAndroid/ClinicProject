package com.clinic.admin.services;

import com.clinic.admin.dtos.AppointmentCancelReasonDTO;
import com.clinic.admin.dtos.AppointmentRequestDTO;
import com.clinic.admin.dtos.AppointmentSearchDTO;
import com.clinic.admin.exceptions.ResourceNotFoundException;
import com.clinic.admin.models.Appointment;
import com.clinic.admin.models.Doctor;
import com.clinic.admin.models.Patient;
import com.clinic.admin.repo.AppointmentRepository;
import com.clinic.admin.repo.DoctorRepository;
import com.clinic.admin.repo.PatientRepository;
import com.clinic.admin.utils.AppointmentType;
import com.clinic.admin.utils.CancellationReason;
import com.clinic.admin.utils.QueryHelper;
import com.github.javafaker.App;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ahmed
 */

@Service
public class AppointmentService {


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    AppointmentRepository appointmentRepo;
    @Autowired
    PatientRepository patientRepo;
    @Autowired
    DoctorRepository doctorRepo;

    @Autowired
    DoctorService doctorService;

    public Appointment getAppointmentById(Long Id) {
        return appointmentRepo.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Cannot Find Appointment With Id :  %s", Id))
        );
    }


    public Appointment addNewAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Patient patient = patientRepo.findByName(appointmentRequestDTO.patientName()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Cannot Find Patient With Name:  %s", appointmentRequestDTO.patientName())));

        Doctor doctor = doctorService.getDoctorByName(appointmentRequestDTO.doctorName());

        Appointment appointment = new Appointment();
        appointment.setPatients(Set.of(patient));
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentRequestDTO.appointmentDate());
        appointment.setAppointmentType(AppointmentType.PENDING);
        return appointmentRepo.save(appointment);

    }

    public Appointment cancelAppointment(AppointmentCancelReasonDTO appointmentCancelReasonDTO) {
        Appointment appointment = appointmentRepo.findById(appointmentCancelReasonDTO.appointmentId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Cannot Find Appointment With Id :  %s", appointmentCancelReasonDTO.appointmentId()))
        );
        appointment.setAppointmentType(AppointmentType.CANCELLED);
        appointment.setCancellationReason(CancellationReason.valueOf(appointmentCancelReasonDTO.cancelReason()));
        return appointmentRepo.save(appointment);
    }


    public List<Appointment> SearchForAppointmentsByPatientName(String patientName) {
        List<Appointment> appointmentList = appointmentRepo.findAllByPatientName(patientName).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Cannot Find Appointments For Patient :  %s", patientName))
        );
        return appointmentList;
    }


    /*
    {

      "fromDate":"2023-05-09",
      "toDate":"2023-05-09",
      "appointmentType": "PENDING",
      "cancellationReason": "NO_SHOW",
      "patientName" :"Estell"
    }
   */

    public List<Appointment> SearchForAppointments(AppointmentSearchDTO appointmentSearchDTO) {


        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(" SELECT a FROM Appointment a JOIN a.patients p ");
        queryBuilder.append(QueryHelper.WHERE);

        if(appointmentSearchDTO.fromDate() != null)
        {
            queryBuilder.append(" a.appointmentDate >='" + DATE_FORMAT.format(appointmentSearchDTO.fromDate()) + "'");
        }

        if (appointmentSearchDTO.fromDate() != null && appointmentSearchDTO.toDate() != null) {
            queryBuilder.append(QueryHelper.AND);
            queryBuilder.append("  a.appointmentDate <='" + DATE_FORMAT.format(appointmentSearchDTO.toDate()) + "' ");
        }

        if (!"".equalsIgnoreCase(appointmentSearchDTO.patientName()) && appointmentSearchDTO.patientName() != null) {
            queryBuilder.append(QueryHelper.AND);
            queryBuilder.append("  p.name = '" + appointmentSearchDTO.patientName() + "' ");
        }

        if (appointmentSearchDTO.appointmentType() != null) {
            queryBuilder.append(QueryHelper.AND);
            queryBuilder.append("  a.appointmentType = " + appointmentSearchDTO.appointmentType());
        }

        if (appointmentSearchDTO.cancellationReason() != null) {
            queryBuilder.append(QueryHelper.AND);
            queryBuilder.append("  a.cancellationReason = " + appointmentSearchDTO.cancellationReason());
        }


        return appointmentRepo.findCustomNativeQuery(queryBuilder.toString());
    }
}
