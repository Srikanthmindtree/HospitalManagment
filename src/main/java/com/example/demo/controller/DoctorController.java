package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Doctor;
import com.example.demo.exception.DoctorException;
import com.example.demo.exception.DoctorExistedException;
import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.repository.DoctorRepository;

@RestController
public class DoctorController {
	@Autowired
	private DoctorRepository repository;
	@GetMapping("/doctor")
	public List<Doctor> getAllDoctors()
	{
		return repository.findAll();
	}
	@GetMapping("/doctor/{id}")
	Doctor getDoctor(@PathVariable Integer id) throws DoctorException, DoctorNotFoundException
	{return repository.findById(id).orElseThrow(()-> new DoctorNotFoundException(id));
		
	}
	@PostMapping("/doctor/save")
	public Doctor saveDoctor(@RequestBody Doctor doctor) throws DoctorExistedException
	{
		if(repository.existsById(doctor.getId()))
		{
			throw new DoctorExistedException();
		}
		return repository.save(doctor);
	}
	@PutMapping("/doctor/salary/{id}/{salary}")
	ResponseEntity<Doctor> updateSalary(@PathVariable Integer id,@PathVariable double salary) throws DoctorNotFoundException,DoctorExistedException,DoctorException
{
	Doctor d=getDoctor(id);
	
	d.setSalary(salary);
	repository.save(d);
	return ResponseEntity.ok(d);
}
	@DeleteMapping("/doctor/{id}")
	void deleteDoctor(@PathVariable Integer id)
	{
		repository.deleteById(id);
	}
	
	@GetMapping("/desc")
	public List<Doctor> getAllDoctorDescendingBySalary(){
		List<Doctor> s= repository.findAll();
		return repository.findAll(Sort.by(Direction.DESC,"salary").and(Sort.by(Sort.Direction.ASC,"name")));
	}
	

}
