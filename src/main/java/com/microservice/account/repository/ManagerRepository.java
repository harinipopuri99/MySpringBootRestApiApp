package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

	@Query("select m from Manager m where m.name LIKE %?1%")
	List<Manager> searchManagerOnName(String searchStr);

}
