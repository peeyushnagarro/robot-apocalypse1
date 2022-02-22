package com.robot.apocalypse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.robot.apocalypse.dto.Location;
import com.robot.apocalypse.dto.Survivor;
import com.robot.apocalypse.repository.SurvivorRepository;
import com.robot.apocalypse.service.SurvivorService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.robot.apocalypse.util.TransFormUtil;
import com.robot.apocalypse.db.SurvivorEntity;
import com.robot.apocalypse.exception.SurvivorInfectedException;
import com.robot.apocalypse.exception.ContaminationAlreadyReportedException;
import com.robot.apocalypse.dto.SurvivorReport;
import com.robot.apocalypse.service.RobotService;
import java.math.BigDecimal;
import com.robot.apocalypse.dto.Robot;
import java.util.Arrays;
import org.springframework.dao.EmptyResultDataAccessException;
import java.math.RoundingMode;

@Service
public class SurvivorServiceImpl implements SurvivorService {
	private static String SURVIVOR_NOT_FOUND_EXCEPTION_MSG = "Survivor not found for given survivor id : %s";

	@Autowired
	private SurvivorRepository survivorRepository;

	@Autowired
	private RobotService robotService;

	@Value("${infection.confirmation.count:3}")
	private int infectionConfirmationCount;

	@Value("${survivorInfectedException.msg}")
	private String survivorInfectedExceptionMsg;

	@Value("${contaminationAlreadyReportedException.msg}")
	private String contaminationAlreadyReportedExceptionMsg;

	private static BigDecimal BIGDECIMAL_HUNDREAD = new BigDecimal(100);

	@Override
	public List<Survivor> getAllSurvivor() {
		return TransFormUtil.convertSurvivorList(survivorRepository.findAll());
	}

	@Override
	public Survivor getSurvivorById(Long survivorId) {
		return TransFormUtil.convertSurvivor(survivorRepository.getById(survivorId));
	}

	@Override
	public Survivor saveSurvivor(Survivor survivor) {
		return TransFormUtil
				.convertSurvivor(survivorRepository.saveAndFlush(TransFormUtil.convertSurvivorEntity(survivor)));
	}

	@Override
	public Survivor updateSurvivor(Long survivorId, Survivor survivor) {
		survivorRepository.getById(survivorId);
		survivor.setId(survivorId);
		return saveSurvivor(survivor);
	}

	@Override
	public void deleteSurvivor(Long survivorId) {
		try {
			survivorRepository.deleteById(survivorId);
		} catch(EmptyResultDataAccessException exception) {
			throw new EmptyResultDataAccessException(String.format(SURVIVOR_NOT_FOUND_EXCEPTION_MSG, survivorId), 1);
		}
	}

	@Override
	public Survivor updateLastLocation(Long survivorId, Location lastLocation) {
		SurvivorEntity survivorEntity = survivorRepository.getById(survivorId);
		survivorEntity.setLatitude(lastLocation.getLatitude());
		survivorEntity.setLongitude(lastLocation.getLongitude());
		return TransFormUtil.convertSurvivor(survivorRepository.saveAndFlush(survivorEntity));
	}

	@Override
	public void reportContamination(Long reportingSurvivorId, Long survivorId) {
		// Get entity details for survivor which is reporting contamination.
		SurvivorEntity reportingSurvivorEntity = survivorRepository.getById(reportingSurvivorId);

		if (reportingSurvivorEntity.isInfected()) {
			throw new SurvivorInfectedException(String.format(survivorInfectedExceptionMsg, reportingSurvivorId));
		}

		// Get entity details for survivor for which contamination is reported.
		SurvivorEntity survivorEntity = survivorRepository.getById(survivorId);
		
		if (survivorEntity.getReportedContaminationNumber() == 0) {
			survivorEntity.setReportedContaminationNumber(1);
			survivorEntity.setReportedContaminationSurvivors(Long.toString(reportingSurvivorEntity.getId()));
		} else {
			List<String> reportedContaminationSurvivorIds = Arrays
					.asList(survivorEntity.getReportedContaminationSurvivors().split(","));

			if (reportedContaminationSurvivorIds.contains(Long.toString(reportingSurvivorId))) {
				throw new ContaminationAlreadyReportedException(
						String.format(contaminationAlreadyReportedExceptionMsg, survivorId, reportingSurvivorId));
			}

			survivorEntity.setReportedContaminationNumber(survivorEntity.getReportedContaminationNumber() + 1);
			survivorEntity.setReportedContaminationSurvivors(survivorEntity.getReportedContaminationSurvivors()
					.concat(",").concat(Long.toString(reportingSurvivorEntity.getId())));

			if (survivorEntity.getReportedContaminationNumber() == infectionConfirmationCount) {
				survivorEntity.setInfected(true);
			}
		}

		survivorRepository.saveAndFlush(survivorEntity);

	}

	@Override
	public SurvivorReport generateReport() {
		List<SurvivorEntity> survivorEntities = survivorRepository.findAll();

		List<Survivor> infectedSurvivors = survivorEntities.stream()
				.filter(survivorEntity -> survivorEntity.isInfected())
				.map(survivorEntity -> TransFormUtil.convertSurvivor(survivorEntity)).collect(Collectors.toList());

		List<Survivor> nonInfectedSurvivors = survivorEntities.stream()
				.filter(survivorEntity -> !survivorEntity.isInfected())
				.map(survivorEntity -> TransFormUtil.convertSurvivor(survivorEntity)).collect(Collectors.toList());

		List<Robot> robots = robotService.getRobots();

		SurvivorReport report = new SurvivorReport();
		BigDecimal survivorEntitiesSize = new BigDecimal(survivorEntities.size());

		report.setInfectedSurvivorsPercentage(BigDecimal.ZERO.equals(survivorEntitiesSize) ? BigDecimal.ZERO
				: (new BigDecimal(infectedSurvivors.size()).divide(survivorEntitiesSize, 2, RoundingMode.HALF_EVEN))
						.multiply(BIGDECIMAL_HUNDREAD));

		report.setNonInfectedSurvivorsPercentage(BigDecimal.ZERO.equals(survivorEntitiesSize) ? BigDecimal.ZERO
				: BIGDECIMAL_HUNDREAD.subtract(report.getInfectedSurvivorsPercentage()));

		report.setInfectedSurvivors(infectedSurvivors);
		report.setNonInfectedSurvivors(nonInfectedSurvivors);
		report.setRobots(robots);

		return report;
	}

}
