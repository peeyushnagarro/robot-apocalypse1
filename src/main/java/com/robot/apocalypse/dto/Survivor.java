package com.robot.apocalypse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.robot.constraints.Gender;

import javax.validation.constraints.Min;
import javax.validation.Valid;

@Component
public class Survivor {
	private Long id;

	@NotBlank(message = "Name of surivor should be present")
	private String name;
	
	@Gender(message= "Valid values for gender field are [male, female](case-insensitive)")
	private String gender;
	
	@Min(1)
	private int age;
	private List<String> resources = new ArrayList<>();

	@NotNull(message = "Last location of survivor should be present")
	@Valid
	private Location lastLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

}
