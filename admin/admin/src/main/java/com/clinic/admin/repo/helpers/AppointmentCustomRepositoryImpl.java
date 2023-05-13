package com.clinic.admin.repo.helpers;

import com.clinic.admin.models.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ahmed
 */
public class AppointmentCustomRepositoryImpl implements AppointmentCustomRepository {


    @Autowired
    private EntityManager em;
    @Override
    public List<Appointment> findCustomNativeQuery(String query) {
        TypedQuery<Appointment> result = em.createQuery(query, Appointment.class);
        List<Appointment> appointments = result.getResultList();
        return appointments;
    }
}
