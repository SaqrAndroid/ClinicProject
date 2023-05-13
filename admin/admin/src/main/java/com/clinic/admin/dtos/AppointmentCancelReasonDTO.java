package com.clinic.admin.dtos;

import java.io.Serializable;

/**
 * @author Ahmed
 */
public record AppointmentCancelReasonDTO(Long appointmentId,String cancelReason) implements Serializable {
}
