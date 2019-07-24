package com.revature.beans;

import java.io.Serializable;
import java.util.Arrays;

import static java.util.Arrays.sort;

public class Bean implements Serializable, Comparable<Bean>  {
	
	public static void main(String[] args) {
		int[] arr = {1,4,3,4};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	private String strain;
	private String color;
	private int yield;
	private float mass;
	private boolean tasty;
	
	public Bean() {
		super();
	}

	public Bean(String strain, String color, int yield, float mass, boolean tasty) {
		super();
		this.strain = strain;
		this.color = color;
		this.yield = yield;
		this.mass = mass;
		this.tasty = tasty;
	}

	public String getStrain() {
		return strain;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getYield() {
		return yield;
	}
	public void setYield(int yield) {
		this.yield = yield;
	}
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	public boolean isTasty() {
		return tasty;
	}
	public void setTasty(boolean tasty) {
		this.tasty = tasty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + Float.floatToIntBits(mass);
		result = prime * result + ((strain == null) ? 0 : strain.hashCode());
		result = prime * result + (tasty ? 1231 : 1237);
		result = prime * result + yield;
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
		Bean other = (Bean) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Float.floatToIntBits(mass) != Float.floatToIntBits(other.mass))
			return false;
		if (strain == null) {
			if (other.strain != null)
				return false;
		} else if (!strain.equals(other.strain))
			return false;
		if (tasty != other.tasty)
			return false;
		if (yield != other.yield)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bean [strain=" + strain + ", color=" + color + ", yield=" + yield + ", mass=" + mass + ", tasty="
				+ tasty + "]";
	}

	@Override
	public int compareTo(Bean b) {
		if((this.strain!=null)&&!this.strain.equals(b.strain))
			return this.strain.compareTo(b.strain);
		if((this.color!=null)&&!this.color.equals(b.color))
			return this.color.compareTo(b.color);
		if((this.yield!= b.yield))
			return this.yield - b.yield;
		if((this.mass!=b.mass))
			return (this.mass > b.mass)? 1: -1; 
		return (new Boolean(this.tasty)).compareTo(b.isTasty());
	}
}
