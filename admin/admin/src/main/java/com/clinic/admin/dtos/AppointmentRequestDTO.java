package com.clinic.admin.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Ahmed
 */
public record AppointmentRequestDTO( String patientName,String doctorName,Date appointmentDate) implements Serializable {

}
