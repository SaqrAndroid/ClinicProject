package com.clinic.admin.repo;

import com.clinic.admin.models.Doctor;
import com.clinic.admin.models.Patient;
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
