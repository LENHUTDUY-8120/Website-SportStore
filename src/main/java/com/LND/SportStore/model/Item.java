package com.LND.SportStore.model;

import lombok.Data;

@Data
public class Item {
	private int id;
	private String title;
	private ProductDetail product;
	private String image;
	private int price;
	private int quantity;
}
