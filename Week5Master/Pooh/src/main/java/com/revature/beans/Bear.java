package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Bear {
	@Id
	@Column(name="bear_id")
	@SequenceGenerator(name="stevebob", sequenceName="bearid_seq", allocationSize=1)
	@GeneratedValue(generator="stevebob", strategy=GenerationType.SEQUENCE)
	private Integer bearId;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="cave_id")
	private Cave cave;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="honeypot_id") //which column holds the foreign key to the HoneyPot table
	private HoneyPot honeyPot;
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="parent_cub",						// the table that holds the relationship
			joinColumns=@JoinColumn(name="parent_id"),	// linked to the entity we are in
			inverseJoinColumns=@JoinColumn(name="cub_id")) // linked to the entity we are retrieving
	private Set<Bear> bearCubs = new HashSet<Bear>();
	@Column(name="bear_color")
	private String bearColor;
	private String breed;
	@Column(name="bear_name")
	private String name;
	private Double weight;
	private Double height;
	public Bear() {
		super();
	}
	public Bear(Integer bearId, Cave cave, HoneyPot honeyPot, Set<Bear> bearCubs, String bearColor, String breed,
			String name, Double weight, Double height) {
		super();
		this.bearId = bearId;
		this.cave = cave;
		this.honeyPot = honeyPot;
		this.bearCubs = bearCubs;
		this.bearColor = bearColor;
		this.breed = breed;
		this.name = name;
		this.weight = weight;
		this.height = height;
	}
	public Integer getBearId() {
		return bearId;
	}
	public void setBearId(Integer bearId) {
		this.bearId = bearId;
	}
	public Cave getCave() {
		return cave;
	}
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	public HoneyPot getHoneyPot() {
		return honeyPot;
	}
	public void setHoneyPot(HoneyPot honeyPot) {
		this.honeyPot = honeyPot;
	}
	public Set<Bear> getBearCubs() {
		return bearCubs;
	}
	public void setBearCubs(Set<Bear> bearCubs) {
		this.bearCubs = bearCubs;
	}
	public String getBearColor() {
		return bearColor;
	}
	public void setBearColor(String bearColor) {
		this.bearColor = bearColor;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bearColor == null) ? 0 : bearColor.hashCode());
		result = prime * result + ((bearCubs == null) ? 0 : bearCubs.hashCode());
		result = prime * result + ((bearId == null) ? 0 : bearId.hashCode());
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((cave == null) ? 0 : cave.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((honeyPot == null) ? 0 : honeyPot.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Bear other = (Bear) obj;
		if (bearColor == null) {
			if (other.bearColor != null)
				return false;
		} else if (!bearColor.equals(other.bearColor))
			return false;
		if (bearCubs == null) {
			if (other.bearCubs != null)
				return false;
		} else if (!bearCubs.equals(other.bearCubs))
			return false;
		if (bearId == null) {
			if (other.bearId != null)
				return false;
		} else if (!bearId.equals(other.bearId))
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (cave == null) {
			if (other.cave != null)
				return false;
		} else if (!cave.equals(other.cave))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (honeyPot == null) {
			if (other.honeyPot != null)
				return false;
		} else if (!honeyPot.equals(other.honeyPot))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bear [bearId=" + bearId + ", cave=" + cave + ", honeyPot=" + honeyPot + ", bearCubs=" + bearCubs
				+ ", bearColor=" + bearColor + ", breed=" + breed + ", name=" + name + ", weight=" + weight
				+ ", height=" + height + "]";
	}
}
