package com.app.mmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mmm.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
