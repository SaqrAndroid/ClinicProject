package com.clinic.admin.dtos;

import com.clinic.admin.utils.AppointmentType;
import com.clinic.admin.utils.CancellationReason;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author Ahmed
 */
public record AppointmentSearchDTO(String patientName, Date fromDate, Date toDate, AppointmentType appointmentType,
                                   CancellationReason cancellationReason) {
}
