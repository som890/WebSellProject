package com.web.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ColorWatch")
public class ColorWatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String colorname;
	
	private String colorCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "watch_id")
	private Watch watch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}

	public Watch getWatch() {
		return watch;
	}

	public void setWatch(Watch watch) {
		this.watch = watch;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorWatch other = (ColorWatch) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ColorWatch [id=" + id + ", colorname=" + colorname + ", colorCode=" + colorCode + "]";
	}
	
	
}
