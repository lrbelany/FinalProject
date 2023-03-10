package com.skilldistillery.bilingualbuddies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.bilingualbuddies.entities.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
	List<Alert> findAlertByReceiverId(int Id);
	//Alert findAlertByReceiverId(User receiver);
	
}
