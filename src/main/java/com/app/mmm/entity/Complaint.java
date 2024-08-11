package com.app.mmm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.app.mmm.enums.ComplaintType;
import com.app.mmm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complaint extends BaseEntity {

	@Enumerated(EnumType.STRING)
    private ComplaintType complaintType;

    @Column(length = 200, nullable = false)
    @NotBlank
    private String complaintDescription;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    private String imagePath;
    
    @Column(nullable = false)
    private int likes;
    
    @NotBlank
    @Column(nullable = false)
    private String location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();
	
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
	// Helper methods
	public void addFeedback(Feedback feedback) {
		feedbacks.add(feedback);
		feedback.setComplaint(this);
	}
	
	public void removeFeedback(Feedback feedback) {
		feedbacks.remove(feedback);
		feedback.setComplaint(null);
	}
	
    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

	public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(citizen, complaintDescription, complaintType, feedbacks, status, team);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		return Objects.equals(citizen, other.citizen)
				&& Objects.equals(complaintDescription, other.complaintDescription)
				&& complaintType == other.complaintType && Objects.equals(feedbacks, other.feedbacks)
				&& status == other.status && team == other.team;
	}
	
	
}
