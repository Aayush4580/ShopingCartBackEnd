package com.niit.shoppingcart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Product")
@Component
public class Product {
	
	@Id
	private String id;
	private String name;
	private String description;
	private double price;
	private String Category_id;
	private String Supplier_id;
	
	@ManyToOne
	@JoinColumn(name="Category_id",updatable=false,insertable=false,nullable=false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="Supplier_id",updatable=false,insertable=false,nullable=false)
	private Category supplier;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory_id() {
		return Category_id;
	}
	public void setCategory_id(String category_id) {
		Category_id = category_id;
	}
	public String getSupplier_id() {
		return Supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		Supplier_id = supplier_id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getSupplier() {
		return supplier;
	}
	public void setSupplier(Category supplier) {
		this.supplier = supplier;
	}
	
	
}
