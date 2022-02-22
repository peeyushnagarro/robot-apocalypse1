package com.robot.apocalypse.service;

import java.util.List;

import com.robot.apocalypse.dto.Location;
import com.robot.apocalypse.dto.Survivor;
import com.robot.apocalypse.dto.SurvivorReport;

public interface SurvivorService {
	public List<Survivor> getAllSurvivor();
	
	public Survivor getSurvivorById(Long survivorId);
	
	public Survivor saveSurvivor(Survivor survivor);
	
	public Survivor updateSurvivor(Long survivorId, Survivor survivor);
	
	public void deleteSurvivor(Long survivorId);
	
	public Survivor updateLastLocation(Long survivorId, Location lastLocation);
	
	public void reportContamination(Long reportingSurvivorId, Long survivorId);
	
	public SurvivorReport generateReport();
	
}
