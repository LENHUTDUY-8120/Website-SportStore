package com.LND.SportStore.model;

import java.util.List;

import lombok.Data;

@Data
public class Order {
	private int id;
	private List<Item> item;
	private String fullname;
	private String address;
	private String tel;
	private String email;
	private int total;
	private String additional_info;
	private int process;
}
