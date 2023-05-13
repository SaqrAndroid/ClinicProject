package com.clinic.admin.utils;

/**
 * @author Ahmed
 */
public enum CancellationReason {

    NO_SHOW("NO_SHOW",1),
    BASED_ON_PATIENT_REQUEST("BASED_ON_PATIENT_REQUEST",2),
    PHYSICIAN_APOLOGY("PHYSICIAN_APOLOGY",3);

    public final String name;
    public final int id;
    private  CancellationReason(String  name , int id)
    {
        this.name = name;
        this.id = id;
    }

}
