package com.revature.beans;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="purchase_book")
@JsonIgnoreProperties(value= {"id", "purchase"})
public class InvoiceLine {
	/* If you need any more reasons to not use composite keys, here you go
	 * Guess what? To create composite keys in an OOP context, you need
	 * to create an object representation of that data. This is called an
	 * Embeddable in JPA. So we have to tell Hibernate we have an EmbeddedId
	 * and give it a class.
	 */
	@EmbeddedId
	private InvoiceLineId id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="purchase_id", insertable=false, updatable=false)
	private Purchase purchase;
	/*
	 * Because we are currently READING THE SAME COLUMN TWICE
	 * Because I need to not COMPLETELY BREAK MY FRONT END
	 * We have to specify our repeated columns should not be insertable or updatable
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="book_id", insertable=false, updatable=false)
	private Book book;
	private Integer quantity;
	public InvoiceLine() {
		super();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceLine other = (InvoiceLine) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InvoiceLine [book=" + book + ", quantity=" + quantity + "]";
	}
}
