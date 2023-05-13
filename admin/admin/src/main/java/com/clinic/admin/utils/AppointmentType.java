package com.clinic.admin.utils;

/**
 * @author Ahmed
 */
public enum AppointmentType {

    PENDING("PENDING",1),
    DONE("DONE",2),
    CANCELLED("CANCELLED",3);

    public final String name;
    public final int id;
    private  AppointmentType(String  name , int id)
    {
        this.name = name;
        this.id = id;
    }

}
