package com.robot.apocalypse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robot.apocalypse.dto.Robot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

@Service
public class RobotService {
	
	@Value("${robot.service.url}")
	private String robotServiceUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Robot> getRobots() {
		ResponseEntity<List<Robot>> responseEntity = restTemplate.exchange(robotServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Robot>>() {});
		return responseEntity.getBody();
	}
}
