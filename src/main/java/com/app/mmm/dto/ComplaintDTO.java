package com.app.mmm.dto;

import java.util.List;

import com.app.mmm.enums.ComplaintType;
import com.app.mmm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ComplaintDTO {

	private ComplaintType complaintType;
	private String complaintDescription;
	private Status status;
	private String location;
	private int likes;
	private String imagePath;
	private CitizenDto citizen;
	private List<FeedbackDTO> feedbacks;
	private TeamDTO team;

	public ComplaintDTO(ComplaintType complaintType, String complaintDescription, Status status, String location,
			int likes, String imagePath, CitizenDto citizen, List<FeedbackDTO> feedbacks, TeamDTO team) {
		this.complaintType = complaintType;
		this.complaintDescription = complaintDescription;
		this.status = status;
		this.location = location;
		this.likes = likes;
		this.imagePath = imagePath;
		this.citizen = citizen;
		this.feedbacks = feedbacks;
		this.team = team;
	}
}
