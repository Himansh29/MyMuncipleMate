package com.app.mmm.service;

import java.util.List;

import com.app.mmm.dto.AddComplaintDTO;
import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ComplainToBeSHownOnFeedDTO;
import com.app.mmm.dto.ComplaintDTO;
import com.app.mmm.enums.ComplaintType;
import com.app.mmm.exception.ResourceNotFoundException;

public interface ComplaintService {

	ApiResponse addComplaint(AddComplaintDTO complaintDTO, String email, ComplaintType complaintType) throws ResourceNotFoundException;
    ComplaintDTO getComplaintById(Long id); 
    ApiResponse deleteComplaintById(Long id);
    List<ComplainToBeSHownOnFeedDTO> getAllComplaints();
    List<ComplaintDTO> getComplaintsByStatus(String status);
	ApiResponse changeStatus(Long id, String status); 
	ApiResponse markComplaintAsResolved(Long id);
    ApiResponse markComplaintAsOpen(Long id);
    
}
