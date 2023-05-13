package com.clinic.admin.repo;

import com.clinic.admin.models.Appointment;
import com.clinic.admin.repo.helpers.AppointmentCustomRepository;
import com.clinic.admin.utils.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Ahmed
 */

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> , AppointmentCustomRepository {

    @Query("SELECT a FROM Appointment a WHERE a.appointmentType=:appointmentType and  a.appointmentDate = :today")
    List<Appointment> findAllWithCreationDateTimeBefore(
            @Param("today") Date today, @Param("appointmentType") AppointmentType appointmentType

    );

    @Query("SELECT a FROM Appointment a WHERE  a.appointmentDate >=:fromDate and a.appointmentDate <=:toDate")
    List<Appointment> findAllBetweenDates(
            @Param("fromDate") Date fromDate,@Param("toDate") Date toDate
    );

    @Query(value = "select * from appointments  a join appointment_patients pp on a.id =pp.appointment_id join patients p on p.id=pp.patient_id where p.name =:patientName",nativeQuery = true)
    Optional<List<Appointment>> findAllByPatientName(
            @Param("patientName") String patientName
    );

}
