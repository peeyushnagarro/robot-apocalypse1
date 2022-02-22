package com.robot.apocalypse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.robot.apocalypse.dto.Location;
import com.robot.apocalypse.dto.Survivor;
import com.robot.apocalypse.dto.SurvivorReport;
import com.robot.apocalypse.service.SurvivorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value = "/survivors")
public class SurvivorController {
	@Autowired
	private SurvivorService survivorService;

	@Value("${survivor.delete.success.msg}")
	private String survivorDeleteSuccessMsg;

	@Value("${contamination.reported.success.msg}")
	private String contaminationReportedSuccessMsg;

	@Operation(summary = "Get list of all survivours")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get list of all survivours", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Survivor.class))) }) })
	@GetMapping(value = "")
	public ResponseEntity<List<Survivor>> getAllSurvivour() {
		return new ResponseEntity<List<Survivor>>(survivorService.getAllSurvivor(), HttpStatus.OK);
	}

	@Operation(summary = "Get details of survivour with given id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get details of survivour with given id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping(value = "/{survivorId}")
	public ResponseEntity<Survivor> getSurvivorById(
			@Parameter(description = "Id of survivour to be searched") @PathVariable("survivorId") Long survivorId) {
		return new ResponseEntity<Survivor>(survivorService.getSurvivorById(survivorId), HttpStatus.OK);
	}

	@Operation(summary = "Create a new survivor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Create a new survivor", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping(value = "")
	public ResponseEntity<Survivor> createSurvivor(@Valid @RequestBody Survivor survivor) {
		return new ResponseEntity<Survivor>(survivorService.saveSurvivor(survivor), HttpStatus.CREATED);
	}

	@Operation(summary = "Update survivor details for given id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update survivor details for given id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@PutMapping(value = "/{survivorId}")
	public ResponseEntity<Survivor> updateSurvivor(
			@Parameter(description = "Id of survivour to be updated") @PathVariable("survivorId") Long survivorId,
			@Valid @RequestBody Survivor survivor) {
		return new ResponseEntity<Survivor>(survivorService.updateSurvivor(survivorId, survivor), HttpStatus.OK);
	}

	@Operation(summary = "Delete survivor details for given id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete survivor details for given id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@DeleteMapping(value = "/{survivorId}")
	public ResponseEntity<String> deleteSurvivor(
			@Parameter(description = "Id of survivour to be deleted") @PathVariable("survivorId") Long survivorId) {
		survivorService.deleteSurvivor(survivorId);
		return new ResponseEntity<String>(String.format(survivorDeleteSuccessMsg, Long.toString(survivorId)),
				HttpStatus.OK);
	}

	@Operation(summary = "Update last location of survivor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "update last location of survivor", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@PutMapping(value = "/{survivorId}/location")
	public ResponseEntity<Survivor> updateSurvivorLocation(
			@Parameter(description = "Id of survivour for which location needs to be updated") @PathVariable("survivorId") Long survivorId,
			@Valid @RequestBody Location lastLocation) {
		return new ResponseEntity<Survivor>(survivorService.updateLastLocation(survivorId, lastLocation),
				HttpStatus.OK);
	}

	@Operation(summary = "Report contamination of a survivor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Report contamination of a survivor", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json") }) })
	@PutMapping(value = "/report/contamination")
	public ResponseEntity<String> reportContamination(
			@Parameter(description = "Id of survivor which is reporting contamination") @RequestParam("reportingSurvivorId") Long reportingSurvivorId,
			@Parameter(description = "Id of survivor for which contamination to be reported") @RequestParam("survivorId") Long survivorId) {
		survivorService.reportContamination(reportingSurvivorId, survivorId);
		return new ResponseEntity<String>(String.format(contaminationReportedSuccessMsg, Long.toString(survivorId),
				Long.toString(reportingSurvivorId)), HttpStatus.OK);
	}

	@Operation(summary = "To get repots")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "To get repots", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Survivor.class)) }) })
	@GetMapping(value = "/report")
	public ResponseEntity<SurvivorReport> generateReport() {
		return new ResponseEntity<SurvivorReport>(survivorService.generateReport(), HttpStatus.OK);
	}
}
