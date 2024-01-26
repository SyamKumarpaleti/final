package com.springboot.main.library.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerBookDto {
	
	private LocalDate issueDate;
	private LocalDate returnDate;
	private Double amount;
	private List<Integer> books;

	public List<Integer> getBooks() {
		return books;
	}

	public void setBooks(List<Integer> books) {
		this.books = books;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CustomerBookDto [issueDate=" + issueDate + ", returnDate=" + returnDate + ", amount=" + amount
				+ ", books=" + books + "]";
	}
}
