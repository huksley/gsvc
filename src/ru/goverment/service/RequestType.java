package ru.goverment.service;

/**
 * Types of requests.
 * 
 * @author ruslan
 */
public class RequestType {
	
	/**
	 * Return all information about available accounts or credits of client.
	 */
	public static final int INQUIRY = 1;
	
	/**
	 * Arrest, block all (or specific accounts in {@link Request#accounts}) money
	 * and return balance or arrest date.
	 */
	public static final int ARREST = 2;
}
