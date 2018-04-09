package com.example.schoolservices.springcloudconsulschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolservices.springcloudconsulschool.delegate.StudentServiceDelegate;

@RestController
@RequestMapping("/getDetailsOf")
public class SchoolServiceController {
	
	@Autowired
	StudentServiceDelegate studentServiceDelegate;

	@RequestMapping(value = "/school/{schoolname}", method = RequestMethod.GET)
	public String getStudentsBySchool(@PathVariable String schoolname) {
		System.out.println("Going to call student service to get data!");
		return studentServiceDelegate.callStudentServiceAndGetStudentsBySchoolName(schoolname);
	}

	@RequestMapping(value = "/class/{studentage}", method = RequestMethod.GET)
	public String getStudentsByClassLevel(@PathVariable int studentage) {
		System.out.println("Going to call student service to get data!");
		return studentServiceDelegate.callStudentServiceAndGetStudentsByStudentAge(studentage);
	}


	
}
