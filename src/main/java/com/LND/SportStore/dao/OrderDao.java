package com.LND.SportStore.dao;

import java.util.List;

import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;

public interface OrderDao {

	public List<Order> getListOrder();
	
	public Order getOrder(int id);
	
	public List<Item> getListItem( int id );
	
	public void checkOrder( int id);
	
	public void addOrder(Order order1, Order order2);
	
	public void addOrderDetail(Item item, int id);
}
