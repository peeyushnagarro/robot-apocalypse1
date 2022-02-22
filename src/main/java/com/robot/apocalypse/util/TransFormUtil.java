package com.robot.apocalypse.util;

import java.util.List;
import java.util.stream.Collectors;

import com.robot.apocalypse.db.SurvivorEntity;
import com.robot.apocalypse.dto.Location;
import com.robot.apocalypse.dto.Survivor;
import org.springframework.util.StringUtils;

public class TransFormUtil {
	public static List<Survivor> convertSurvivorList(List<SurvivorEntity> survivorEntities) {
		return survivorEntities.stream().map(survivorEntity -> convertSurvivor(survivorEntity))
				.collect(Collectors.toList());
	}

	public static Survivor convertSurvivor(SurvivorEntity survivorEntity) {
		Survivor survivor = new Survivor();

		survivor.setId(survivorEntity.getId());
		survivor.setAge(survivorEntity.getAge());
		survivor.setName(survivorEntity.getName());
		survivor.setGender(survivorEntity.getGender());
		survivor.setLastLocation(new Location(survivorEntity.getLatitude(), survivorEntity.getLongitude()));
		survivor.setResources(survivorEntity.getResources());

		return survivor;
	}
	
	public static SurvivorEntity convertSurvivorEntity(Survivor survivor) {
		SurvivorEntity survivorEntity = new SurvivorEntity();
		
		survivorEntity.setId(survivor.getId());
		survivorEntity.setAge(survivor.getAge());
		survivorEntity.setName(StringUtils.capitalize(survivor.getName()));
		survivorEntity.setGender(StringUtils.capitalize(survivor.getGender()));
		survivorEntity.setLatitude(survivor.getLastLocation().getLatitude());
		survivorEntity.setLongitude(survivor.getLastLocation().getLongitude());
		survivorEntity.setResources(survivor.getResources());
		
		return survivorEntity;
	}
}
