package com.springboot.main.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.library.exception.InvalidIdException;

import com.springboot.main.library.model.Book;
import com.springboot.main.library.model.Category;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.repository.BookRepository;
@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public Book postBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	public List<Book> getAllBook(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookRepository.findAll(pageable).getContent();
	}

	public List<Book> getbookByAdmin(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findByAdminId(id);
	}

	public Book getOne(int bid) throws InvalidIdException{
		Optional<Book> optional =  bookRepository.findById(bid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Book ID Invalid");
		}
		return optional.get();
	}

	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		bookRepository.delete(book);
		
	}

	public Book getBookTitle(String bookTitle) {
		// TODO Auto-generated method stub
		return bookRepository.findBookTitle(bookTitle);
	}

	public Book getAuthor(String author) {
		// TODO Auto-generated method stub
		return bookRepository.findAuthor(author);
	}

	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findBook(id);
	}
	
	

	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
		
	}

	public Book getBook(String isbn) {
		
		return bookRepository.getByIsbn(isbn);
	}

	/*@SuppressWarnings("unchecked")
	public List<Book> getbooks(String name) throws InvalidIdException {
		
	
			Optional<?> optional =	bookRepository.getByauthor(name);
			if(!optional.isPresent())
				throw new InvalidIdException("no books avaliable");
			
			return  (List<Book>) optional.get();
	}
	*/
	
	public List<Book> getBooksByCategory(int id) {
        return bookRepository.findByCategory(id);
    }

	
	public List<Book> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return bookRepository.findByAuthor(author);
	}

	public List<Book> getAllCategory() {
		// TODO Auto-generated method stub
		return bookRepository.findAllCategory();
	}

	public List<Book> findwithauthdesc(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findwithauthdesc(id);
	}

	public List<Book> findwithbookdesc(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findwithbookdesc(id);
	}

	public void deleteBookById(int bid) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(bid);
		
	}

	public Book postBookById(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	
	

	
	/*public Book getStatus(String status) {
		
		return bookRepository.getStatus(status);
	}*/

	public List<Book> getbookByStatus(int cbid) {
		// TODO Auto-generated method stub
		return bookRepository.getbookByStatus(cbid);
	}

	
	

	

	
}
