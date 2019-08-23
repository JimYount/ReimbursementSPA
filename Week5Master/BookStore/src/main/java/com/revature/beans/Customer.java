package com.revature.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="id")
public class Customer extends User {
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="address_id")
	private Address address;
	@Column(name="fav_color")
	private String favoriteColor;
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="READING_LIST",
		joinColumns=@JoinColumn(name="cust_id"),
		inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> readingList;
	public Customer() {
		super();
	}
	public Customer(Integer id) {
		super(id);
	}
	public Customer(Integer id, String username, String password) {
		super(id, username, password);
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<Book> getReadingList() {
		return readingList;
	}
	public void setReadingList(Set<Book> readingList) {
		this.readingList = readingList;
	}
	public String getFavoriteColor() {
		return favoriteColor;
	}
	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((favoriteColor == null) ? 0 : favoriteColor.hashCode());
		result = prime * result + ((readingList == null) ? 0 : readingList.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (favoriteColor == null) {
			if (other.favoriteColor != null)
				return false;
		} else if (!favoriteColor.equals(other.favoriteColor))
			return false;
		if (readingList == null) {
			if (other.readingList != null)
				return false;
		} else if (!readingList.equals(other.readingList))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [address=" + address + ", favoriteColor=" + favoriteColor + ", readingList=" + readingList
				+ "]";
	}
}
