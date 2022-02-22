package com.robot.apocalypse.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.apocalypse.dto.Robot;
import com.robot.apocalypse.dto.Survivor;
import com.robot.apocalypse.service.RobotService;

@RestController
@RequestMapping(value = "/robots")
public class RobotController {
	
	@Autowired
	private RobotService robotService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<Robot>> getAllRobot() {
		List<Robot> robots = robotService.getRobots();
		Collections.sort(robots, (robot1, robot2) -> robot1.getCategory().compareTo(robot2.getCategory()));
		return new ResponseEntity<List<Robot>>(robots, HttpStatus.OK);
	}
}
