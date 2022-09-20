package com.example.demo.exception;

public class DoctorExistedException extends Exception {
	public DoctorExistedException() {
		super("Doctor exist in the database");
	}

}
