package restaurant.model;

import java.io.Serializable;

public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pkRegion;
	private String name;
	private int fkRegion;
	
	public Region() {}

	public Region(int pkRegion, String name, int fkRegion) {
		this.pkRegion = pkRegion;
		this.name = name;
		this.fkRegion = fkRegion;
	}

	public int getPkRegion() {
		return pkRegion;
	}

	public void setPkRegion(int pkRegion) {
		this.pkRegion = pkRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFkRegion() {
		return fkRegion;
	}

	public void setFkRegion(int fkRegion) {
		this.fkRegion = fkRegion;
	}

	@Override
	public String toString() {
		return "Region [pkRegion=" + pkRegion + ", name=" + name +
			", fkRegion=" + fkRegion + "]";
	}
	
	
}
