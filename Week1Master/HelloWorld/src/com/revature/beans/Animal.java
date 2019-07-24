package com.revature.beans;

public abstract class Animal implements Breathable {
	public String name;
	private Boolean adorable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Boolean isAdorable() {
		return adorable;
	}
	public void setAdorable(Boolean adorable) {
		this.adorable = adorable;
	}
	public void speak() {
		System.out.println("Hello, I am "+name);
	}
	@Override
	public String toString() {
		return "Animal [name=" + name + ", adorable=" + adorable + "]";
	}
	public abstract void walk();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adorable == null) ? 0 : adorable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Animal other = (Animal) obj;
		if (adorable == null) {
			if (other.adorable != null)
				return false;
		} else if (!adorable.equals(other.adorable))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
