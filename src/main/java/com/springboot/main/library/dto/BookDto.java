package com.springboot.main.library.dto;

import com.springboot.main.library.model.Category;

public class BookDto {
	private String bookTitle;
	private String author;
	private float rating;
	
	private String noOfCopies;
	private String isbn;
    private String bookDesc;
    private String authorDesc;

	private double bookPrice;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	

	public String getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(String noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "BookDto [bookTitle=" + bookTitle + ", author=" + author + ", rating=" + rating + 
				"noOfCopies=" + noOfCopies + ", isbn=" + isbn + ", bookDesc=" + bookDesc
				+ ", authorDesc=" + authorDesc + ", bookPrice=" + bookPrice + "]";
	}

	public Category getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	


	

}