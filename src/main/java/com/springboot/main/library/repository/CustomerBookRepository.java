package com.springboot.main.library.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.library.dto.CustomerBookDto;
import com.springboot.main.library.model.CustomerBook;

public interface CustomerBookRepository extends JpaRepository<CustomerBook, Integer>{
    
	
	@Query("select cb from CustomerBook cb where cb.book.id=?1")
	List<?> getcustomers(int bid);

	@Query("select cb from CustomerBook cb where cb.customer.id=?1")
	List<?> getbooks(int cid);

	
	@Query("select cb from CustomerBook cb where cb.book.id=?1")
	List<CustomerBook> findCustomers(int id);

	
	
}
