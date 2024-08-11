package com.app.mmm.serviceimple;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mmm.dto.AddComplaintDTO;
import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ComplainToBeSHownOnFeedDTO;
import com.app.mmm.dto.ComplaintDTO;
import com.app.mmm.entity.Citizen;
import com.app.mmm.entity.Complaint;
import com.app.mmm.enums.ComplaintType;
import com.app.mmm.enums.Status;
import com.app.mmm.exception.ResourceNotFoundException;
import com.app.mmm.repository.CitizenRepository;
import com.app.mmm.repository.ComplaintRepository;
import com.app.mmm.service.ComplaintService;

@Service
@Transactional
public class ComplaintServiceImple implements ComplaintService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Override
    public ApiResponse addComplaint(AddComplaintDTO complaintDTO, Long citizenId, ComplaintType complaintType) throws ResourceNotFoundException {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen Id not found"));
        
        Complaint complaint = mapper.map(complaintDTO, Complaint.class);
        complaint.setComplaintType(complaintType);
        complaint.setStatus(Status.OPEN);
        complaint.setCitizen(citizen);
        complaintRepository.save(complaint);
        
        return new ApiResponse("Complaint added successfully");
    }

	@Override
	public ComplaintDTO getComplaintById(Long id) {
		Complaint complaint = complaintRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint Id not found"));
		return mapper.map(complaint, ComplaintDTO.class);
	}

	@Override
	public ApiResponse deleteComplaintById(Long id) {
		Complaint complaint = complaintRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint Id not found"));
		complaintRepository.delete(complaint);
		return new ApiResponse("Complaint Deleted with id: " + id);
	}

	@Override
	public List<ComplainToBeSHownOnFeedDTO> getAllComplaints() {
		return complaintRepository.findAll().stream().map(complaint -> mapper.map(complaint, ComplainToBeSHownOnFeedDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ComplaintDTO> getComplaintsByStatus(String status) {
		Status complaintStatus = Status.valueOf(status.toUpperCase());
		List<Complaint> complaintDTO = complaintRepository.findByStatus(complaintStatus);
		return complaintDTO.stream().map(dto -> mapper.map(dto, ComplaintDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse markComplaintAsResolved(Long id) {
        return changeStatus(id, "RESOLVED");
    }

	@Override
    public ApiResponse markComplaintAsOpen(Long id) {
        return changeStatus(id, "OPEN");
    }
	
	@Override
    public ApiResponse changeStatus(Long id, String status) {
		try {
			Status newStatus;
			try {
				newStatus = Status.valueOf(status.toUpperCase());
			} catch (IllegalArgumentException e) {
				return new ApiResponse("Invalid status value");
			}

			Complaint complaint = complaintRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Complaint not found"));

			complaint.setStatus(newStatus);

			complaintRepository.save(complaint);

			return new ApiResponse("Status updated successfully");
		} catch (Exception e) {
			return new ApiResponse(e.getMessage());
		}
    }
}
