package com.springboot.main.library.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.library.dto.BookDto;
import com.springboot.main.library.dto.CustomerBookDto;
import com.springboot.main.library.exception.InvalidIdException;

import com.springboot.main.library.model.Book;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.model.CustomerBook;
import com.springboot.main.library.service.BookService;
import com.springboot.main.library.service.CustomerBookService;
import com.springboot.main.library.service.CustomerService;

@RestController
@RequestMapping("/customerBook")
@CrossOrigin(origins=("http://localhost:3000"))
public class CustomerBookController {
	@Autowired
	private Logger logger;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CustomerBookService customerBookService;
	
	
	
	@PostMapping("/{id}/{bid}")
	//@PostMapping("/{customerId}/{bookId}")
    public ResponseEntity<?> createCustomerBook(@PathVariable("id") int id,@PathVariable("bid") int bid, @RequestBody CustomerBook customerBook)  {
     
		
		try {
			Customer customer = customerService.getOne(id);
			Book book=bookService.getOne(bid);
			customerBook. setCustomer(customer) ;
			customerBook. setBook(book);
			
			LocalDate issueDate=customerBook.getIssueDate();
			LocalDate returnDate=customerBook.getReturnDate();
			double amount=customerBook.getBook().getBookPrice();
			double amount1=0;
			long days = ChronoUnit.DAYS.between(issueDate, returnDate);
			if(days<=7) {
	          amount1 +=(amount* 0.2);
			}
			else if(days>7 && days<=14){
				 amount1+=(amount*0.4);
			}
			else if(days>14 && days<=21){
				 amount1+=(amount*0.6);
			}
			else {
				 amount1+=(amount*0.8);
			}
	
			customerBook. setAmount(amount1);
			
			 customerBook=customerBookService.createCustomerBook(customerBook);
			 logger.info("successfully posted");
			 return ResponseEntity.ok().body(customerBook);
			
		} catch (InvalidIdException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		}
		
		/* localhost:8182/customerBook/30/7
		 * 
		 *  {
  "issueDate": "2023-10-04",
  "returnDate": "2023-10-18"
}*/
	@GetMapping("/bookid/{bid}")
	public ResponseEntity<?> getcustomers(@PathVariable("bid") int bid ) {
		try {
			Book book = bookService.getOne(bid);
			
		return ResponseEntity.ok().body(customerBookService.getcustomers(bid));
			
		}
		catch (InvalidIdException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	/*       localhost:8182/customerBook/customerid/37     */
	@GetMapping("/customerid/{cid}")
	public ResponseEntity<?> getbooks(@PathVariable("cid") int cid ) {
		try {
			Customer customer = customerService.getOne(cid);
			
		return ResponseEntity.ok().body(customerBookService.getbooks(cid));
			
		}
		catch (InvalidIdException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}

	}	

	
	
	/*
	@GetMapping("/getstatus/{status}")
	public Book getStatus(@PathVariable("status") String status) {
	Book book=bookService.getStatus(status);
	return book;
	}
	*/
	
	
	
	@GetMapping("/all/{cbid}")
	public ResponseEntity<?> getbookByStatus(@PathVariable("cbid") int cbid) {
		try {
			Customer customer = customerService.getOne(cbid);
			List<Book> list= bookService.getbookByStatus(cbid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	@GetMapping("/getall") /// customer/getall?page=0&size=10
	public List<CustomerBook> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
		// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return customerBookService.getAll(pageable);
	}
	
	
	
	
	
	
	
//	@PostMapping("/{id}/{bid}")
//	//@PostMapping("/{customerId}/{bookId}")
//    public ResponseEntity<?> createCustomerBook(@PathVariable("id") int id,@PathVariable("bid") int bid, @RequestBody CustomerBook customerBook)  {
//     
//		
//		try {
//			Customer customer = customerService.getOne(id);
//			Book book=bookService.getOne(bid);
//			customerBook. setCustomer(customer) ;
//			customerBook. setBook(book);
//			
//			LocalDate issueDate=customerBook.getIssueDate();
//			LocalDate returnDate=customerBook.getReturnDate();
//			double amount=customerBook.getBook().getBookPrice();
//			double amount1=0;
//			long days = ChronoUnit.DAYS.between(issueDate, returnDate);
//			if(days<=7) {
//	          amount1 +=(amount* 0.2);
//			}
//			else if(days>7 && days<=14){
//				 amount1+=(amount*0.4);
//			}
//			else if(days>14 && days<=21){
//				 amount1+=(amount*0.6);
//			}
//			else {
//				 amount1+=(amount*0.8);
//			}
//	
//			customerBook. setAmount(amount1);
//			
//			 customerBook=customerBookService.createCustomerBook(customerBook);
//			 return ResponseEntity.ok().body(customerBook);
//			
//		} catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//		
//		}
	
	
	

	   

	@PostMapping("/create/{customerId}")
	public ResponseEntity<?> createCustomerBooks(@PathVariable("customerId") int customerId, @RequestBody CustomerBookDto customerBookDto) {
	    try {
	        List<Integer> books = customerBookDto.getBooks();
	       
	        for (Integer bookId : books) {
	        	 CustomerBook customerBook=new CustomerBook();

	            customerBook.setIssueDate(customerBookDto.getIssueDate());
	            customerBook.setReturnDate(customerBookDto.getReturnDate());

	            Customer customer = customerService.getOne(customerId);
	            Book dbBook = bookService.getBook(bookId);

	            customerBook.setCustomer(customer);
	            System.out.println(customer);
	            customerBook.setBook(dbBook);

	            double amount = dbBook.getBookPrice();
	            double amount1 = 0;
	            long days = ChronoUnit.DAYS.between(customerBookDto.getIssueDate(), customerBookDto.getReturnDate());
	            if (days <= 7) {
	                amount1 += (amount * 0.2);
	            } else if (days > 7 && days <= 14) {
	                amount1 += (amount * 0.4);
	            } else if (days > 14 && days <= 21) {
	                amount1 += (amount * 0.6);
	            } else {
	                amount1 += (amount * 0.8);
	            }
	            customerBook.setAmount(amount1);
	           	            System.out.println(customerBook.getAmount());
	            customerBookService.createCustomerBook(customerBook);
	        }
	        
	        logger.info("successfully posted");
	        return ResponseEntity.ok().body(customerBookDto);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        System.out.println(e);
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	}
	
	    

	    // Implement your methods to get Customer and Book entities from the database
	
	
	
}

