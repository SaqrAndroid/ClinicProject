package com.clinic.admin.repo;

import com.clinic.admin.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahmed
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


    public Optional<Patient> findByName(String name);
}
