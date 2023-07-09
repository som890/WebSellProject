package com.web.model;

public class ColorDto {

	private String mamau;

	private String tenmau;
	
	private String selected;

	public ColorDto() {
	}

	public ColorDto(String mamau, String tenmau) {
		this.mamau = mamau;
		this.tenmau = tenmau;
	}

	public String getMamau() {
		return mamau;
	}

	public void setMamau(String mamau) {
		this.mamau = mamau;
	}

	public String getTenmau() {
		return tenmau;
	}

	public void setTenmau(String tenmau) {
		this.tenmau = tenmau;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

}
