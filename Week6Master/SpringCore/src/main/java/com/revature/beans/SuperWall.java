package com.revature.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Steve")
//@Scope(scopeName="prototype")
public class SuperWall implements Wall{
	private String material;
	public SuperWall() {
		super();
	}
	public SuperWall(String material) {
		super();
		this.material = "SUPER"+material;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = "AWESOME"+material;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((material == null) ? 0 : material.hashCode());
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
		SuperWall other = (SuperWall) obj;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SuperWall [material=" + material + "]";
	}
}