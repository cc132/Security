package com.example.demo.multidatasource.exception;

public class ServiceException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8594110016189090540L;

	public ServiceException(String msg, Exception e) {
        super(msg + "\n" + e.getMessage());
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
