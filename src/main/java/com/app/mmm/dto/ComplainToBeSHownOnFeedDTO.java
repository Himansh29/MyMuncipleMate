package com.app.mmm.dto;

import com.app.mmm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ComplainToBeSHownOnFeedDTO {

	private int likes;
	private String imagePath;
	private String location;
	private Status status;
}
