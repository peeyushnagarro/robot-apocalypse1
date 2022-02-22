package com.robot.apocalypse.dto;

import java.time.OffsetDateTime;

public class Robot {
	private String model;
	private String serialNumber;
	private OffsetDateTime manufacturedDate;
	private String category;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public OffsetDateTime getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(OffsetDateTime manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
