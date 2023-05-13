package com.clinic.admin.models;

import com.clinic.admin.utils.AppointmentType;
import com.clinic.admin.utils.CancellationReason;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ahmed
 */


@Entity
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;




    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "appointment_patients",
            joinColumns = { @JoinColumn(name = "appointment_id") },
            inverseJoinColumns = { @JoinColumn(name = "patient_id") })
    private Set<Patient> patients = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;


    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;


    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public CancellationReason getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(patients, that.patients) && Objects.equals(doctor, that.doctor) && cancellationReason == that.cancellationReason && appointmentType == that.appointmentType && Objects.equals(createdTime, that.createdTime) && Objects.equals(lastModifiedTime, that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointmentDate, patients, doctor, cancellationReason, appointmentType, createdTime, lastModifiedTime);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment{id=").append(id);
        sb.append(", appointmentDate=").append(appointmentDate);
        sb.append(", doctor=").append(doctor.getName()).append("(id=").append(doctor.getId()).append(")");
        sb.append(", patients=").append(patients);
        sb.append('}');
        return sb.toString();
    }

}
