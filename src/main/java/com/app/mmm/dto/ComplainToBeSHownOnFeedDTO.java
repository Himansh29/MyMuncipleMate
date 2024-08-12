package com.app.mmm.dto;

import com.app.mmm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ComplainToBeSHownOnFeedDTO {

	private byte[] imageData;
	private String location;
	private Status status;
	private String pname;

	public ComplainToBeSHownOnFeedDTO(byte[] imageData, String location, Status status, String pname) {
		this.imageData = imageData;
		this.location = location;
		this.status = status;
		this.pname = pname;
	}
}
