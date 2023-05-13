package com.clinic.admin.repo.helpers;

import com.clinic.admin.models.Appointment;

import java.util.List;

/**
 * @author Ahmed
 */
public interface AppointmentCustomRepository {
    List<Appointment> findCustomNativeQuery(String query);
}
