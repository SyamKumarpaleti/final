package com.springboot.main.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.library.exception.InvalidIdException;

import com.springboot.main.library.model.Customer;

import com.springboot.main.library.repository.CustomerRepository;
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
	public Customer postCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public Customer getOne(int id) throws InvalidIdException {
		Optional<Customer> optional =  customerRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("customer ID Invalid");
		}
		return optional.get();
	}

	public List<Customer> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(pageable).getContent();
	}

	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
		// TODO Auto-generated method stub
		
	}

	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return customerRepository.getCustomer(id);
	}

	

	

	
	
}