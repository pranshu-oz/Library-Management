package pranshu.library.management.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	public Book() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String author;
	
	@NotNull
	private String title;
	
	@NotNull
	@DecimalMin(value="1001", message="amount must be greater than 1000")
	private long isbn;
	
	@NotNull
	private long availableCopy;
	
	@NotNull
	@Column(columnDefinition="INT DEFAULT 18")
	private long totalCopy;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate createdAt;

	@ManyToOne
	private BookCategory bookCategory;
	
	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public long getAvailableCopy() {
		return availableCopy;
	}

	public void setAvailableCopy(long availableCopy) {
		this.availableCopy = availableCopy;
	}

	public long getTotalCopy() {
		return totalCopy;
	}

	public void setTotalCopy(long totalCopy) {
		this.totalCopy = totalCopy;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
