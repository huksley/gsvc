package ru.goverment.service;

/**
 * Possible states of {@link Request}
 * Poor man`s enum, caused by Axis2 unable to handle enums.
 * 
 * @author ruslan
 */
public class RequestState {
	/**
	 * New request, avaiable for processing
	 */
	public static final int NEW = 1;
	
	/**
	 * Accepted for processing
	 */
	public static final int ACCEPTED = 2;
	
	/**
	 * Fullfilled, response sent using {@link GovermentService#sendResponse(String, Response)}
	 */
	public static final int FULLFILLED = 3;
	
	/**
	 * Error during processing, unable to process request.
	 */
	public static final int ERROR = 4;
}
