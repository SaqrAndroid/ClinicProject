package com.clinic.admin.exceptions;

/**
 * @author Ahmed
 */
public class ServiceNotAvailableException extends RuntimeException{

    public ServiceNotAvailableException() {
        super();
    }
    public ServiceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceNotAvailableException(String message) {
        super(message);
    }
    public ServiceNotAvailableException(Throwable cause) {
        super(cause);
    }
}
