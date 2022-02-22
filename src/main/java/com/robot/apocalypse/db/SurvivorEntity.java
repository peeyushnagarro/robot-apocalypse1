package com.robot.apocalypse.db;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SURVIVOR")
public class SurvivorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SURVIVOR_ID")
	private Long id;

	@Column(name = "SURVIVOR_NAME")
	private String name;

	@Column(name = "AGE")
	private int age;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "LATITUDE")
	private BigDecimal latitude;

	@Column(name = "LONGITUDE")
	private BigDecimal longitude;

	@Column(name = "IS_INFECTED")
	private boolean isInfected;

	@Column(name = "REPORTED_CONTAMINATION_NUMBER")
	private int reportedContaminationNumber;

	@Column(name = "REPORTED_CONTAMINATION_SURVIVOR")
	private String reportedContaminationSurvivors;

	@ElementCollection
	private List<String> resources;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public int getReportedContaminationNumber() {
		return reportedContaminationNumber;
	}

	public void setReportedContaminationNumber(int reportedContaminationNumber) {
		this.reportedContaminationNumber = reportedContaminationNumber;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public String getReportedContaminationSurvivors() {
		return reportedContaminationSurvivors;
	}

	public void setReportedContaminationSurvivors(String reportedContaminationSurvivors) {
		this.reportedContaminationSurvivors = reportedContaminationSurvivors;
	}

}
