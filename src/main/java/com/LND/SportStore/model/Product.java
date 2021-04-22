package com.LND.SportStore.model;

import lombok.Data;

@Data
public class Product {
	private int id;
	private String title;
	private String brand;
	private int price;
	private String image;
}

