package com.springboot.main.library.controller;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.main.library.dto.BookDto;
import com.springboot.main.library.exception.InvalidIdException;
import com.springboot.main.library.model.Admin;
import com.springboot.main.library.model.Book;
import com.springboot.main.library.model.Category;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.service.AdminService;
import com.springboot.main.library.service.BookService;
import com.springboot.main.library.service.CategoryService;




@RestController
@RequestMapping("/Book")
@CrossOrigin(origins=("http://localhost:3000"))
public class BookController {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/add/{id}")
	public ResponseEntity<?> postBook(@RequestBody BookDto bookdto,
						    @PathVariable("id") int id) {
			/* Fetch vendor object from db using id */
		try {
			
		
			Admin admin = adminService.getOne(id);
			/* Attach vendor to product */
//			Category category =categoryService.getbyName(bookdto.getCategory()) ;
			Book book =new Book();
			
			book.setAdmin(admin);
//			book.setCategory(category);
			book.setAuthor(bookdto.getAuthor());
			book.setAuthorDesc(bookdto.getAuthor());
			book.setBookDesc(bookdto.getBookDesc());
			book.setBookPrice(bookdto.getBookPrice());
			book.setBookTitle(bookdto.getBookTitle());
			book.setRating(bookdto.getRating());
			book.setIsbn(bookdto.getIsbn());
			book.setNoOfCopies(bookdto.getNoOfCopies());
			
			
			
			/* Save the product in the DB */
			book = bookService.postBook(book);
			 logger.info("successfully posted");
			return ResponseEntity.ok().body(book);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
//	@PostMapping("/add/{catid}/{id}")
//	public ResponseEntity<?> postBook(@RequestBody Book book,@PathVariable("catid") int catid, 
//						    @PathVariable("id") int id) {
//			/* Fetch vendor object from db using id */
//		try {
//			Category category=categoryService.getOne(catid);
//			book.setCategory(category);
//			
//			Admin admin = adminService.getOne(id);
//			/* Attach vendor to product */
//			book.setAdmin(admin);
//			/* Save the product in the DB */
//			book = bookService.postBook(book);
//			return ResponseEntity.ok().body(book);
//		} catch (InvalidIdException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}

	@GetMapping("/all")
	public List<Book> getAllBook(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {  
		
		Pageable pageable =  PageRequest.of(page, size);
		return bookService.getAllBook(pageable);
	}
	@GetMapping("/getbybook/{bid}")
	public ResponseEntity<?> getBookByBookid(@PathVariable("bid") int bid) {
	    try {
	        
	        Book book = bookService.getOne(bid);
	        return ResponseEntity.ok().body(book);
	    } catch (NumberFormatException | InvalidIdException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@GetMapping("/getwithauthdesc")
		public List<Book> getwithauthdesc(@RequestParam int id){
		
		return bookService.findwithauthdesc(id);
	}
	@GetMapping("/getwithbookdesc")
	public List<Book> getwithbookdesc(@RequestParam int id){
	
	return bookService.findwithbookdesc(id);
}

	/*fetch book by Admin id*/
	@GetMapping("/all/{id}")
	public ResponseEntity<?> getbookByAdmin(@PathVariable("id") int id) {
		try {
			
			Admin admin = adminService.getOne(id);
			List<Book> list= bookService.getbookByAdmin(id);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	/*Delete book by admin*/
	@DeleteMapping("/delete/{bid}/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") int id,@PathVariable("bid") int bid) {
		
		try {
			//validate id
			Admin admin = adminService.getOne(id);
			Book book=bookService.getOne(bid);
			//delete
			bookService.deleteBook(book);
			return ResponseEntity.ok().body("Book Deleted Successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<?> deleteBook(@PathVariable("bid") int bid) throws InvalidIdException {
		// validate id
		Book book = bookService.getOne(bid);
		// delete
		bookService.deleteBookById(bid);
		return ResponseEntity.ok().body("Book deleted successfully");
	}
	@PutMapping("/update/{bid}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateBook(@PathVariable("bid") int bid,
							@RequestBody BookDto newBook) {
		try {
			//validate id
			
			Book oldBook=bookService.getOne(bid);
			if(newBook.getBookTitle() != null)
				oldBook.setBookTitle(newBook.getBookTitle());
			if(newBook.getAuthor() != null)
				oldBook.setAuthor(newBook.getAuthor());
			if(newBook.getCategory() != null)
				oldBook.setCategory(newBook.getCategory());
			if(newBook.getRating()!=0)
				oldBook.setRating(newBook.getRating());
			if(newBook.getNoOfCopies()!=null)
				oldBook.setNoOfCopies(newBook.getNoOfCopies());
			if(newBook.getBookDesc()!=null)
				oldBook.setBookDesc(newBook.getBookDesc());
			if(newBook.getAuthorDesc()!=null)
				oldBook.setAuthorDesc(newBook.getAuthorDesc());
			if(newBook.getIsbn() != null)
				oldBook.setIsbn(newBook.getIsbn());
			if(newBook.getBookPrice() != 0)
				oldBook.setBookPrice(newBook.getBookPrice());
			oldBook = bookService.postBook(oldBook); 
			 logger.info("updated status of bookTitle"+oldBook.getBookTitle()+"to"+newBook.getBookTitle());
			return ResponseEntity.ok().body(oldBook);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
		
}
	
	
	
}