package com.lixa.dao;

import java.util.List;

import com.lixa.bean.Order;

public interface OrderDao {

	/**
	 * 添加菜品
	 * @param order
	 */
	int addOrder(Order order);

	/**
	 * 获取所有菜品列表
	 * @return List<Order>
	 */
	List<Order> getAllOrder();

	/**
	 * 删除菜
	 * @param id
	 */
	int deleteOrderById(String id);

	/**
	 * 修改菜单
	 */
	int updateOrder(Order order);

	
	/**
	 * 查询菜单
	 */
	Order getOrderById(String orderId);
	
	/**
	 * 查询最新数据
	 */
	int getNewOrder(String substring);

}
