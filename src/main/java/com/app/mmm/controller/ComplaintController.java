package com.app.mmm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mmm.dto.AddComplaintDTO;
import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ComplainToBeSHownOnFeedDTO;
import com.app.mmm.dto.ComplaintDTO;
import com.app.mmm.enums.ComplaintType;
import com.app.mmm.service.ComplaintService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Add Garbage Management Complaint")
    @PostMapping("/garbage-management/{citizenId}")
    public ResponseEntity<ApiResponse> addGarbageManagementComplaint(
            @PathVariable Long citizenId,
            @RequestBody AddComplaintDTO complaintDTO) {
        try {
            ApiResponse response = service.addComplaint(complaintDTO, citizenId, ComplaintType.GARBAGE_MANAGEMENT);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Add Water Supply Complaint")
    @PostMapping("/water-supply/{citizenId}")
    public ResponseEntity<ApiResponse> addWaterSupplyComplaint(
            @PathVariable Long citizenId,
            @RequestBody AddComplaintDTO complaintDTO) {
        try {
            ApiResponse response = service.addComplaint(complaintDTO, citizenId, ComplaintType.WATER_SUPPLY);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Add Electricity Management Complaint")
    @PostMapping("/electricity-management/{citizenId}")
    public ResponseEntity<ApiResponse> addElectricityManagementComplaint(
            @PathVariable Long citizenId,
            @RequestBody AddComplaintDTO complaintDTO) {
        try {
            ApiResponse response = service.addComplaint(complaintDTO, citizenId, ComplaintType.ELECTRICITY_MANAGEMENT);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Add Road Repair Complaint")
    @PostMapping("/road-repair/{citizenId}")
    public ResponseEntity<ApiResponse> addRoadRepairComplaint(
            @PathVariable Long citizenId,
            @RequestBody AddComplaintDTO complaintDTO) {
        try {
            ApiResponse response = service.addComplaint(complaintDTO, citizenId, ComplaintType.ROAD_REPAIR);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Get Complaint By ID")
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> getComplaint(@PathVariable Long id) {
        try {
            ComplaintDTO complaintDTO = service.getComplaintById(id);
            return ResponseEntity.ok(complaintDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Get Complaints By Status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsByStatus(@PathVariable String status) {
        List<ComplaintDTO> complaints = service.getComplaintsByStatus(status);
        return ResponseEntity.ok(complaints);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Delete Complaint By ID")
    @DeleteMapping("/{complaintId}")
    public ResponseEntity<ApiResponse> deleteComplaint(@PathVariable Long complaintId) {
        try {
            ApiResponse response = service.deleteComplaintById(complaintId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CITIZEN')")
    @Operation(description = "Get All Complaints")
    @GetMapping("/")
    public ResponseEntity<List<ComplainToBeSHownOnFeedDTO>> getAllComplaints() {
        List<ComplainToBeSHownOnFeedDTO> complaints = service.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }
}

