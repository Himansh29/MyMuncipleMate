package com.app.mmm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.app.mmm.dto.ComplaintDTO;
import com.app.mmm.entity.Complaint;
import com.app.mmm.enums.Status;

@EnableJpaRepositories
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	@Query("SELECT c FROM Complaint c LEFT JOIN FETCH c.citizen LEFT JOIN FETCH c.feedbacks LEFT JOIN FETCH c.team WHERE c.status = :status")
    List<Complaint> findByStatus(@Param("status") Status status);
}
