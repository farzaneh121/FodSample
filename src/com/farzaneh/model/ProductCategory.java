package com.farzaneh.model;

public class ProductCategory {
	private Integer Id;
	private String name;

	public ProductCategory() {
		super();
	}

	public ProductCategory(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
