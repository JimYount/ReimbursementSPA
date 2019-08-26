package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cave_id")
public class Cave {
	@Id
	@Column(name="cave_id_id")
	@SequenceGenerator(name="bob", sequenceName="caveid_seq", allocationSize=1)
	@GeneratedValue(generator="bob", strategy=GenerationType.SEQUENCE)
	private Integer caveId;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cave")
	private Set<Bear> residents = new HashSet<Bear>();
	@Column(name="sq_footage")
	private Double squareFootage;
	private String caveType;
	@Column(name="cave_name")
	private String caveName;
	public Cave() {
		super();
	}
	public Cave(Integer caveId, Set<Bear> residents, Double squareFootage, String caveType, String caveName) {
		super();
		this.caveId = caveId;
		this.residents = residents;
		this.squareFootage = squareFootage;
		this.caveType = caveType;
		this.caveName = caveName;
	}
	public Integer getCaveId() {
		return caveId;
	}
	public void setCaveId(Integer caveId) {
		this.caveId = caveId;
	}
	public Set<Bear> getResidents() {
		return residents;
	}
	public void setResidents(Set<Bear> residents) {
		this.residents = residents;
	}
	public Double getSquareFootage() {
		return squareFootage;
	}
	public void setSquareFootage(Double squareFootage) {
		this.squareFootage = squareFootage;
	}
	public String getCaveType() {
		return caveType;
	}
	public void setCaveType(String caveType) {
		this.caveType = caveType;
	}
	public String getCaveName() {
		return caveName;
	}
	public void setCaveName(String caveName) {
		this.caveName = caveName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caveId == null) ? 0 : caveId.hashCode());
		result = prime * result + ((caveName == null) ? 0 : caveName.hashCode());
		result = prime * result + ((caveType == null) ? 0 : caveType.hashCode());
		result = prime * result + ((squareFootage == null) ? 0 : squareFootage.hashCode());
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
		Cave other = (Cave) obj;
		if (caveId == null) {
			if (other.caveId != null)
				return false;
		} else if (!caveId.equals(other.caveId))
			return false;
		if (caveName == null) {
			if (other.caveName != null)
				return false;
		} else if (!caveName.equals(other.caveName))
			return false;
		if (caveType == null) {
			if (other.caveType != null)
				return false;
		} else if (!caveType.equals(other.caveType))
			return false;
		if (squareFootage == null) {
			if (other.squareFootage != null)
				return false;
		} else if (!squareFootage.equals(other.squareFootage))
			return false;
		return true;
	}
	// If I'm printing my residents... and my residents are bears... and my bears have caves... and my caves have residents...
	@Override
	public String toString() {
		return "Cave [caveId=" + caveId + ", squareFootage=" + squareFootage
				+ ", caveType=" + caveType + ", caveName=" + caveName + "]";
	}
}
