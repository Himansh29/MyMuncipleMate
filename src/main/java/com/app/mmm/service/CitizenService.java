package com.app.mmm.service;

import java.util.List;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.CitizenDto;
import com.app.mmm.dto.CitizenDtoWithComplaints;
import com.app.mmm.dto.CitizenSummaryDto;
import com.app.mmm.dto.AddComplaintDTO;
import com.app.mmm.dto.RoleAssignmentDTO;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.entity.Citizen;

public interface CitizenService {
	CitizenDtoWithComplaints getCitizen(Long id);
	ApiResponse deleteCitizenByID(Long id);
	List<AddComplaintDTO> getAllComplaintsByCitizen(Long id);
	CitizenDto updateCitizenById(Long id, CitizenDto citizenDto);
	// un-added 
	List<CitizenSummaryDto> getAllCitizen();
	ApiResponse addRoleToCitizen(RoleAssignmentDTO assignmentDTO);
	CitizenDto updatePartialCitizenDetails(Long citizenId, CitizenDto citizenDto);
}
