package com.microservice.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.account.model.Customer;
import com.microservice.account.repository.CustomerRepository;



@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	public Customer addCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	public List<Customer> getCustomerByRegion(int regionId) {
		// TODO Auto-generated method stub
		return customerRepository.findByRegionId(regionId);
	}

	public List<Customer> getCustomerByCountryJpql(int countryId) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerByCountryJpql(countryId);
	}
	
	

}