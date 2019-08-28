package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Scope(scopeName="prototype")
public class House {
	private String name;
	@Autowired
	@Qualifier("Steve")
	private Wall wall;
	private double numWalls;
	public House() {
		super();
		System.out.println("House() called.");
	}
	//@Autowired
	public House(Wall wall) {
		super();
		System.out.println("House(Wall) called.");
		this.wall = wall;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Wall getWall() {
		return wall;
	}
	//@Autowired
	public void setWall(Wall wall) {
		System.out.println("setWall(Wall) called.");
		this.wall = wall;
	}
	public double getNumWalls() {
		return numWalls;
	}
	public void setNumWalls(double numWalls) {
		this.numWalls = numWalls;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(numWalls);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((wall == null) ? 0 : wall.hashCode());
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
		House other = (House) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(numWalls) != Double.doubleToLongBits(other.numWalls))
			return false;
		if (wall == null) {
			if (other.wall != null)
				return false;
		} else if (!wall.equals(other.wall))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "House [name=" + name + ", wall=" + wall + ", numWalls=" + numWalls + "]";
	}
}
