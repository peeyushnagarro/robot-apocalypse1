package com.robot.apocalypse.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Location {
	@NotNull(message = "Latitude should be present")
	private BigDecimal latitude;

	@NotNull(message = "Longitude should be present")
	private BigDecimal longitude;
	
	public Location() {
	}

	public Location(BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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

}
