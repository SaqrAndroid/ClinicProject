package com.clinic.doctor.repo;

import com.clinic.doctor.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahmed
 */

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    public Optional<Doctor> findByName(String name);
}
