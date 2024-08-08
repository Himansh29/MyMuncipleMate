package com.app.mmm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.mmm.entity.ComplaintType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class AddComplaintDTO {

	@NotNull(message = "Status is required")
    private ComplaintType complaintType;
    @NotEmpty(message = "Description must not be empty")
    private String complaintDescription;

    @NotEmpty(message = "Location must not be empty")
    private String location;
    
    private String imagePath;
}
