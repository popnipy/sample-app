package com.example.schoolservices.springcloudconsulschool.delegate;

import java.util.Date;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentServiceDelegate {
/*
JSONParser parser = new JSONParser();
	JSONPObject json = (JSONPObject) parser.parse(stringToParse);
*/



	@Autowired
	RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "callStudentServiceAndGetStudentsBySchoolName_Fallback")
	public String callStudentServiceAndGetStudentsBySchoolName(String schoolname) {
		System.out.println("Consul Demo - Getting School details for " +schoolname);
		String response = restTemplate.exchange("http://student-service/api/students/schoolname/{schoolname}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, schoolname).getBody();


		System.out.println("Response Received as " + response + " -  " + new Date());

		return "School Name -  " + schoolname + " :::  Student Details " + response + " -  " + new Date();
		//return response;
	}

	@SuppressWarnings("unused")
	private String callStudentServiceAndGetStudentsBySchoolName_Fallback(String schoolname) {

		System.out.println("Student Service is down!!! fallback route enabled...");

		return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
				" Service will be back shortly - " + new Date();
	}




	@HystrixCommand(fallbackMethod = "callStudentServiceAndGetStudentsByStudentAge_Fallback")
	public String callStudentServiceAndGetStudentsByStudentAge(int studentage) {
		System.out.println("Consul Demo - Getting School details for " + studentage);
		String response = restTemplate.exchange("http://student-service/api/students/studentage/{studentage}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, studentage).getBody();

      if (studentage>18){


	return "Higher Level Class -   :::  Student Details " + response + " -  " + new Date();
}
		else if(studentage<=18)

		System.out.println("Response Received as " + response + " -  " + new Date());

		return "Primary Class -   :::  Student Details " + response + " -  " + new Date();
	}

	@SuppressWarnings("unused")
	private String callStudentServiceAndGetStudentsByStudentAge_Fallback(int studentage) {
		System.out.println("Student Service is down!!! fallback route enabled...");

		return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
				" Service will be back shortly - " + new Date();
	}







	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
