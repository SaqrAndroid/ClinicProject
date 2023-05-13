package com.clinic.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ahmed
 */


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;

    private  String email;

    private  String mobile;


//    @ManyToMany(mappedBy = "patients")
//    Set<Appointment> appointments;

//    @ManyToMany(mappedBy = "patients", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<Appointment> appointments = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "patients")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(email, patient.email) && Objects.equals(mobile, patient.mobile) && Objects.equals(appointments, patient.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, mobile, appointments);
    }



}
