package com.springboot.main.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.library.model.Book;
import com.springboot.main.library.model.Customer;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select b from Book b where b.id=?1")
	List<Book> getBookByAdminJpql(int id);

	List<Book> findByAdminId(int id);
	
	@Query(value = "select * from book where book_title=?1",nativeQuery = true)
	Book findBookTitle(String bookTitle);
	@Query(value = "select * from book where author=?1",nativeQuery = true)
	Book findAuthor(String author);
	@Query(value = "select * from book where id=?1",nativeQuery = true)
	Book findBook(int id);
    
	@Query("select b from Book b where b.isbn=?1")
	Book getByIsbn(String isbn);
    
	/*@Query("select b from Book b where b.author=?1 ")
	Optional<?> getByauthor(String name);
	*/
	@Query("SELECT b FROM Book b WHERE b.category.id = ?1")
    List<Book> findByCategory(int id);
	
	
	
	@Query("select b from Book b where b.author=?1")
	List<Book> findByAuthor(String author);
	@Query("select b from Book b")
	List<Book> findAllCategory();

	@Query("select b from Book b where b.id=?1")
	List<Book> findwithauthdesc(int id);

	@Query("select b from Book b where b.id=?1")
	List<Book> findwithbookdesc(int id);

	@Query("select b from Book b where b.id=?1")
	List<Book> findbybook(int id);

	@Query("select b from Book b where b.id=?1")
	List<Book> getbookByStatus(Object status);

	
	
//	@Query("select b from Book b where b.id=?1")
//	void deleteByid(Book book);

	
	
	
	


	

	
	/*
	@Query("select b from Book b where b.status=borrowed")
	Book getStatus(String status);
	*/
	
	
	/*
	 @Query("select b from Book b where b.status=borrowed")
	
	List<Book> getbookByStatus(Object status);
	 */
	
   /* @Query(value = "select * from book where book_title=?1",nativeQuery = true)
	Customer findBookTitle(String bookTitle);*/
	
   
	
	
    

	

}
