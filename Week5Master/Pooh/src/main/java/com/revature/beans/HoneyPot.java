package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="getAllHoneypots", query="FROM HoneyPot"),
	@NamedQuery(name="getAllHoneypotsWithHoney", query="from HoneyPot where honeyAmount > :amount")
})

@Entity // Tells Hibernate that this class is a "managed entity" and should be mapped to the database
@Table(name="honey_pot") // Tells Hibernate what table should be mapped to the class
public class HoneyPot {
	@Id // Tells Hibernate that this field is the primary key of the table/object relationship
	@Column(name="honeypot_id") // maps the field to a column
	@SequenceGenerator(name="gen", sequenceName="honeypotid_seq", allocationSize=1)
	@GeneratedValue(generator="gen", strategy=GenerationType.SEQUENCE)
	private Integer honeypotId;
	@Column // @Column defaults to the name of the field
	private Double volume;
	// All fields are inherently annotated with @Column.
	private Double honeyAmount;
	
	public HoneyPot() {
		super();
	}
	public HoneyPot(Integer honeypotId, Double volume, Double honeyAmount) {
		super();
		this.honeypotId = honeypotId;
		this.volume = volume;
		this.honeyAmount = honeyAmount;
	}
	
	public Integer getHoneypotId() {
		return honeypotId;
	}
	public void setHoneypotId(Integer honeypotId) {
		this.honeypotId = honeypotId;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getHoneyAmount() {
		return honeyAmount;
	}
	public void setHoneyAmount(Double honeyAmount) {
		this.honeyAmount = honeyAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((honeyAmount == null) ? 0 : honeyAmount.hashCode());
		result = prime * result + ((honeypotId == null) ? 0 : honeypotId.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		HoneyPot other = (HoneyPot) obj;
		if (honeyAmount == null) {
			if (other.honeyAmount != null)
				return false;
		} else if (!honeyAmount.equals(other.honeyAmount))
			return false;
		if (honeypotId == null) {
			if (other.honeypotId != null)
				return false;
		} else if (!honeypotId.equals(other.honeypotId))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HoneyPot [honeypotId=" + honeypotId + ", volume=" + volume + ", honeyAmount=" + honeyAmount + "]";
	}
}
