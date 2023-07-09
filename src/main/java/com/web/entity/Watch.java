package com.web.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "watch")
public class Watch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Tên sản phẩm không được trống")
	private String name;

//	@NotNull(message="giá sản phẩm không được trống")
	private Double price;

	@NotNull(message = "số lượng sản phẩm không được trống")
	@Min(value = 1, message = "số lượng sản phẩm không được nhỏ hơn 1")
	private Integer quantity;

	private String banner;

	private String listColor;

	private String description;

	@Transient
	private MultipartFile anhphu[];

	@Transient
	private MultipartFile anhnen = null;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "trademark_id")
	private TradeMark tradeMark;

	@OneToMany(mappedBy = "watch", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<ColorWatch> colorWatchs = new HashSet<>();

	@OneToMany(mappedBy = "watch", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<ImageWatch> imageWatch = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getListColor() {
		return listColor;
	}

	public void setListColor(String listColor) {
		this.listColor = listColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public TradeMark getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(TradeMark tradeMark) {
		this.tradeMark = tradeMark;
	}

	public Set<ColorWatch> getColorWatchs() {
		return colorWatchs;
	}

	public void setColorWatchs(Set<ColorWatch> colorWatchs) {
		this.colorWatchs = colorWatchs;
	}

	public Set<ImageWatch> getImageWatch() {
		return imageWatch;
	}

	public void setImageWatch(Set<ImageWatch> imageWatch) {
		this.imageWatch = imageWatch;
	}

	public MultipartFile[] getAnhphu() {
		return anhphu;
	}

	public void setAnhphu(MultipartFile[] anhphu) {
		this.anhphu = anhphu;
	}

	public MultipartFile getAnhnen() {
		return anhnen;
	}

	public void setAnhnen(MultipartFile anhnen) {
		this.anhnen = anhnen;
	}

	@Override
	public String toString() {
		return "Watch [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", banner="
				+ banner + ", listColor=" + listColor + ", description=" + description + ", category=" + category
				+ ", tradeMark=" + tradeMark + ", colorWatchs=" + colorWatchs + ", imageWatch=" + imageWatch + "]";
	}

}
