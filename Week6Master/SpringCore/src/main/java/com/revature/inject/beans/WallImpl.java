package com.revature.inject.beans;

import javax.inject.Named;

@Named("injectWall")
public class WallImpl implements Wall{
	private String material;
	public WallImpl() {
		super();
		System.out.println("WallImpl()");
	}
	public WallImpl(String material) {
		super();
		this.material = material;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
		WallImpl other = (WallImpl) obj;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WallImpl [material=" + material + "]";
	}
}
