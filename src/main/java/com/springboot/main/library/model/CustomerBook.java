package com.springboot.main.library.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class CustomerBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	
	private LocalDate issueDate;
	private LocalDate returnDate;
	private double amount;
	
	
	@OneToOne
	private Book book;
	@OneToOne
	private Customer customer;
	
    
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "CustomerBook [id=" + id + ", customer=" + customer + ", book=" + book + ", issueDate=" + issueDate
				+ ", returnDate=" + returnDate + ", amount=" + amount + "]";
	}
	
	
	
}