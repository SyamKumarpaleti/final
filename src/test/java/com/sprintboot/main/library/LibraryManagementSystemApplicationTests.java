package com.sprintboot.main.library;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.main.library.exception.InvalidIdException;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.repository.CustomerRepository;
import com.springboot.main.library.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class LibraryManagementSystemApplicationTests {
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private CustomerService customerService;
	@Test
	public void testPostCustomer() {
		Customer customer = new Customer();
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.postCustomer(customer);
		assertNotNull(result);
		assertEquals(customer, result);
	}
	@Test
	public void testGetOneValidId() throws InvalidIdException {
		int customerId = 1;
		Customer customer = new Customer();
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		Customer result = customerService.getOne(customerId);
		assertNotNull(result);
		assertEquals(customer, result);
	}
	@Test
	public void testGetOneInvalidId() {
		int customerId = 1;
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
		assertThrows(InvalidIdException.class, () -> customerService.getOne(customerId));
	}
	@Test
	public void testDeleteCustomer() {
		Customer customer = new Customer();
		customerService.deleteCustomer(customer);
		verify(customerRepository, times(1)).delete(customer);
	}
	@Test
	public void testGetCustomer() {
		int customerId = 1;
		Customer customer = new Customer();
		when(customerRepository.getCustomer(customerId)).thenReturn(customer);
		Customer result = customerService.getCustomer(customerId);
		assertNotNull(result);
		assertEquals(customer, result);
	}
}
