package com.robot.apocalypse.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SurvivorReport {
	private BigDecimal infectedSurvivorsPercentage;
	private BigDecimal nonInfectedSurvivorsPercentage;
	private List<Survivor> infectedSurvivors = new ArrayList<>();
	private List<Survivor> nonInfectedSurvivors = new ArrayList<>();
	private List<Robot> robots = new ArrayList<>();

	public BigDecimal getInfectedSurvivorsPercentage() {
		return infectedSurvivorsPercentage;
	}

	public void setInfectedSurvivorsPercentage(BigDecimal infectedSurvivorsPercentage) {
		this.infectedSurvivorsPercentage = infectedSurvivorsPercentage;
	}

	public BigDecimal getNonInfectedSurvivorsPercentage() {
		return nonInfectedSurvivorsPercentage;
	}

	public void setNonInfectedSurvivorsPercentage(BigDecimal nonInfectedSurvivorsPercentage) {
		this.nonInfectedSurvivorsPercentage = nonInfectedSurvivorsPercentage;
	}

	public List<Survivor> getInfectedSurvivors() {
		return infectedSurvivors;
	}

	public void setInfectedSurvivors(List<Survivor> infectedSurvivors) {
		this.infectedSurvivors = infectedSurvivors;
	}

	public List<Survivor> getNonInfectedSurvivors() {
		return nonInfectedSurvivors;
	}

	public void setNonInfectedSurvivors(List<Survivor> nonInfectedSurvivors) {
		this.nonInfectedSurvivors = nonInfectedSurvivors;
	}

	public List<Robot> getRobots() {
		return robots;
	}

	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}

}
