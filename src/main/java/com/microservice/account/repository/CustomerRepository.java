package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByRegionId(int regionId);

	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from customer c JOIN region r on c.region_id = r.id JOIN country con on r.country_id = con.id where con.id=?1"
	 * )
	 * 
	 */

	@Query("select c from Customer c " 
	+ "JOIN c.region r " 
	+ "JOIN r.country con " 
	+ "where con.id=?1")

	List<Customer> getCustomerByCountryJpql(int countryId);
}